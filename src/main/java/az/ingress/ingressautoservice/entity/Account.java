package az.ingress.ingressautoservice.entity;

import az.ingress.ingressautoservice.entity.helper.RootIdentifiable;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@NoArgsConstructor
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Account extends RootIdentifiable {

    @Size(max = 10)
    @NotNull
    String phoneNumber;

    @NotNull
    String password;

    String emailAddress;

    @OneToMany(mappedBy = Ads_.ACCOUNT)
    Set<Ads> ads;
}