package com.mrms.suppletrack.presentation

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
class HealthCheckController {
    @RequestMapping(path = ["/"], method = [RequestMethod.GET, RequestMethod.HEAD])
    fun healthCheck(): ResponseEntity<Map<String, String>> {
        return ResponseEntity.ok(mapOf("status" to "UP"))
    }
}
