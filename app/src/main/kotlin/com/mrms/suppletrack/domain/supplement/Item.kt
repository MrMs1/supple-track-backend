package com.mrms.suppletrack.domain.supplement

import java.time.LocalDate
import java.util.UUID

data class Item(
    val id: UUID,
    val name: String,
    val quantity: Int,
    val dosagePerUse: Int,
    val dailyIntakeFrequency: Int,
    val supplyDays: Int,
    val expiredAt: LocalDate,
    val startAt: LocalDate,
    val endAt: LocalDate,
) {
    val restOfSupplyDays: Int
        get() {
            val today = LocalDate.now()
            val endAtPeriod = endAt.toEpochDay() - today.toEpochDay()
            return when {
                endAtPeriod < 0 -> 0
                endAtPeriod > supplyDays -> supplyDays
                else -> endAtPeriod.toInt()
            }
        }

    companion object {
        fun create(
            name: String,
            quantity: Int,
            dosagePerUse: Int,
            dailyIntakeFrequency: Int,
            expiredAt: LocalDate,
            startAt: LocalDate,
        ): Item {
            val supplyDays = calculateSupplyDays(quantity, dosagePerUse, dailyIntakeFrequency)
            return Item(
                id = UUID.randomUUID(),
                name = name,
                quantity = quantity,
                dosagePerUse = dosagePerUse,
                dailyIntakeFrequency = dailyIntakeFrequency,
                supplyDays = supplyDays,
                expiredAt = expiredAt,
                startAt = startAt,
                endAt = calculateEndAt(supplyDays, startAt),
            )
        }

        fun calculateSupplyDays(
            quantity: Int,
            dosagePerUse: Int,
            dailyIntakeFrequency: Int,
        ): Int {
            return quantity / (dosagePerUse * dailyIntakeFrequency)
        }

        fun calculateEndAt(
            supplyDays: Int,
            startAt: LocalDate,
        ): LocalDate {
            return startAt.plusDays(supplyDays.toLong())
        }
    }

    fun update(
        name: String?,
        quantity: Int?,
        dosagePerUse: Int?,
        dailyIntakeFrequency: Int?,
        expiredAt: LocalDate?,
        startAt: LocalDate?,
    ): Item {
        val newQuantity = quantity ?: this.quantity
        val newDosagePerUse = dosagePerUse ?: this.dosagePerUse
        val newDailyIntakeFrequency = dailyIntakeFrequency ?: this.dailyIntakeFrequency
        val newStartAt = startAt ?: this.startAt

        val supplyDays = calculateSupplyDays(newQuantity, newDosagePerUse, newDailyIntakeFrequency)
        return copy(
            name = name ?: this.name,
            quantity = newQuantity,
            dosagePerUse = newDosagePerUse,
            dailyIntakeFrequency = newDailyIntakeFrequency,
            supplyDays = supplyDays,
            expiredAt = expiredAt ?: this.expiredAt,
            startAt = newStartAt,
            endAt = calculateEndAt(supplyDays, newStartAt),
        )
    }
}
