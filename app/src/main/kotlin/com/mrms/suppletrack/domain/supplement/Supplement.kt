package com.mrms.suppletrack.domain.supplement

data class Supplement(
    val name: String,
    val items: List<Item>,
) {
    companion object {
        fun create(
            name: String,
            items: List<Item>,
        ): Supplement {
            return Supplement(
                name = name,
                items = items,
            )
        }
    }
}
