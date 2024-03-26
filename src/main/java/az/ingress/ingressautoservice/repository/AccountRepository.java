package az.ingress.ingressautoservice.repository;

import az.ingress.ingressautoservice.entity.Account;

public interface AccountRepository {
    Account save(Account account);

    boolean existsByPhoneNumberOrEmailAddress(String emailAddress, String phoneNumber);
}
