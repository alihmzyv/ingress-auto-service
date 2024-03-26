package az.ingress.ingressautoservice.constant.error;

import lombok.Getter;

@Getter
public enum AccountError {
    DUPLICATE_CREDENTIALS("AS:2", "Account already exists by email or phone number"),
    ACCOUNT_NOT_FOUND_BY_ID("AS:3", "Account not found by id: %s");

    private final String code;
    private final String messageFormat;

    AccountError(String code, String messageFormat) {
        this.code = code;
        this.messageFormat = messageFormat;
    }

    AccountError(String code) {
        this.code = code;
        this.messageFormat = null;
    }

    public String buildMessage(Object... args) {
        return String.format(messageFormat, args);
    }
}
