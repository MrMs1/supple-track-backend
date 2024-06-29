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
    val groupName: String?,
) {
    val endAt: LocalDate
        get() {
            val daysOfConsumption = quantity / (dosagePerUse * dailyIntakeFrequency)
            return startAt.plusDays(daysOfConsumption.toLong())
        }

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
            return Supplement(
                id = UUID.randomUUID(),
                name = name,
                quantity = quantity,
                dosagePerUse = dosagePerUse,
                dailyIntakeFrequency = dailyIntakeFrequency,
                expiredAt = expiredAt,
                startAt = startAt,
                groupName = groupName,
            )
        }
    }

    fun update(
        name: String?,
        quantity: Int?,
        dosagePerUse: Int?,
        dailyIntakeFrequency: Int?,
        expiredAt: LocalDate?,
        startAt: LocalDate?,
    ): Supplement {
        val newQuantity = quantity ?: this.quantity
        val newDosagePerUse = dosagePerUse ?: this.dosagePerUse
        val newDailyIntakeFrequency = dailyIntakeFrequency ?: this.dailyIntakeFrequency
        val newStartAt = startAt ?: this.startAt

        return this.copy(
            name = name ?: this.name,
            quantity = newQuantity,
            dosagePerUse = newDosagePerUse,
            dailyIntakeFrequency = newDailyIntakeFrequency,
            expiredAt = expiredAt ?: this.expiredAt,
            startAt = newStartAt,
        )
    }

    fun updateGroup(groupName: String?): Supplement {
        return this.copy(groupName = groupName)
    }
}
