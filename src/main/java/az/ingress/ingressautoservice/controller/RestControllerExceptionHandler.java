package az.ingress.ingressautoservice.controller;

import az.ingress.ingressautoservice.dto.BaseRestApiResponseDto;
import az.ingress.ingressautoservice.exception.BadRequestException;
import az.ingress.ingressautoservice.exception.NotFoundException;
import az.ingress.ingressautoservice.util.RestApiResponseBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

import static az.ingress.ingressautoservice.constant.error.GeneralError.INVALID_REQUEST_BODY;
import static az.ingress.ingressautoservice.constant.error.GeneralError.MISSING_REQUEST_HEADER;

@RestControllerAdvice
public class RestControllerExceptionHandler implements RestApiResponseBuilder {
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public BaseRestApiResponseDto<?> handle(NotFoundException e) {
        return generateErrorResponse(e);
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseRestApiResponseDto<?> handle(BadRequestException e) {
        return generateErrorResponse(e);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseRestApiResponseDto<?> handle(MethodArgumentNotValidException ex) {
        List<BaseRestApiResponseDto.Error> validationErrors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> BaseRestApiResponseDto.Error.of(
                        INVALID_REQUEST_BODY.getCode(),
                        error.getDefaultMessage(),
                        error.getField()))
                .collect(Collectors.toList());
        return BaseRestApiResponseDto.ofErrors(validationErrors);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public BaseRestApiResponseDto<?> handle(HttpMessageNotReadableException ex) {
        return BaseRestApiResponseDto.ofErrors(BaseRestApiResponseDto.Error.of(INVALID_REQUEST_BODY.getCode(), INVALID_REQUEST_BODY.getMessageFormat()));
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    public BaseRestApiResponseDto<?> handle(MissingRequestHeaderException ex) {
        return BaseRestApiResponseDto.ofErrors(BaseRestApiResponseDto.Error.of(MISSING_REQUEST_HEADER.getCode(), MISSING_REQUEST_HEADER.buildMessage(ex.getHeaderName())));
    }
}
