package az.ingress.ingressautoservice.dto.ad;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@FieldNameConstants
@Data
public class AdResponseDto {
    private Long id;
    private CarDetailsDto carDetails;
    private Long priceVal;
    private String priceCurrencyName;
    private boolean eligibleForLoan;
    private boolean eligibleForBarter;
    private String nameOfSeller;
    private String cityName;
    private String accountPhoneNumber;
}
