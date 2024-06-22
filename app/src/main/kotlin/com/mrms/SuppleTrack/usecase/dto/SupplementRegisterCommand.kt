package com.mrms.suppletrack.usecase.dto

import java.time.LocalDate

data class SupplementRegisterCommand(
    val name: String,
    val quantity: Int,
    val dosagePerUse: Int,
    val dailyIntakeFrequency: Int,
    val expiredAt: LocalDate,
    val startAt: LocalDate,
    val groupName: String?,
)
