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

    @ExceptionHandler({ValidationException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleValidationException(ValidationException ex) {
        log.debug("Validation error: {}", ex.getMessage());
        return new ErrorResponse("ERROR", "Validation doesn't pass");
    }

    @ExceptionHandler({MethodArgumentNotValidException.class, WrongRequirementsException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleArgumentException(MethodArgumentNotValidException ex) {
        log.debug("Argument(s) validation error: {}", ex.getMessage());
        return new ErrorResponse("ERROR", "Wrong argument(s)");
    }

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleGeneralException(Exception ex) {
        log.debug("Unexpected error: {}", ex.getMessage());
        return new ErrorResponse("ERROR", "Unexpected error");
    }

}
