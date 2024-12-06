package io.github.untalsanders.contacts.shared.infrastructure.rest.advise;

import io.github.untalsanders.contacts.shared.domain.ApiResponse;
import io.github.untalsanders.contacts.shared.domain.ErrorMessage;
import io.github.untalsanders.contacts.shared.domain.ErrorResponse;
import io.github.untalsanders.contacts.shared.infrastructure.rest.exception.ResourceNotFoundException;
import io.github.untalsanders.contacts.shared.infrastructure.rest.exception.ResponseNotFoundException;
import io.github.untalsanders.contacts.shared.infrastructure.rest.exception.SuchElementAlreadyExistsException;
import io.github.untalsanders.contacts.shared.infrastructure.rest.util.ResponseUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
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

import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.Objects;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @Value("${contacts.trace:false}")
    private boolean printStackTrace;

    private ResponseEntity<Object> buildErrorResponse(
        Exception exception,
        String message,
        HttpStatus status,
        WebRequest request
    ) {
        ErrorResponse errorResponse = new ErrorResponse(String.valueOf(status.value()), message);
        if (printStackTrace && isTraceOn(request)) {
            errorResponse.setStackTrace(ExceptionUtils.getStackTrace(exception));
        }
        return ResponseEntity.status(status).body(errorResponse);
    }

    private ResponseEntity<Object> buildErrorResponse(Exception exception, WebRequest request) {
        return buildErrorResponse(exception, exception.getMessage(), HttpStatus.NOT_FOUND, request);
    }

    private static final String TRACE = "trace";

    private boolean isTraceOn(WebRequest request) {
        String[] value = request.getParameterValues(TRACE);
        return Objects.nonNull(value) && value.length > 0 && value[0].contentEquals("true");
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ApiResponse<Object>> handleGeneralException(
        Exception exception, HttpServletRequest request
    ) {
        log.error("===== ERROR ===== : {}", exception.getMessage());
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(ResponseUtil.error(
                Collections.singletonList(exception.getMessage()),
                "An unexpected error occurred",
                500,
                request.getRequestURI()
            ));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiResponse<Object>> handleResourceNotFoundException(
        ResourceNotFoundException exception, HttpServletRequest request
    ) {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(ResponseUtil.error(
                Collections.singletonList(exception.getMessage()),
                "Resource not found",
                404,
                request.getRequestURI()
            ));
    }

    @ExceptionHandler(ResponseNotFoundException.class)
    public ApiResponse<Object> handleResponseNotFoundException(
        ResponseNotFoundException exception,
        HttpServletRequest request
    ) {
        return ResponseUtil.error(
            Collections.singletonList(exception.getMessage()),
            "Response not found",
            204,
            request.getRequestURI()
        );
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> handleAllUncaughtException(RuntimeException exception, WebRequest request) {
        LOG.error("===== ERROR ===== : {}", exception.getMessage());
        return buildErrorResponse(
            exception,
            ErrorMessage.UNKNOWN.getMessage(),
            HttpStatus.INTERNAL_SERVER_ERROR,
            request
        );
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleNoSuchElementFoundException(
        NoSuchElementException exception,
        WebRequest request
    ) {
        LOG.error(ErrorMessage.FAILED_TO_FIND_REQUESTED_ELEMENT.getMessage(), exception);
        return buildErrorResponse(exception, request);
    }

    @ExceptionHandler(SuchElementAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleSuchElementAlreadyExistsException(
        SuchElementAlreadyExistsException exception,
        WebRequest request
    ) {
        LOG.error(ErrorMessage.ITEM_ALREADY_EXISTS.getMessage(), exception);
        return buildErrorResponse(exception, request);
    }
}
