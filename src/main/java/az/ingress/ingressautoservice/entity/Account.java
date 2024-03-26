package az.ingress.ingressautoservice.entity;

import az.ingress.ingressautoservice.entity.helper.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.Objects;
import java.util.Set;

@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Account extends BaseEntity {

    @Size(max = 10)
    @NotNull
    String phoneNumber;

    @NotNull
    String password;

    String emailAddress;

    @OneToMany(mappedBy = Ad_.ACCOUNT)
    Set<Ad> ads;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Account account = (Account) o;
        return Objects.equals(phoneNumber, account.phoneNumber) && Objects.equals(emailAddress, account.emailAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), phoneNumber, emailAddress);
    }
}