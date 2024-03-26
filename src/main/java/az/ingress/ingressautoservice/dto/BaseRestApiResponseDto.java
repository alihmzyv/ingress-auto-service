package az.ingress.ingressautoservice.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
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

    public static <D> BaseRestApiResponseDto<D> ofErrors(List<Error> errors) {
        BaseRestApiResponseDto<D> dto = new BaseRestApiResponseDto<>();
        dto.setErrors(errors);
        return dto;
    }

    @Data
    @NoArgsConstructor
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


    @EqualsAndHashCode(callSuper = true)
    @FieldDefaults(level = AccessLevel.PRIVATE)
    @Data
    public static class ValidationError extends Error {
        public static ValidationError of(String code, String property, String message) {
            return new ValidationError(code, property, message);
        }

        String property;

        public ValidationError(String code, String property, String message) {
            super(code, message);
            this.property = property;
        }
    }

    D data;
    List<Error> errors;
    int totalNumOfPages;

    private BaseRestApiResponseDto() {
    }
}
