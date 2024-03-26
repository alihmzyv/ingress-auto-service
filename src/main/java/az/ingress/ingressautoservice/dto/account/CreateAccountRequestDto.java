package az.ingress.ingressautoservice.dto.account;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class CreateAccountRequestDto {
    @NotEmpty
    @Pattern(regexp = "^0(?:55|77)\\d{7}$\n")
    String phoneNumber;
    @NotEmpty
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$")
    String password;
    @Email
    String emailAddress;
}
