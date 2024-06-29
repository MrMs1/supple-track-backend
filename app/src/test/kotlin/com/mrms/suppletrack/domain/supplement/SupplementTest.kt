package com.mrms.suppletrack.domain.supplement

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.time.LocalDate
import java.util.stream.Stream

private class SupplementTest {
    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class EndAt {
        private fun calculateParams(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(LocalDate.of(2024, 6, 22), 1000, 10, 3, LocalDate.of(2024, 7, 25)),
                Arguments.of(LocalDate.of(2024, 6, 22), 300, 2, 1, LocalDate.of(2024, 11, 19)),
                Arguments.of(LocalDate.of(2024, 6, 22), 120, 2, 1, LocalDate.of(2024, 8, 21)),
                Arguments.of(LocalDate.of(2024, 6, 22), 360, 5, 1, LocalDate.of(2024, 9, 2)),
                Arguments.of(LocalDate.of(2024, 6, 22), 2500, 37, 2, LocalDate.of(2024, 7, 25)),
            )
        }

        @ParameterizedTest
        @MethodSource("calculateParams")
        fun `総量、一回あたりの摂取量、1日あたりの摂取回数から算出された摂取終了日が取得できる`(
            startAt: LocalDate,
            quantity: Int,
            dosagePerUse: Int,
            dailyIntakeFrequency: Int,
            endAt: LocalDate,
        ) {
            val actual =
                Supplement.create(
                    "サプリメント",
                    quantity,
                    dosagePerUse,
                    dailyIntakeFrequency,
                    LocalDate.of(2025, 6, 22),
                    startAt,
                )

            assertEquals(endAt, actual.endAt)
        }
    }
}
