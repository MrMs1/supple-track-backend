package com.mrms.suppletrack.domain.supplement

import com.mrms.suppletrack.domain.repository.SupplementRepository
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class SupplementService(
    val supplementRepository: SupplementRepository,
) {
    fun createSupplement(
        supplementName: String,
        itemName: String,
        quantity: Int,
        dosagePerUse: Int,
        dailyIntakeFrequency: Int,
        expiredAt: LocalDate,
        startAt: LocalDate,
    ): Supplement {
        val item =
            Item.create(
                name = itemName,
                quantity = quantity,
                dosagePerUse = dosagePerUse,
                dailyIntakeFrequency = dailyIntakeFrequency,
                expiredAt = expiredAt,
                startAt = startAt,
            )
        val supplement =
            Supplement.create(
                name = supplementName,
                items = listOf(item),
            )
        return supplementRepository.save(supplement)
    }
}
