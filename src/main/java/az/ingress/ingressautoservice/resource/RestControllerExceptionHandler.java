package az.ingress.ingressautoservice.resource;

import az.ingress.ingressautoservice.dto.BaseRestApiResponseDto;
import az.ingress.ingressautoservice.exception.NotFoundException;
import az.ingress.ingressautoservice.util.RestApiResponseBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestControllerExceptionHandler implements RestApiResponseBuilder {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<BaseRestApiResponseDto<?>> handle(NotFoundException e) {
        BaseRestApiResponseDto<?> responseBody = generateErrorResponse(e);
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(responseBody);
    }
}
