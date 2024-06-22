package com.mrms.suppletrack.domain.supplement

import java.time.LocalDate
import java.util.UUID

data class Supplement(
    val id: UUID,
    val name: String,
    val quantity: Int,
    val dosagePerUse: Int,
    val dailyIntakeFrequency: Int,
    val expiredAt: LocalDate,
    val startAt: LocalDate,
    val endAt: LocalDate,
    val groupName: String?,
) {
    companion object {
        fun create(
            name: String,
            quantity: Int,
            dosagePerUse: Int,
            dailyIntakeFrequency: Int,
            expiredAt: LocalDate,
            startAt: LocalDate,
            groupName: String? = null,
        ): Supplement {
            val daysOfConsumption = quantity / (dosagePerUse * dailyIntakeFrequency)
            val endAt = startAt.plusDays(daysOfConsumption.toLong())

            return Supplement(
                id = UUID.randomUUID(),
                name = name,
                quantity = quantity,
                dosagePerUse = dosagePerUse,
                dailyIntakeFrequency = dailyIntakeFrequency,
                expiredAt = expiredAt,
                startAt = startAt,
                endAt = endAt,
                groupName = groupName,
            )
        }
    }
}
