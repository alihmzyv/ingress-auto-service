package az.ingress.ingressautoservice.entity;

import az.ingress.ingressautoservice.entity.addetails.City;
import az.ingress.ingressautoservice.entity.addetails.Currency;
import az.ingress.ingressautoservice.entity.helper.BaseEntity;
import az.ingress.ingressautoservice.entity.helper.CarDetails;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
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
@Entity
public class Ad extends BaseEntity {
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

    @Column(nullable = false)
    @Generated(event = EventType.INSERT)
    Instant createdAt;

    @Column(nullable = false, insertable = false)
    @Generated(event = EventType.INSERT)
    Instant updatedAt;
}