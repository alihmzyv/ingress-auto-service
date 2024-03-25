package az.ingress.ingressautoservice.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class BaseRestApiResponseDto<D> {
    public static <D> BaseRestApiResponseDto<D> of(D data) {
        BaseRestApiResponseDto<D> dto = new BaseRestApiResponseDto<>();
        dto.setData(data);
        return dto;
    }

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    private static class Error {
        String code;
        String description;
    }

    D data;
    List<Error> errors;

    private BaseRestApiResponseDto() {
    }
}