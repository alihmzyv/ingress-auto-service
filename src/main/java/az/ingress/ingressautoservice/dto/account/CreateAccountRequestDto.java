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
    @Pattern(regexp = "^0(55|77)\\d{7}$", message = "Phone number should have format of '0554443322'")
    String phoneNumber;
    @NotEmpty
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", message = "Password should contain only letters, digits and have min length of 8.")
    String password;
    @Email
    String emailAddress;
}
