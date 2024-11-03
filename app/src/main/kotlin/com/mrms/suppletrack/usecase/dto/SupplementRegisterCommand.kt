package com.mrms.suppletrack.usecase.dto

import java.time.LocalDate

data class SupplementRegisterCommand(
    val supplementName: String,
    val itemName: String,
    val quantity: Int,
    val dosagePerUse: Int,
    val dailyIntakeFrequency: Int,
    val expiredAt: LocalDate,
    val startAt: LocalDate,
)
