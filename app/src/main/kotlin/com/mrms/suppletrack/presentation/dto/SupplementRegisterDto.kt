package com.mrms.suppletrack.presentation.dto

import java.time.LocalDate

data class SupplementRegisterDto(
    val supplementName: String,
    val itemName: String,
    val name: String,
    val quantity: Int,
    val dosagePerUse: Int,
    val dailyIntakeFrequency: Int,
    val expiredAt: LocalDate,
    val startAt: LocalDate,
)
