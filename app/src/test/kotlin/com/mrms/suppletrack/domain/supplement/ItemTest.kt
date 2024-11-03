package com.mrms.suppletrack.domain.supplement

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.time.LocalDate
import java.util.stream.Stream

private class ItemTest {
    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class CalculateSupplyDays {
        private fun calculateSupplyDaysParams(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(1000, 10, 3, 33),
                Arguments.of(300, 2, 1, 150),
                Arguments.of(120, 2, 1, 60),
                Arguments.of(360, 5, 1, 72),
                Arguments.of(2500, 37, 2, 33),
            )
        }

        @ParameterizedTest
        @MethodSource("calculateSupplyDaysParams")
        fun `総量、一回あたりの摂取量、1日あたりの摂取回数から供給日数を計算できる`(
            quantity: Int,
            dosagePerUse: Int,
            dailyIntakeFrequency: Int,
            expected: Int,
        ) {
            val actual =
                Item.calculateSupplyDays(
                    quantity,
                    dosagePerUse,
                    dailyIntakeFrequency,
                )

            assertEquals(expected, actual)
        }
    }

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class CalculateEndAt {
        private fun calculateEndAtParams(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(LocalDate.of(2024, 6, 22), 60, LocalDate.of(2024, 8, 21)),
                Arguments.of(LocalDate.of(2024, 6, 22), 30, LocalDate.of(2024, 7, 22)),
                Arguments.of(LocalDate.of(2024, 6, 22), 3, LocalDate.of(2024, 6, 25)),
                Arguments.of(LocalDate.of(2024, 6, 22), 5, LocalDate.of(2024, 6, 27)),
                Arguments.of(LocalDate.of(2024, 6, 22), 120, LocalDate.of(2024, 10, 20)),
            )
        }

        @ParameterizedTest
        @MethodSource("calculateEndAtParams")
        fun `供給日数と開始日時から終了日を計算できる`(
            startAt: LocalDate,
            supplyDays: Int,
            expected: LocalDate,
        ) {
            val actual =
                Item.calculateEndAt(
                    supplyDays,
                    startAt,
                )
            assertEquals(expected, actual)
        }
    }
}
