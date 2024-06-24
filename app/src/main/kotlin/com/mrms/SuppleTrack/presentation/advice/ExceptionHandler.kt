package com.mrms.suppletrack.presentation.advice

import com.mrms.suppletrack.domain.exception.DomainNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionHandler {
    @ExceptionHandler(DomainNotFoundException::class)
    fun handleDomainNotFoundException(e: DomainNotFoundException): ResponseEntity<String> {
        return ResponseEntity("target not found.", HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): ResponseEntity<String> {
        return ResponseEntity("internal server error.", HttpStatus.INTERNAL_SERVER_ERROR)
    }
}
