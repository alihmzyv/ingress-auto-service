package az.ingress.ingressautoservice.service.impl;

import az.ingress.ingressautoservice.dto.account.AccountResponseDto;
import az.ingress.ingressautoservice.dto.account.CreateAccountRequestDto;
import az.ingress.ingressautoservice.entity.Account;
import az.ingress.ingressautoservice.exception.BadRequestException;
import az.ingress.ingressautoservice.mapper.AccountMapper;
import az.ingress.ingressautoservice.repository.AccountRepository;
import az.ingress.ingressautoservice.service.AccountService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import static az.ingress.ingressautoservice.constant.AccountError.DUPLICATE_CREDENTIALS;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {
    AccountRepository accountRepository;
    AccountMapper accountMapper;

    @Override
    public AccountResponseDto create(CreateAccountRequestDto request) {
        ensureAccountDoesNotExistByPhoneNumberOrEmail(request);
        Account account = accountMapper.toEntity(request);
        return accountMapper.toResponse(accountRepository.save(account));
    }

    private void ensureAccountDoesNotExistByPhoneNumberOrEmail(CreateAccountRequestDto request) {
        boolean existsByPhoneNumberOrEmail = accountRepository.existsByPhoneNumberOrEmailAddress(
                request.getEmailAddress(), request.getPhoneNumber());
        if (existsByPhoneNumberOrEmail) {
            throw BadRequestException.of(DUPLICATE_CREDENTIALS.getCode(), DUPLICATE_CREDENTIALS.buildMessage());
        }
    }
}
