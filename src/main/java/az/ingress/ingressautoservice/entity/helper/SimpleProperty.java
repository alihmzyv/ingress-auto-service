package az.ingress.ingressautoservice.entity.helper;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;


@NoArgsConstructor
@SuperBuilder
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@MappedSuperclass
public class SimpleProperty extends BaseEntity {
    @NotNull
    @Column(nullable = false, unique = true)
    String name;
}
