package az.ingress.ingressautoservice.entity.addetails;

import az.ingress.ingressautoservice.entity.helper.SimpleProperty;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@SuperBuilder
@Getter
@Entity
public class Currency extends SimpleProperty {
}