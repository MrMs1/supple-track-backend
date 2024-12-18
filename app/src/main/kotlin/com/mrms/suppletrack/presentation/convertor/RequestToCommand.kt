package com.mrms.suppletrack.presentation.convertor

import com.mrms.suppletrack.presentation.dto.ItemRegisterDto
import com.mrms.suppletrack.presentation.dto.SupplementRegisterDto
import com.mrms.suppletrack.presentation.dto.UpdateItemRequest
import com.mrms.suppletrack.usecase.dto.ItemRegisterCommand
import com.mrms.suppletrack.usecase.dto.SupplementRegisterCommand
import com.mrms.suppletrack.usecase.dto.UpdateItemCommand
import java.util.UUID

fun SupplementRegisterDto.toCommand(): SupplementRegisterCommand {
    return SupplementRegisterCommand(
        supplementName = this.supplementName,
        itemName = this.itemName,
        quantity = this.quantity,
        dosagePerUse = this.dosagePerUse,
        dailyIntakeFrequency = this.dailyIntakeFrequency,
        expiredAt = this.expiredAt,
        startAt = this.startAt,
    )
}

fun ItemRegisterDto.toCommand(): ItemRegisterCommand {
    return ItemRegisterCommand(
        supplementName = this.supplementName,
        itemName = this.itemName,
        quantity = this.quantity,
        dosagePerUse = this.dosagePerUse,
        dailyIntakeFrequency = this.dailyIntakeFrequency,
        expiredAt = this.expiredAt,
        startAt = this.startAt,
    )
}

fun UpdateItemRequest.toCommand(id: UUID): UpdateItemCommand {
    return UpdateItemCommand(
        id = id,
        itemName = this.itemName,
        quantity = this.quantity,
        dosagePerUse = this.dosagePerUse,
        dailyIntakeFrequency = this.dailyIntakeFrequency,
        expiredAt = this.expiredAt,
        startAt = this.startAt,
    )
}
