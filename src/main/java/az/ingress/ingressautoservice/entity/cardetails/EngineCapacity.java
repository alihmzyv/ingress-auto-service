package az.ingress.ingressautoservice.entity.cardetails;

import az.ingress.ingressautoservice.entity.helper.RootIdentifiable;
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
public class EngineCapacity extends RootIdentifiable {

    @NotNull
    @Column(nullable = false, unique = true)
    Short capacityInSm3;

}