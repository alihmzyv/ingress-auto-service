package az.ingress.ingressautoservice.dto.account;

import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class AccountResponseDto {
    String phoneNumber;
    String emailAddress;
}
