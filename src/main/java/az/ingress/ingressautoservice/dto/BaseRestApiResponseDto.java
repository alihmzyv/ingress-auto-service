package az.ingress.ingressautoservice.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class BaseRestApiResponseDto<D> {
    public static <D> BaseRestApiResponseDto<D> of(D data) {
        BaseRestApiResponseDto<D> dto = new BaseRestApiResponseDto<>();
        dto.setData(data);
        return dto;
    }

    public static <D> BaseRestApiResponseDto<D> of(D data, int totalNumOfPages) {
        BaseRestApiResponseDto<D> dto = new BaseRestApiResponseDto<>();
        dto.setData(data);
        dto.setTotalNumOfPages(totalNumOfPages);
        return dto;
    }

    public static <D> BaseRestApiResponseDto<D> ofErrors(Error... errors) {
        BaseRestApiResponseDto<D> dto = new BaseRestApiResponseDto<>();
        Optional.ofNullable(errors)
                .map(arr -> Arrays.stream(arr).toList())
                .ifPresent(dto::setErrors);
        return dto;
    }

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class Error {
        public static Error of(String code, String message) {
            return new Error(code, message);
        }

        String code;
        String message;

        private Error(String code, String message) {
            this.code = code;
            this.message = message;
        }
    }

    D data;
    List<Error> errors;
    int totalNumOfPages;

    private BaseRestApiResponseDto() {
    }
}
