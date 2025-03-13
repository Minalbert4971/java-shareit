package ru.practicum.shareit.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler({NotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotFoundException(NotFoundException ex) {
        log.debug("Not found error: {}", ex.getMessage());
        return new ErrorResponse("ERROR", "Not found");
    }

    @ExceptionHandler({MethodArgumentNotValidException.class, WrongRequirementsException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleArgumentException(MethodArgumentNotValidException ex) {
        log.debug("Argument(s) validation error: {}", ex.getMessage());
        return new ErrorResponse("ERROR", "Wrong argument(s)");
    }

    @ExceptionHandler({AuthentificationException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorResponse handleArgumentException(AuthentificationException ex) {
        log.debug("Authentification error: {}", ex.getMessage());
        return new ErrorResponse("ERROR", "Authentification error");
    }

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleGeneralException(Exception ex) {
        log.debug("Unexpected error: {}", ex.getMessage());
        return new ErrorResponse("ERROR", "Unexpected error");
    }

}
