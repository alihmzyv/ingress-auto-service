package az.ingress.ingressautoservice.constant;

import lombok.Getter;

@Getter
public enum AdError {
    AD_NOT_FOUND("AS:1", "Ad not found by id: %s");

    private final String code;
    private final String messageFormat;

    AdError(String code, String messageFormat) {
        this.code = code;
        this.messageFormat = messageFormat;
    }

    public String buildMessage(Object... args) {
        return String.format(messageFormat, args);
    }
}
