package az.ingress.ingressautoservice.repository.impl;

import az.ingress.ingressautoservice.entity.Account;
import az.ingress.ingressautoservice.repository.AccountRepository;
import jakarta.persistence.PersistenceUnit;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
class AccountRepositoryImplTest {
    @PersistenceUnit
    private SessionFactory sessionFactory;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @AfterEach
    void clean() {
        sessionFactory.inTransaction(session -> {
            session.createNativeMutationQuery("delete from ad").executeUpdate();
            session.createNativeMutationQuery("delete from account").executeUpdate();
        });
    }

    @Test
    void whenAccountIsBeingSaved() {
        String phoneNumber = "0559994650";
        String password = "Ali1234$";
        Account account = Account.builder()
                .phoneNumber(phoneNumber)
                .password(passwordEncoder.encode(password))
                .build();
        Account accountSaved = accountRepository.save(account);
        assertNotNull(accountSaved.getId());
        sessionFactory.inSession(session -> {
            assertEquals(accountSaved, session.find(Account.class, accountSaved.getId()));
        });
    }

    @Test
    void whenAccountAlreadyExistsByPhoneNumber() {
        String phoneNumber = "0559994650";
        String password = "Ali1234$";
        Account account = Account.builder()
                .phoneNumber(phoneNumber)
                .password(passwordEncoder.encode(password))
                .build();
        Account accountSaved = accountRepository.save(account);

        assertTrue(accountRepository.existsByPhoneNumberOrEmailAddress(null, phoneNumber));
    }

    @Test
    void whenAccountAlreadyExistsByEmail() {
        String phoneNumber = "0559994650";
        String emailAddress = "alihmzyv@gmail.com";
        String password = "Ali1234$";
        Account account = Account.builder()
                .phoneNumber(phoneNumber)
                .emailAddress(emailAddress)
                .password(passwordEncoder.encode(password))
                .build();
        accountRepository.save(account);
        assertTrue(accountRepository.existsByPhoneNumberOrEmailAddress(emailAddress, null));
    }

    @Test
    void whenAccountDoesNotExistsByEmail() {
        String phoneNumber = "0559994650";
        String emailAddress = "alihmzyv@gmail.com";
        assertFalse(accountRepository.existsByPhoneNumberOrEmailAddress(emailAddress, phoneNumber));
    }
}