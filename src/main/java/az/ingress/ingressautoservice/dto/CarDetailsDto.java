package az.ingress.ingressautoservice.dto;

import az.ingress.ingressautoservice.constant.MileageType;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@FieldNameConstants
public class CarDetailsDto {
    String brandName;
    String modelName;
    String fuelTypeName;
    String driveUnitTypeName;
    String bodyStyleName;
    Long mileage;
    Short yearOfCar;
    String colorName;
    BigDecimal engineCapacityInLitres;
    Long enginePowerInHp;
    String ownershipHistoryName;
    String marketVersionName;
    String transmissionTypeName;
    boolean damaged;
    boolean painted;
    boolean crashedOrForSpareParts;
    Short numOfSeats;
    String extraInfo;
    MileageType mileageType;
}
