package com.mrms.suppletrack.usecase.dto

import java.time.LocalDate
import java.util.UUID

data class UpdateItemCommand(
    val id: UUID,
    val itemName: String?,
    val quantity: Int?,
    val dosagePerUse: Int?,
    val dailyIntakeFrequency: Int?,
    val expiredAt: LocalDate?,
    val startAt: LocalDate?,
)
