package az.ingress.ingressautoservice.dto.ad;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class CreateAdRequestDto {
    @Valid
    @NotNull
    CarDetailsRequestDto carDetails;

    @NotNull
    Long priceVal;

    @NotNull
    Long priceCurrencyId;

    @NotNull
    String nameOfSeller;

    @NotNull
    Long cityId;

    @NotNull
    String emailAddressOfSeller;

    @JsonIgnore
    Long accountId;

    boolean eligibleForLoan;

    boolean eligibleForBarter;
}
