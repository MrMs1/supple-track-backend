package com.mrms.suppletrack.presentation

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HealthCheckController {
    @GetMapping("/")
    fun healthCheck(): ResponseEntity<Map<String, String>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(mapOf("status" to "ok"))
    }
}
