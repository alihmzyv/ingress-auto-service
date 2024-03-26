package az.ingress.ingressautoservice.service;

import az.ingress.ingressautoservice.dto.account.AccountResponseDto;
import az.ingress.ingressautoservice.dto.account.CreateAccountRequestDto;

public interface AccountService {
    AccountResponseDto create(CreateAccountRequestDto request);
}
