package az.ingress.ingressautoservice.mapper;

import az.ingress.ingressautoservice.dto.account.AccountResponseDto;
import az.ingress.ingressautoservice.dto.account.CreateAccountRequestDto;
import az.ingress.ingressautoservice.entity.Account;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Component
public class AccountMapper {
    ModelMapper modelMapper;
    PasswordEncoder passwordEncoder;

    public Account toEntity(CreateAccountRequestDto createAccountRequestDto) {
        Account account = modelMapper.map(createAccountRequestDto, Account.class);
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        return account;
    }

    public AccountResponseDto toResponse(Account account) {
        return modelMapper.map(account, AccountResponseDto.class);
    }
}
