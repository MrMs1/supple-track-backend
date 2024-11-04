package com.mrms.suppletrack.presentation.dto

import java.time.LocalDate

data class ItemRegisterDto(
    val supplementName: String,
    val itemName: String,
    val quantity: Int,
    val dosagePerUse: Int,
    val dailyIntakeFrequency: Int,
    val expiredAt: LocalDate,
    val startAt: LocalDate,
)
