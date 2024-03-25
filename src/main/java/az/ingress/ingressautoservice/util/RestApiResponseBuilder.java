package az.ingress.ingressautoservice.util;

import az.ingress.ingressautoservice.dto.BaseRestApiResponseDto;

public interface RestApiResponseBuilder {
    default <D> BaseRestApiResponseDto<D> generateResponse(D data) {
        return BaseRestApiResponseDto.of(data);
    }
}
