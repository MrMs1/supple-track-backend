package com.mrms.suppletrack.domain.repository

import com.mrms.suppletrack.domain.supplement.Supplement
import java.util.UUID

interface SupplementRepository {
    fun save(supplement: Supplement)

    fun findAll(): List<Supplement>

    fun findById(id: UUID): Supplement?

    fun update(supplement: Supplement)
}
