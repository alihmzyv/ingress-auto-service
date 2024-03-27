package az.ingress.ingressautoservice.constant.error;

import lombok.Getter;

@Getter
public enum PropertyError {
    PROPERTY_NOT_FOUND("AS:1", "Property not found: %s");

    private final String code;
    private final String messageFormat;

    PropertyError(String code, String messageFormat) {
        this.code = code;
        this.messageFormat = messageFormat;
    }

    public String buildMessage(Object... args) {
        return String.format(messageFormat, args);
    }
}
