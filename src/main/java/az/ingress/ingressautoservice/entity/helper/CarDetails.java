package az.ingress.ingressautoservice.entity.helper;

import az.ingress.ingressautoservice.entity.cardetails.*;
import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Embeddable
public class CarDetails {
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    Model model;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    FuelType fuelType;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    DriveUnitType driveUnitType;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    BodyStyle bodyStyle;

    @NotNull
    Long mileage;

    @NotNull
    Short yearOfCar;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    Color color;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    EngineCapacity engineCapacity;

    @NotNull
    Long enginePowerInHp;

    @ManyToOne(fetch = FetchType.LAZY)
    OwnershipHistory ownershipHistory;

    @ManyToOne(fetch = FetchType.LAZY)
    MarketVersion marketVersion;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    TransmissionType transmissionType;

    boolean damaged;

    boolean painted;

    boolean crashedOrForSpareParts;

    Short numOfSeats;

    String vinCode;

    String extraInfo;

}
