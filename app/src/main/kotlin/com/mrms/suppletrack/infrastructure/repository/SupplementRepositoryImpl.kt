package com.mrms.suppletrack.infrastructure.repository

import com.mrms.suppletrack.domain.repository.SupplementRepository
import com.mrms.suppletrack.domain.supplement.Item
import com.mrms.suppletrack.domain.supplement.Supplement
import org.jooq.DSLContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import supplement.Tables.ITEMS
import supplement.Tables.SUPPLEMENTS
import supplement.tables.records.ItemsRecord
import supplement.tables.records.SupplementsRecord

@Repository
class SupplementRepositoryImpl
    @Autowired
    constructor(
        private val context: DSLContext,
    ) : SupplementRepository {
        override fun save(supplement: Supplement): Supplement {
            // サプリメント登録
            val supplementsRecord = createSupplementIfNotExist(supplement.name)

            val item = supplement.items.first()
            val itemsRecord = insertItem(item, supplementsRecord.id)

            return supplementsRecord.toSupplement(listOf(itemsRecord))
        }

        override fun saveItem(
            supplementName: String,
            item: Item,
        ): Item {
            // サプリメント登録
            val supplementsRecord = createSupplementIfNotExist(supplementName)
            return insertItem(item, supplementsRecord.id).toItem()
        }

        override fun getSupplements(): List<Supplement> {
            return context.select(
                SUPPLEMENTS.ID,
                SUPPLEMENTS.NAME,
                ITEMS.ID,
                ITEMS.NAME,
                ITEMS.DOSAGE_PER_USE,
                ITEMS.QUANTITY,
                ITEMS.DAILY_INTAKE_FREQUENCY,
                ITEMS.SUPPLY_DAYS,
                ITEMS.EXPIRED_AT,
                ITEMS.START_AT,
                ITEMS.END_AT,
            ).from(ITEMS)
                .leftJoin(SUPPLEMENTS)
                .on(ITEMS.SUPPLEMENT_ID.eq(SUPPLEMENTS.ID))
                .fetch()
                .groupBy { it[SUPPLEMENTS.ID] }
                .mapValues { entry ->
                    val supplementRecord = entry.value.first().into(SUPPLEMENTS)
                    val itemRecords = entry.value.map { it.into(ITEMS) }
                    supplementRecord.toSupplement(itemRecords)
                }.values.toList()
        }

        private fun createSupplementIfNotExist(supplementName: String): SupplementsRecord {
            val existSupplement =
                context
                    .select(
                        SUPPLEMENTS,
                    )
                    .from(SUPPLEMENTS)
                    .where(SUPPLEMENTS.NAME.eq(supplementName))
                    .fetchOne()
                    ?.value1()

            // サプリメントが存在しない場合は登録する
            return if (existSupplement == null) {
                context.insertInto(
                    SUPPLEMENTS,
                    SUPPLEMENTS.NAME,
                ).values(
                    supplementName,
                ).returning()
                    .fetchOne()!!
            } else {
                existSupplement
            }
        }

        private fun insertItem(
            item: Item,
            supplementId: Int,
        ): ItemsRecord {
            return context.insertInto(
                ITEMS,
                ITEMS.ID,
                ITEMS.NAME,
                ITEMS.DOSAGE_PER_USE,
                ITEMS.QUANTITY,
                ITEMS.DAILY_INTAKE_FREQUENCY,
                ITEMS.SUPPLY_DAYS,
                ITEMS.EXPIRED_AT,
                ITEMS.START_AT,
                ITEMS.END_AT,
                ITEMS.SUPPLEMENT_ID,
            ).values(
                item.id,
                item.name,
                item.dosagePerUse,
                item.quantity,
                item.dailyIntakeFrequency,
                item.supplyDays,
                item.expiredAt,
                item.startAt,
                item.endAt,
                supplementId,
            ).returning()
                .fetchOne()!!
        }

        private fun ItemsRecord.toItem(): Item {
            return Item(
                id = this.id,
                name = this.name,
                quantity = this.quantity,
                dosagePerUse = this.dosagePerUse,
                dailyIntakeFrequency = this.dailyIntakeFrequency,
                supplyDays = this.supplyDays,
                expiredAt = this.expiredAt,
                startAt = this.startAt,
                endAt = this.endAt,
            )
        }

        private fun SupplementsRecord.toSupplement(
            itemsRecords: List<ItemsRecord>,
        ): Supplement {
            return Supplement(
                name = this.name,
                items = itemsRecords.map { it.toItem() },
            )
        }
    }
