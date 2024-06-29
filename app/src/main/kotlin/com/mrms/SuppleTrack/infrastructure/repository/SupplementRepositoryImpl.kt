package com.mrms.suppletrack.infrastructure.repository

import com.mrms.suppletrack.domain.repository.SupplementRepository
import com.mrms.suppletrack.domain.supplement.Supplement
import org.jooq.DSLContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import supplement.Tables.SUPPLEMENTS
import supplement.Tables.SUPPLEMENT_GROUP
import supplement.tables.records.SupplementsRecord
import java.util.UUID

@Repository
class SupplementRepositoryImpl
    @Autowired
    constructor(
        private val context: DSLContext,
    ) : SupplementRepository {
        override fun save(supplement: Supplement) {
            // 新規のサプリメントグループは登録する
            createSupplementGroup(supplement)

            context.insertInto(
                SUPPLEMENTS,
                SUPPLEMENTS.ID,
                SUPPLEMENTS.NAME,
                SUPPLEMENTS.DOSAGE_PER_USE,
                SUPPLEMENTS.QUANTITY,
                SUPPLEMENTS.DAILY_INTAKE_FREQUENCY,
                SUPPLEMENTS.EXPIRED_AT,
                SUPPLEMENTS.START_AT,
                SUPPLEMENTS.END_AT,
                SUPPLEMENTS.GROUP_NAME,
            ).values(
                supplement.id,
                supplement.name,
                supplement.dosagePerUse,
                supplement.quantity,
                supplement.dailyIntakeFrequency,
                supplement.expiredAt,
                supplement.startAt,
                supplement.endAt,
                supplement.groupName,
            ).execute()
        }

        override fun findAll(): List<Supplement> {
            return context.selectFrom(SUPPLEMENTS)
                .orderBy(SUPPLEMENTS.CREATED_AT.desc())
                .fetch()
                .map {
                    it.toSupplement()
                }
        }

        override fun findAllGroup(): List<String> {
            return context.select(SUPPLEMENT_GROUP.NAME)
                .from(SUPPLEMENT_GROUP)
                .orderBy(SUPPLEMENT_GROUP.CREATED_AT.asc())
                .fetch()
                .map {
                    it.value1()
                }
        }

        override fun findById(id: UUID): Supplement? {
            return context.selectFrom(SUPPLEMENTS)
                .where(SUPPLEMENTS.ID.eq(id))
                .fetchOne()
                ?.toSupplement()
        }

        override fun update(supplement: Supplement) {
            context.update(SUPPLEMENTS)
                .set(SUPPLEMENTS.NAME, supplement.name)
                .set(SUPPLEMENTS.DOSAGE_PER_USE, supplement.dosagePerUse)
                .set(SUPPLEMENTS.QUANTITY, supplement.quantity)
                .set(SUPPLEMENTS.DAILY_INTAKE_FREQUENCY, supplement.dailyIntakeFrequency)
                .set(SUPPLEMENTS.EXPIRED_AT, supplement.expiredAt)
                .set(SUPPLEMENTS.START_AT, supplement.startAt)
                .set(SUPPLEMENTS.END_AT, supplement.endAt)
                .where(SUPPLEMENTS.ID.eq(supplement.id))
                .execute()
        }

        override fun updateGroup(supplement: Supplement) {
            // 新規のサプリメントグループは登録する
            createSupplementGroup(supplement)
            context.update(SUPPLEMENTS)
                .set(SUPPLEMENTS.GROUP_NAME, supplement.groupName)
                .where(SUPPLEMENTS.ID.eq(supplement.id))
                .execute()
        }

        override fun delete(supplement: Supplement) {
            context.deleteFrom(SUPPLEMENTS)
                .where(SUPPLEMENTS.ID.eq(supplement.id))
                .execute()
        }

        override fun deleteGroup(groupName: String) {
            context.update(SUPPLEMENTS)
                .setNull(SUPPLEMENTS.GROUP_NAME)
                .where(SUPPLEMENTS.GROUP_NAME.eq(groupName))
                .execute()
            context.deleteFrom(SUPPLEMENT_GROUP)
                .where(SUPPLEMENT_GROUP.NAME.eq(groupName))
                .execute()
        }

        private fun createSupplementGroup(supplement: Supplement) {
            if (supplement.groupName != null) {
                val isNewGroup =
                    context.select(SUPPLEMENT_GROUP.NAME)
                        .from(SUPPLEMENT_GROUP)
                        .where(SUPPLEMENT_GROUP.NAME.eq(supplement.groupName))
                        .fetchOne()
                        ?.value1() == null

                if (isNewGroup) {
                    context.insertInto(
                        SUPPLEMENT_GROUP,
                        SUPPLEMENT_GROUP.NAME,
                    ).values(
                        supplement.groupName,
                    ).execute()
                }
            }
        }

        private fun SupplementsRecord.toSupplement(): Supplement {
            return Supplement(
                id = this.id,
                name = this.name,
                dosagePerUse = this.dosagePerUse,
                quantity = this.quantity,
                dailyIntakeFrequency = this.dailyIntakeFrequency,
                expiredAt = this.expiredAt,
                startAt = this.startAt,
                groupName = this.groupName,
            )
        }
    }
