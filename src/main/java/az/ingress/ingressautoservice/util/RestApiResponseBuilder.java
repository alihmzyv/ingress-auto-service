package az.ingress.ingressautoservice.util;

import az.ingress.ingressautoservice.dto.BaseRestApiResponseDto;
import az.ingress.ingressautoservice.exception.ApplicationException;

public interface RestApiResponseBuilder {
    default <D> BaseRestApiResponseDto<D> generateResponse(D data) {
        return BaseRestApiResponseDto.of(data);
    }
    default BaseRestApiResponseDto<?> generateErrorResponse(ApplicationException e) {
        return BaseRestApiResponseDto.ofErrors(BaseRestApiResponseDto.Error.of(e.getCode(), e.getMessage()));
    }
}
