package az.ingress.ingressautoservice.entity.cardetails;

import az.ingress.ingressautoservice.entity.helper.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;


@SuperBuilder
@NoArgsConstructor
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class EngineCapacity extends BaseEntity {

    @NotNull
    @Column(nullable = false, unique = true)
    Short capacityInSm3;

}