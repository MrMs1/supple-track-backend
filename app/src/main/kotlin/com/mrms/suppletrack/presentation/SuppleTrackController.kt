package com.mrms.suppletrack.presentation

import com.mrms.suppletrack.domain.supplement.Supplement
import com.mrms.suppletrack.presentation.convertor.toCommand
import com.mrms.suppletrack.presentation.dto.ItemRegisterDto
import com.mrms.suppletrack.presentation.dto.SupplementRegisterDto
import com.mrms.suppletrack.usecase.SupplementUseCase
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class SuppleTrackController
    @Autowired
    constructor(
        private val supplementUseCase: SupplementUseCase,
    ) {
        @PostMapping("/supplement")
        fun registerSupplement(
            @RequestBody supplementRegisterDto: SupplementRegisterDto,
        ): ResponseEntity<Unit> {
            supplementUseCase.createSupplement(supplementRegisterDto.toCommand())
            return ResponseEntity.ok().build()
        }

        @PostMapping("/item")
        fun registerItem(
            @RequestBody itemRegisterDto: ItemRegisterDto,
        ): ResponseEntity<Unit> {
            supplementUseCase.addItem(itemRegisterDto.toCommand())
            return ResponseEntity.ok().build()
        }

        @GetMapping("/supplements")
        fun getSupplements(): ResponseEntity<List<Supplement>> {
            val result = supplementUseCase.getSupplements()
            return ResponseEntity.ok(result)
        }
    }
