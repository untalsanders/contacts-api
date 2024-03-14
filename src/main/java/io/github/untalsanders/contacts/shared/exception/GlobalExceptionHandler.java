package io.github.untalsanders.contacts.shared.exception;

import io.github.untalsanders.contacts.shared.domain.ErrorResponse;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Objects;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @Value("${avoristech.trace:false}")
    private boolean printStackTrace;

    private ResponseEntity<Object> buildErrorResponse(Exception exception, String message, HttpStatus status, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(status.value(), message);
        if (printStackTrace && isTraceOn(request)) {
            errorResponse.setStackTrace(ExceptionUtils.getStackTrace(exception));
        }
        return ResponseEntity.status(status).body(errorResponse);
    }

    private ResponseEntity<Object> buildErrorResponse(Exception exception, HttpStatus status, WebRequest request) {
        return buildErrorResponse(exception, exception.getMessage(), status, request);
    }

    private static final String TRACE = "trace";

    private boolean isTraceOn(WebRequest request) {
        String[] value = request.getParameterValues(TRACE);
        return Objects.nonNull(value) && value.length > 0 && value[0].contentEquals("true");
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> handleAllUncaughtException(Exception exception, WebRequest request) {
        LOGGER.error("Unknown error occurred", exception);
        return buildErrorResponse(exception, "Unknown error occurred", HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler(NoSuchElementFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleNoSuchElementFoundException(NoSuchElementFoundException exception, WebRequest request) {
        LOGGER.error("Failed to find the requested element", exception);
        return buildErrorResponse(exception, HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(SuchElementAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleSuchElementAlreadyExistsException(SuchElementAlreadyExistsException exception, WebRequest request) {
        LOGGER.error("Item already exists", exception);
        return buildErrorResponse(exception, HttpStatus.NOT_FOUND, request);
    }
}
