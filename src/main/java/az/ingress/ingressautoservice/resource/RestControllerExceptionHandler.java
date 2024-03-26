package az.ingress.ingressautoservice.resource;

import az.ingress.ingressautoservice.constant.GeneralError;
import az.ingress.ingressautoservice.dto.BaseRestApiResponseDto;
import az.ingress.ingressautoservice.exception.BadRequestException;
import az.ingress.ingressautoservice.exception.NotFoundException;
import az.ingress.ingressautoservice.util.RestApiResponseBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

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
    public final BaseRestApiResponseDto<?> handle(MethodArgumentNotValidException ex) {
        List<BaseRestApiResponseDto.Error> validationErrors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> BaseRestApiResponseDto.ValidationError.of(
                        GeneralError.INVALID_REQUEST.getCode(),
                        error.getField(),
                        error.getDefaultMessage()))
                .collect(Collectors.toList());
        return BaseRestApiResponseDto.ofErrors(validationErrors);
    }
}
