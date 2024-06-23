package com.mrms.suppletrack.domain.repository

import com.mrms.suppletrack.domain.supplement.Supplement
import java.util.UUID

interface SupplementRepository {
    fun save(supplement: Supplement)

    fun findAll(): List<Supplement>

    fun findAllGroup(): List<String>

    fun findById(id: UUID): Supplement?

    fun update(supplement: Supplement)

    fun updateGroup(supplement: Supplement)

    fun delete(supplement: Supplement)

    fun deleteGroup(groupName: String)
}
