package com.mrms.suppletrack.presentation.convertor

import com.mrms.suppletrack.presentation.dto.SupplementRegisterDto
import com.mrms.suppletrack.usecase.dto.SupplementRegisterCommand

fun SupplementRegisterDto.toCommand(): SupplementRegisterCommand {
    return SupplementRegisterCommand(
        name = this.name,
        quantity = this.quantity,
        dosagePerUse = this.dosagePerUse,
        dailyIntakeFrequency = this.dailyIntakeFrequency,
        expiredAt = this.expiredAt,
        startAt = this.startAt,
        groupName = this.groupName,
    )
}
