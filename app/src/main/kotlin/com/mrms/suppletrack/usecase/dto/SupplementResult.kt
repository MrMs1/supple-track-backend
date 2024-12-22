package com.mrms.suppletrack.usecase.dto

import com.mrms.suppletrack.domain.supplement.Item
import java.time.LocalDate

data class SupplementResult(
    val name: String,
    val items: List<Item>,
) {
    // 商品がない場合は存在しない
    val endAt: LocalDate? = items.maxByOrNull { it.endAt }?.endAt

    val totalSupplyDays = items.sumOf { it.supplyDays }
    val totalRestOfSupplyDays = items.sumOf { it.restOfSupplyDays }
}
