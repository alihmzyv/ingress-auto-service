package az.ingress.ingressautoservice.repository;

import az.ingress.ingressautoservice.entity.Account;

import java.util.Optional;

public interface AccountRepository {
    Account save(Account account);

    boolean existsByPhoneNumberOrEmailAddress(String emailAddress, String phoneNumber);

    Optional<Account> findById(Long accountId);

    boolean existsById(Long id);
}
