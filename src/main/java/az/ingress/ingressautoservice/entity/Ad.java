package az.ingress.ingressautoservice.entity;

import az.ingress.ingressautoservice.entity.addetails.City;
import az.ingress.ingressautoservice.entity.addetails.Currency;
import az.ingress.ingressautoservice.entity.helper.BaseEntity;
import az.ingress.ingressautoservice.entity.helper.CarDetails;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Generated;
import org.hibernate.generator.EventType;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
@Entity
public class Ad extends BaseEntity {
    CarDetails carDetails;

    @NotNull
    Long priceVal;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @ToString.Exclude
    Currency priceCurrency;

    boolean eligibleForLoan;

    boolean eligibleForBarter;

    @NotNull
    String nameOfSeller;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @ToString.Exclude
    City city;

    @NotNull
    String emailAddressOfSeller;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    Account account;

    @Generated(event = EventType.INSERT)
    Instant createdAt;

    @Generated(event = EventType.INSERT)
    Instant updatedAt;
}