package az.ingress.ingressautoservice.dto.account;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class AccountResponseDto {
    String phoneNumber;
    String emailAddress;
}
