package az.ingress.ingressautoservice.exception;

public class BadRequestException extends ApplicationException {
    public static BadRequestException of(String code, String message) {
        return new BadRequestException(code, message);
    }

    private BadRequestException(String code, String message) {
        super(code, message);
    }
}
