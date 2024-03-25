package az.ingress.ingressautoservice.entity;

import az.ingress.ingressautoservice.entity.adsdetails.City;
import az.ingress.ingressautoservice.entity.adsdetails.Currency;
import az.ingress.ingressautoservice.entity.helper.CarDetails;
import az.ingress.ingressautoservice.entity.helper.RootIdentifiable;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
//TODO: rename
public class Ads extends RootIdentifiable {
    /**
     * java.lang.Long, java.lang.Long, java.lang.String, java.lang.String, java.lang.String, java.lang.Short, java.lang.Short, java.lang.Long, java.lang.String, java.time.Instant
     */
    CarDetails carDetails;

    @NotNull
    Long priceVal;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    Currency priceCurrency;

    boolean eligibleForLoan;

    boolean eligibleForBarter;

    @NotNull
    String nameOfSeller;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    City city;

    @NotNull
    String emailAddressOfSeller;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    Account account;

    //FIXME

//    @Column(insertable = false, nullable = false)
//    Instant createdAt;
//
//    @Column(insertable = false, nullable = false)
//    Instant updatedAt;
}