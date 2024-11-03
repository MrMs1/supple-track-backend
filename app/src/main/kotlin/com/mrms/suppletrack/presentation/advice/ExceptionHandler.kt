package com.mrms.suppletrack.presentation.advice

import com.mrms.suppletrack.domain.exception.DomainNotFoundException
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionHandler {
    companion object {
        private val logger = LoggerFactory.getLogger(ExceptionHandler::class.java)
    }

    @ExceptionHandler(DomainNotFoundException::class)
    fun handleDomainNotFoundException(e: DomainNotFoundException): ResponseEntity<String> {
        return ResponseEntity("target not found.", HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): ResponseEntity<String> {
        logger.error("internal server error.", e)
        return ResponseEntity("internal server error.", HttpStatus.INTERNAL_SERVER_ERROR)
    }
}
