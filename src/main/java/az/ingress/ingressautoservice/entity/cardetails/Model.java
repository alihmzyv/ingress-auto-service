package az.ingress.ingressautoservice.entity.cardetails;

import az.ingress.ingressautoservice.entity.helper.SimpleProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Model extends SimpleProperty {
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    Brand brand;
}