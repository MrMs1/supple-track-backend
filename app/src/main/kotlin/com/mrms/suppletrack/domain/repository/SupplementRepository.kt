package com.mrms.suppletrack.domain.repository

import com.mrms.suppletrack.domain.supplement.Item
import com.mrms.suppletrack.domain.supplement.Supplement

interface SupplementRepository {
    fun save(supplement: Supplement): Supplement

    fun saveItem(
        supplementName: String,
        item: Item,
    ): Item

    fun getSupplements(): List<Supplement>
}
