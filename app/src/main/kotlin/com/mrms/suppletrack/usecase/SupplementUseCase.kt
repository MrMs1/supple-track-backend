package com.mrms.suppletrack.usecase

import com.mrms.suppletrack.domain.repository.SupplementRepository
import com.mrms.suppletrack.domain.supplement.Item
import com.mrms.suppletrack.domain.supplement.Supplement
import com.mrms.suppletrack.domain.supplement.SupplementService
import com.mrms.suppletrack.usecase.command.SupplementCommandService
import com.mrms.suppletrack.usecase.dto.ItemRegisterCommand
import com.mrms.suppletrack.usecase.dto.SupplementRegisterCommand
import com.mrms.suppletrack.usecase.dto.SupplementRemoveCommand
import com.mrms.suppletrack.usecase.dto.UpdateItemCommand
import com.mrms.suppletrack.usecase.dto.UpdateSupplementCommand
import com.mrms.suppletrack.usecase.dto.UpdateSupplementCommandResult
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class SupplementUseCase
    @Autowired
    constructor(
        private val supplementRepository: SupplementRepository,
        private val supplementService: SupplementService,
        private val supplementCommandService: SupplementCommandService,
    ) {
        fun createSupplement(command: SupplementRegisterCommand): Supplement {
            return supplementService.createSupplement(
                command.supplementName,
                command.itemName,
                command.quantity,
                command.dosagePerUse,
                command.dailyIntakeFrequency,
                command.expiredAt,
                command.startAt,
            )
        }

        fun addItem(command: ItemRegisterCommand): Item {
            val item =
                Item.create(
                    name = command.itemName,
                    quantity = command.quantity,
                    dosagePerUse = command.dosagePerUse,
                    dailyIntakeFrequency = command.dailyIntakeFrequency,
                    expiredAt = command.expiredAt,
                    startAt = command.startAt,
                )

            return supplementRepository.saveItem(command.supplementName, item)
        }

        fun getSupplements(): List<Supplement> {
            return supplementRepository.getSupplements()
        }

        fun removeSupplement(command: SupplementRemoveCommand) {
            supplementRepository.removeSupplement(command.name)
        }

        fun removeItem(id: UUID) {
            supplementRepository.removeItem(id)
        }

        fun updateSupplement(command: UpdateSupplementCommand): UpdateSupplementCommandResult {
            return UpdateSupplementCommandResult(supplementCommandService.updateSupplement(command.name))
        }

        fun updateItem(command: UpdateItemCommand): Item {
            val targetItem = supplementRepository.getItem(command.id)
            val updatedItem =
                targetItem.update(
                    command.itemName,
                    command.quantity,
                    command.dosagePerUse,
                    command.dailyIntakeFrequency,
                    command.expiredAt,
                    command.startAt,
                )
            return supplementRepository.updateItem(updatedItem)
        }
    }
