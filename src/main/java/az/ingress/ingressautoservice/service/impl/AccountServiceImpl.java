package az.ingress.ingressautoservice.service.impl;

import az.ingress.ingressautoservice.dto.account.AccountResponseDto;
import az.ingress.ingressautoservice.dto.account.CreateAccountRequestDto;
import az.ingress.ingressautoservice.entity.Account;
import az.ingress.ingressautoservice.exception.BadRequestException;
import az.ingress.ingressautoservice.exception.NotFoundException;
import az.ingress.ingressautoservice.mapper.AccountMapper;
import az.ingress.ingressautoservice.repository.AccountRepository;
import az.ingress.ingressautoservice.service.AccountService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import static az.ingress.ingressautoservice.constant.AccountError.ACCOUNT_NOT_FOUND_BY_ID;
import static az.ingress.ingressautoservice.constant.AccountError.DUPLICATE_CREDENTIALS;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {
    AccountRepository accountRepository;
    AccountMapper accountMapper;

    @Override
    public AccountResponseDto create(CreateAccountRequestDto request) {
        ensureDoesNotExistByPhoneNumberOrEmail(request);
        Account account = accountMapper.toEntity(request);
        return accountMapper.toResponse(accountRepository.save(account));
    }

    @Override
    public AccountResponseDto getById(Long accountId) {
        return accountRepository.findById(accountId)
                .map(accountMapper::toResponse)
                .orElseThrow(() -> NotFoundException.of(ACCOUNT_NOT_FOUND_BY_ID.getCode(), ACCOUNT_NOT_FOUND_BY_ID.buildMessage(accountId)));
    }

    @Override
    public void ensureExistsById(Long id) {
        boolean existsById = accountRepository.existsById(id);
        if (!existsById) {
            throw NotFoundException.of(ACCOUNT_NOT_FOUND_BY_ID.getCode(), ACCOUNT_NOT_FOUND_BY_ID.buildMessage(id));
        }
    }

    private void ensureDoesNotExistByPhoneNumberOrEmail(CreateAccountRequestDto request) {
        boolean existsByPhoneNumberOrEmail = accountRepository.existsByPhoneNumberOrEmailAddress(
                request.getEmailAddress(), request.getPhoneNumber());
        if (existsByPhoneNumberOrEmail) {
            throw BadRequestException.of(DUPLICATE_CREDENTIALS.getCode(), DUPLICATE_CREDENTIALS.buildMessage());
        }
    }
}
