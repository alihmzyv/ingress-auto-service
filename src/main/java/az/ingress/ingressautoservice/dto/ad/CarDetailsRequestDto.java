package az.ingress.ingressautoservice.dto.ad;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class CarDetailsRequestDto {
    @NotNull
    Long brandId;

    @NotNull
    Long modelId;

    @NotNull
    Long fuelTypeId;

    @NotNull
    Long driveUnitTypeId;

    @NotNull
    Long bodyStyleId;

    @NotNull
    Long mileage;

    @NotNull
    Short yearOfCar;

    @NotNull
    Long colorId;

    @NotNull
    Long engineCapacityId;

    @NotNull
    Long enginePowerInHp;

    @NotNull
    Long transmissionTypeId;

    Long ownershipHistoryId;

    Long marketVersionId;

    boolean damaged;

    boolean painted;

    boolean crashedOrForSpareParts;

    Short numOfSeats;

    String vinCode;

    String extraInfo;
}
