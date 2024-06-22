package com.mrms.suppletrack.infrastructure.repository

import com.mrms.suppletrack.domain.repository.SupplementRepository
import com.mrms.suppletrack.domain.supplement.Supplement
import org.jooq.DSLContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import supplement.Tables.SUPPLEMENTS
import supplement.tables.records.SupplementsRecord
import java.util.UUID

@Repository
class SupplementRepositoryImpl
    @Autowired
    constructor(
        private val context: DSLContext,
    ) : SupplementRepository {
        override fun save(supplement: Supplement) {
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

        override fun findById(id: UUID): Supplement? {
            return context.selectFrom(SUPPLEMENTS)
                .where(SUPPLEMENTS.ID.eq(id))
                .fetchOne()
                ?.toSupplement()
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
                endAt = this.endAt,
                groupName = this.groupName,
            )
        }
    }
