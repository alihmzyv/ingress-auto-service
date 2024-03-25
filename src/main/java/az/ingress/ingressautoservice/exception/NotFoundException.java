package az.ingress.ingressautoservice.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NotFoundException extends Exception {
    String code;

    public static NotFoundException of(String code, String message) {
        return new NotFoundException(code, message);
    }

    private NotFoundException(String code, String message) {
        super(message);
        this.code = code;
    }

}
