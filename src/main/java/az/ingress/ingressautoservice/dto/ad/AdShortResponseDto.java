package az.ingress.ingressautoservice.dto.ad;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.Instant;

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
    BigDecimal capacityInLitres;
    Long mileage;
    String cityName;
    @JsonFormat(shape = JsonFormat.Shape.NUMBER_INT)
    Instant createdAt;
}
