package com.mrms.suppletrack.infrastructure.repository

import com.mrms.suppletrack.domain.repository.SupplementRepository
import com.mrms.suppletrack.domain.supplement.Supplement
import org.jooq.DSLContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import supplement.Tables.SUPPLEMENTS

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
    }
