package co.simplon.devbookapi.controllers.errors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@RestControllerAdvice
public class ControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    protected ResponseEntity<Object> handleDataIntegrityException(
            DataIntegrityViolationException exception,
            WebRequest request) {

        return handleExceptionInternal(exception, null, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception,
            HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        CustomErrors customErrors = new CustomErrors();
        List<FieldError> fieldErrors = exception.getFieldErrors();
        fieldErrors.forEach(error -> customErrors.addFieldError(error.getField(), error.getDefaultMessage()));
        System.out.println("fieldErrors" + fieldErrors);
        List<ObjectError> globalErrors = exception.getGlobalErrors();
        globalErrors.forEach(error -> customErrors.addGlobalError(error.getCode()));
        return handleExceptionInternal(exception, customErrors, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception exception, Object body, HttpHeaders headers,
            HttpStatusCode statusCode, WebRequest request) {

        return super.handleExceptionInternal(exception, body, headers, statusCode, request);
    }
}
