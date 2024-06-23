package com.mrms.suppletrack.presentation.dto

import java.time.LocalDate

data class SupplementUpdateDto(
    val name: String?,
    val quantity: Int?,
    val dosagePerUse: Int?,
    val dailyIntakeFrequency: Int?,
    val expiredAt: LocalDate?,
    val startAt: LocalDate?,
)
