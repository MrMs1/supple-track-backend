package com.mrms.suppletrack.usecase

import com.mrms.suppletrack.domain.repository.SupplementRepository
import com.mrms.suppletrack.domain.supplement.Item
import com.mrms.suppletrack.domain.supplement.Supplement
import com.mrms.suppletrack.domain.supplement.SupplementService
import com.mrms.suppletrack.usecase.dto.ItemRegisterCommand
import com.mrms.suppletrack.usecase.dto.SupplementRegisterCommand
import com.mrms.suppletrack.usecase.dto.SupplementRemoveCommand
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class SupplementUseCase
    @Autowired
    constructor(
        private val supplementRepository: SupplementRepository,
        private val supplementService: SupplementService,
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
    }
