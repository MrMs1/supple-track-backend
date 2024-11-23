package com.mrms.suppletrack.presentation

import com.mrms.suppletrack.domain.supplement.Item
import com.mrms.suppletrack.domain.supplement.Supplement
import com.mrms.suppletrack.presentation.convertor.toCommand
import com.mrms.suppletrack.presentation.dto.ItemRegisterDto
import com.mrms.suppletrack.presentation.dto.RemoveSupplementRequest
import com.mrms.suppletrack.presentation.dto.SupplementRegisterDto
import com.mrms.suppletrack.presentation.dto.UpdateItemRequest
import com.mrms.suppletrack.presentation.dto.UpdateSupplementRequest
import com.mrms.suppletrack.usecase.SupplementUseCase
import com.mrms.suppletrack.usecase.dto.SupplementRemoveCommand
import com.mrms.suppletrack.usecase.dto.UpdateSupplementCommand
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

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

        @DeleteMapping("supplement")
        fun removeSupplement(
            @RequestBody removeSupplementRequest: RemoveSupplementRequest,
        ): ResponseEntity<Unit> {
            supplementUseCase.removeSupplement(SupplementRemoveCommand(removeSupplementRequest.name))
            return ResponseEntity.noContent().build()
        }

        @DeleteMapping("/item/{id}")
        fun removeItem(
            @PathVariable id: UUID,
        ): ResponseEntity<Unit> {
            supplementUseCase.removeItem(id)
            return ResponseEntity.noContent().build()
        }

        @PatchMapping("/supplement")
        fun updateSupplement(
            @RequestBody updateSupplementRequest: UpdateSupplementRequest,
        ): ResponseEntity<String> {
            val result = supplementUseCase.updateSupplement(UpdateSupplementCommand(updateSupplementRequest.name))
            return ResponseEntity.ok(result.name)
        }

        @PatchMapping("/item/{id}")
        fun updateItem(
            @PathVariable id: UUID,
            @RequestBody updateItemRequest: UpdateItemRequest,
        ): ResponseEntity<Item> {
            val result = supplementUseCase.updateItem(updateItemRequest.toCommand(id))
            return ResponseEntity.ok(result)
        }
    }
