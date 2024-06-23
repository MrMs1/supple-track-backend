package com.mrms.suppletrack.usecase

import com.mrms.suppletrack.domain.repository.SupplementRepository
import com.mrms.suppletrack.domain.supplement.Supplement
import com.mrms.suppletrack.usecase.dto.SupplementRegisterCommand
import com.mrms.suppletrack.usecase.dto.SupplementUpdateCommand
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class SupplementUseCase
    @Autowired
    constructor(
        private val supplementRepository: SupplementRepository,
    ) {
        fun createSupplement(command: SupplementRegisterCommand) {
            val supplement =
                Supplement.create(
                    name = command.name,
                    quantity = command.quantity,
                    dosagePerUse = command.dosagePerUse,
                    dailyIntakeFrequency = command.dailyIntakeFrequency,
                    expiredAt = command.expiredAt,
                    startAt = command.startAt,
                    groupName = command.groupName,
                )
            supplementRepository.save(supplement)
        }

        fun findAllSupplements(): List<Supplement> {
            return supplementRepository.findAll()
        }

        fun findSupplementById(id: UUID): Supplement? {
            return supplementRepository.findById(id)
        }

        fun updateSupplement(command: SupplementUpdateCommand) {
            val supplement = supplementRepository.findById(command.id) ?: throw Exception()
            val newSupplement =
                supplement.update(
                    command.name,
                    command.quantity,
                    command.dosagePerUse,
                    command.dailyIntakeFrequency,
                    command.expiredAt,
                    command.startAt,
                )
            supplementRepository.update(newSupplement)
        }
    }
