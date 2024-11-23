package com.mrms.suppletrack.infrastructure.repository.command

import com.mrms.suppletrack.usecase.command.SupplementCommandService
import org.jooq.DSLContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import supplement.Tables.SUPPLEMENTS

@Repository
class SupplementCommandServiceImpl
    @Autowired
    constructor(
        private val context: DSLContext,
    ) : SupplementCommandService {
        override fun updateSupplement(name: String): String {
            return context.select(SUPPLEMENTS.ID)
                .from(SUPPLEMENTS)
                .where(SUPPLEMENTS.NAME.eq(name))
                .fetchOne()!!
                .into(SUPPLEMENTS)
                .id
                .let {
                    context.update(SUPPLEMENTS)
                        .set(SUPPLEMENTS.NAME, name)
                        .where(SUPPLEMENTS.ID.eq(it))
                        .returning()
                        .fetchOne()!!
                }.map { record ->
                    record.into(SUPPLEMENTS).name
                }
        }
    }
