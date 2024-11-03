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
            val supplementsRecord = createSupplementIfNotExist(supplement)

            val item = supplement.items.first()
            val itemsRecord =
                context.insertInto(
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
                    supplementsRecord.id,
                ).returning()
                    .fetchOne()!!

            return toSupplement(supplementsRecord, itemsRecord)
        }

        private fun toSupplement(
            supplementsRecord: SupplementsRecord,
            itemsRecord: ItemsRecord,
        ): Supplement {
            val item =
                Item(
                    id = itemsRecord.id,
                    name = itemsRecord.name,
                    quantity = itemsRecord.quantity,
                    dosagePerUse = itemsRecord.dosagePerUse,
                    dailyIntakeFrequency = itemsRecord.dailyIntakeFrequency,
                    supplyDays = itemsRecord.supplyDays,
                    expiredAt = itemsRecord.expiredAt,
                    startAt = itemsRecord.startAt,
                    endAt = itemsRecord.endAt,
                )
            return Supplement(
                name = supplementsRecord.name,
                items = listOf(item),
            )
        }

        private fun createSupplementIfNotExist(supplement: Supplement): SupplementsRecord {
            val existSupplement =
                context
                    .select(
                        SUPPLEMENTS,
                    )
                    .from(SUPPLEMENTS)
                    .where(SUPPLEMENTS.NAME.eq(supplement.name))
                    .fetchOne()
                    ?.value1()

            // サプリメントが存在しない場合は登録する
            return if (existSupplement == null) {
                context.insertInto(
                    SUPPLEMENTS,
                    SUPPLEMENTS.NAME,
                ).values(
                    supplement.name,
                ).returning()
                    .fetchOne()!!
            } else {
                existSupplement
            }
        }
    }
