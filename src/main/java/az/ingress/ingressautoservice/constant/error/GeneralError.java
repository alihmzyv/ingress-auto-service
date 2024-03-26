package az.ingress.ingressautoservice.constant.error;

import lombok.Getter;

@Getter
public enum GeneralError {
    INVALID_REQUEST_BODY("AS:4", "The request body is not correct. Please check the doc."),
    MISSING_REQUEST_HEADER("AS:5", "The request header '%s' is missing");

    private final String code;
    private final String messageFormat;

    GeneralError(String code, String messageFormat) {
        this.code = code;
        this.messageFormat = messageFormat;
    }

    public String buildMessage(Object... args) {
        return String.format(messageFormat, args);
    }
}
