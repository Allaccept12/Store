package com.example.store.common.error;


import com.example.store.account.exception.AccountNotFoundException;
import com.example.store.account.exception.EmailDuplicationException;
import com.example.store.account.exception.WrongPasswordException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(value = {AccountNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ErrorResponse handleAccountNotFoundException(AccountNotFoundException e) {
        final ErrorCode errorCode = ErrorCode.ACCOUNT_NOT_FOUND;
        return ErrorResponse.builder().message(errorCode.getValue()).build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        final List<ErrorResponse.FieldError> fieldErrors = getFieldErrors(e.getBindingResult());
        return ErrorResponse.builder()
                .message(ErrorCode.INPUT_VALUE_INVALID.getValue())
                .errors(fieldErrors)
                .build();
    }

    @ExceptionHandler(EmailDuplicationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorResponse handleConstraintViolationException(EmailDuplicationException e) {
        final ErrorCode errorCode = ErrorCode.EMAIL_DUPLICATION;
        return ErrorResponse.builder().message(errorCode.getValue()).build();
    }

    @ExceptionHandler(WrongPasswordException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorResponse handleWrongPasswordException(WrongPasswordException e) {
        final ErrorCode errorCode = ErrorCode.PASSWORD_WRONG;
        return ErrorResponse.builder().message(errorCode.getValue()).build();
    }

    private List<ErrorResponse.FieldError> getFieldErrors(BindingResult bindingResult) {
        final List<FieldError> errors = bindingResult.getFieldErrors();
        return errors.stream()
                .map(error -> ErrorResponse.FieldError.builder()
                        .reason(error.getDefaultMessage())
                        .field(error.getField())
                        .value((String) error.getRejectedValue())
                        .build())
                .collect(Collectors.toList());
    }
}
