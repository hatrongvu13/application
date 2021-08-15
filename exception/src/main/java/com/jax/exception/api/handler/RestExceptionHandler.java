package com.jax.exception.api.handler;

import com.jax.exception.data.constant.exception.AuthorizationException;
import com.jax.exception.data.constant.exception.BadRequestException;
import com.jax.exception.data.response.ExceptionMessage;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.Nullable;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.*;

@RestControllerAdvice()
public class RestExceptionHandler extends RuntimeException{
    private static final long serialVersionUID = 1L;

    private static final String MESSAGE_COMMAND_REGEX = "^\\{(.*?)}$";

    private final MessageSource messageSource;

    public RestExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    private String messageResolver(String message, @Nullable Object[] args, Locale locale) {
        if (!message.matches(MESSAGE_COMMAND_REGEX)) return message;

        String resolved = message.substring(1, message.length() - 1);
        return messageSource.getMessage(resolved, args, locale);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ExceptionMessage handleArgumentNotValidException(MethodArgumentNotValidException ex, Locale locale) {
        Map<String, String> errors = new HashMap<>();

        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            String fieldName = error.getField();
            String errorMessage = messageResolver(error.getDefaultMessage(), null, locale);
            errors.put(fieldName, errorMessage);
        }

        return new ExceptionMessage(new Date(), HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(),
                messageResolver("{invalid.input}", null, locale), errors);
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ExceptionMessage handleBadRequestException(BadRequestException ex, Locale locale) {
        return new ExceptionMessage(new Date(), HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(), messageResolver(ex.getMessage(), null, locale));
    }

    @ExceptionHandler(MultipartException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ExceptionMessage handleMultipartException(MultipartException ex, Locale locale) {
        return new ExceptionMessage(new Date(), HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(), messageResolver(ex.getCause().getMessage(), null, locale));
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ExceptionMessage handleMaxUploadSizeExceededException(MaxUploadSizeExceededException ex, Locale locale) {
        return new ExceptionMessage(new Date(), HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(), messageResolver(ex.getCause().getCause().getMessage(), null, locale));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ExceptionMessage handleConstraintViolationException(ConstraintViolationException ex, Locale locale) {
        String error = ex.getMessage();
        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
        if (!constraintViolations.isEmpty()){
            ConstraintViolation<?> constraintViolation = constraintViolations.stream().findFirst().orElse(null);
            error = constraintViolation != null ? constraintViolation.getMessage() : error ;
        }

        return new ExceptionMessage(new Date(), HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(), messageResolver(error, null, locale));
    }

    @ExceptionHandler(AuthorizationException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ExceptionMessage handleAuthorizationException(AuthorizationException ex, Locale locale) {
        return new ExceptionMessage(new Date(), HttpStatus.UNAUTHORIZED.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(), messageResolver(ex.getMessage(), null, locale));
    }

    @ExceptionHandler(ResponseStatusException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ExceptionMessage handleResponseException(ResponseStatusException ex) {
        return new ExceptionMessage(new Date(), ex.getStatus().value(),
                ex.getStatus().getReasonPhrase(), ex.getReason());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ExceptionMessage handleRuntimeException(RuntimeException ex) {
        return new ExceptionMessage(new Date(), HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), null);
    }


    @ExceptionHandler({
            BindException.class
    })
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ExceptionMessage handleBindException(BindException e) {
        BindingResult result = e.getBindingResult();
        List<ObjectError> allErrors = result.getAllErrors();
        String messageError = allErrors.get(0).getDefaultMessage();
        if (Objects.isNull(messageError)) {
            messageError = "The input is not valid";
        }
        return new ExceptionMessage(new Date(), HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(), messageError);
    }


    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ExceptionMessage handleException(Exception ex) {
        return new ExceptionMessage(new Date(), HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), ex.getMessage());
    }
}
