package com.mrms.suppletrack.presentation.advice

import com.mrms.suppletrack.domain.exception.DomainNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class ExceptionHandler {
    @ExceptionHandler(DomainNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleDomainNotFoundException(e: DomainNotFoundException): String {
        return "target not found."
    }

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleException(e: Exception): String {
        return "internal server error."
    }
}
