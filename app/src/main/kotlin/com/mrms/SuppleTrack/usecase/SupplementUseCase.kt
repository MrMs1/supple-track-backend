package com.mrms.suppletrack.usecase

import com.mrms.suppletrack.domain.exception.DomainNotFoundException
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

        fun findAllSupplementGroup(): List<String> {
            return supplementRepository.findAllGroup()
        }

        fun findSupplementById(id: UUID): Supplement? {
            return supplementRepository.findById(id)
        }

        fun updateSupplement(command: SupplementUpdateCommand) {
            val supplement = supplementRepository.findById(command.id) ?: throw DomainNotFoundException("対象のサプリメントが見つかりません。 id: ${command.id}")
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

        fun updateSupplementGroup(
            id: UUID,
            groupName: String?,
        ) {
            val supplement = supplementRepository.findById(id) ?: throw DomainNotFoundException("対象のサプリメントが見つかりません。 id: $id")
            val newSupplement = supplement.updateGroup(groupName)
            supplementRepository.updateGroup(newSupplement)
        }

        fun deleteSupplement(id: UUID) {
            val supplement = supplementRepository.findById(id) ?: throw DomainNotFoundException("対象のサプリメントが見つかりません。 id: $id")
            supplementRepository.delete(supplement)
        }

        fun deleteSupplementGroup(groupName: String) {
            supplementRepository.deleteGroup(groupName)
        }
    }
