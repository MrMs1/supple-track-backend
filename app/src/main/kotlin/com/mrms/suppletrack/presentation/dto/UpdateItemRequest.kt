package com.mrms.suppletrack.presentation.dto

import java.time.LocalDate
import java.util.UUID

data class UpdateItemRequest(
    val id: UUID,
    val itemName: String,
    val quantity: Int,
    val dosagePerUse: Int,
    val dailyIntakeFrequency: Int,
    val expiredAt: LocalDate,
    val startAt: LocalDate,
)
