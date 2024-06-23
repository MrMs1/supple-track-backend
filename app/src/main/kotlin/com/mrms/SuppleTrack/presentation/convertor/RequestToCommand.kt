package com.mrms.suppletrack.presentation.convertor

import com.mrms.suppletrack.presentation.dto.SupplementRegisterDto
import com.mrms.suppletrack.presentation.dto.SupplementUpdateDto
import com.mrms.suppletrack.usecase.dto.SupplementRegisterCommand
import com.mrms.suppletrack.usecase.dto.SupplementUpdateCommand
import java.util.UUID

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

fun SupplementUpdateDto.toCommand(id: UUID): SupplementUpdateCommand {
    return SupplementUpdateCommand(
        id = id,
        name = this.name,
        quantity = this.quantity,
        dosagePerUse = this.dosagePerUse,
        dailyIntakeFrequency = this.dailyIntakeFrequency,
        expiredAt = this.expiredAt,
        startAt = this.startAt,
    )
}
