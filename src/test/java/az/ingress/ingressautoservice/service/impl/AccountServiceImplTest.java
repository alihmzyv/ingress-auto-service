package az.ingress.ingressautoservice.service.impl;

import az.ingress.ingressautoservice.dto.account.CreateAccountRequestDto;
import az.ingress.ingressautoservice.exception.BadRequestException;
import az.ingress.ingressautoservice.repository.AccountRepository;
import az.ingress.ingressautoservice.service.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureTestDatabase
class AccountServiceImplTest {
    @MockBean
    private AccountRepository accountRepository;

    @Autowired
    private AccountService accountService;

    @Test
    void whenAccountAlreadyExistsByPhoneNumber() {
        String emailAddress = "alhmzyv@gmail.com";
        String phoneNumber = "0559994650";
        when(accountRepository.existsByPhoneNumberOrEmailAddress(emailAddress, phoneNumber))
                .thenReturn(true);
        CreateAccountRequestDto request = CreateAccountRequestDto.builder()
                .emailAddress(emailAddress)
                .phoneNumber(phoneNumber)
                .build();

        assertThrowsExactly(BadRequestException.class, () -> accountService.create(request),
                "Account already exists with email or phone number");
    }
}