package com.mrms.suppletrack.domain.repository

import com.mrms.suppletrack.domain.supplement.Supplement

interface SupplementRepository {
    fun save(supplement: Supplement)
}
