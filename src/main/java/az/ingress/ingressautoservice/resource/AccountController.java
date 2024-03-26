package az.ingress.ingressautoservice.resource;

import az.ingress.ingressautoservice.dto.BaseRestApiResponseDto;
import az.ingress.ingressautoservice.dto.account.AccountResponseDto;
import az.ingress.ingressautoservice.dto.account.CreateAccountRequestDto;
import az.ingress.ingressautoservice.service.AccountService;
import az.ingress.ingressautoservice.util.RestApiResponseBuilder;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("/accounts")
@RestController
public class AccountController implements RestApiResponseBuilder {
    AccountService accountService;

    @PostMapping
    public BaseRestApiResponseDto<AccountResponseDto> create(@Valid @RequestBody CreateAccountRequestDto request) {
        return generateResponse(accountService.create(request));
    }
}
