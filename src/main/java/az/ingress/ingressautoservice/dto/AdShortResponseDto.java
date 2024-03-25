package az.ingress.ingressautoservice.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AdShortResponseDto {
    Long id;
    Long price;
    String currencyName;
    String brandName;
    String modelName;
    Short year;
    Short capacityInSm3;
    Long mileage;
    String cityName;
//    Instant createdAt;
}
