package com.mrms.suppletrack.domain.repository

import com.mrms.suppletrack.domain.supplement.Item
import com.mrms.suppletrack.domain.supplement.Supplement
import java.util.UUID

interface SupplementRepository {
    fun save(supplement: Supplement): Supplement

    fun saveItem(
        supplementName: String,
        item: Item,
    ): Item

    fun getSupplements(): List<Supplement>

    fun getItem(id: UUID): Item

    fun removeSupplement(name: String)

    fun removeItem(id: UUID)

    fun updateItem(item: Item): Item
}
