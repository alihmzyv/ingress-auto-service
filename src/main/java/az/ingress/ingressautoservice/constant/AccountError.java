package az.ingress.ingressautoservice.constant;

import lombok.Getter;

@Getter
public enum AccountError {
    DUPLICATE_CREDENTIALS("AS:2", "Account already exists by email or phone number");

    private final String code;
    private final String messageFormat;

    AccountError(String code, String messageFormat) {
        this.code = code;
        this.messageFormat = messageFormat;
    }

    public String buildMessage(Object... args) {
        return String.format(messageFormat, args);
    }
}
