package az.ingress.ingressautoservice.constant;

import lombok.Getter;

@Getter
public enum GeneralError {
    INVALID_REQUEST("AS:4");

    private final String code;

    GeneralError(String code) {
        this.code = code;
    }
}
