package az.ingress.ingressautoservice.service;

import az.ingress.ingressautoservice.dto.account.AccountResponseDto;
import az.ingress.ingressautoservice.dto.account.CreateAccountRequestDto;

public interface AccountService {
    void create(CreateAccountRequestDto request);

    AccountResponseDto getById(Long accountId);

    void ensureExistsById(Long id);
}
