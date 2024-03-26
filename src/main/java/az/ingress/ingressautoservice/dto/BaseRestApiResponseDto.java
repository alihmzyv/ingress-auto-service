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

    public static <D> BaseRestApiResponseDto<D> of(D data, long totalNumOfPages) {
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
    @FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
    public static class Error {

        public static Error of(String code, String message) {
            return new Error(code, message);
        }

        public static Error of(String code, String message, String property) {
            return new Error(code, message, property);
        }

        String code;
        String message;
        String property;

        private Error(String code, String message) {
            this.code = code;
            this.message = message;
            this.property = null;
        }

        private Error(String code, String message, String property) {
            this.code = code;
            this.message = message;
            this.property = property;
        }
    }

    D data;
    List<Error> errors;
    long totalNumOfPages;

    private BaseRestApiResponseDto() {
    }
}
