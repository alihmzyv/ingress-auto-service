package az.ingress.ingressautoservice.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ApplicationException extends RuntimeException {
    String code;

    protected ApplicationException(String code, String message) {
        super(message);
        this.code = code;
    }
}
