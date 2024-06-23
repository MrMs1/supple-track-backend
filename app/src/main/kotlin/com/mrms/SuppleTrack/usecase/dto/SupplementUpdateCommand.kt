package com.mrms.suppletrack.usecase.dto

import java.time.LocalDate
import java.util.UUID

data class SupplementUpdateCommand(
    val id: UUID,
    val name: String?,
    val quantity: Int?,
    val dosagePerUse: Int?,
    val dailyIntakeFrequency: Int?,
    val expiredAt: LocalDate?,
    val startAt: LocalDate?,
)
