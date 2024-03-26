package az.ingress.ingressautoservice.resource;

import az.ingress.ingressautoservice.dto.BaseRestApiResponseDto;
import az.ingress.ingressautoservice.dto.account.AccountResponseDto;
import az.ingress.ingressautoservice.dto.account.CreateAccountRequestDto;
import az.ingress.ingressautoservice.dto.ad.AdShortResponseDto;
import az.ingress.ingressautoservice.dto.ad.CreateAdRequestDto;
import az.ingress.ingressautoservice.dto.ad.FindAdsRequestParams;
import az.ingress.ingressautoservice.entity.Ad_;
import az.ingress.ingressautoservice.service.AccountService;
import az.ingress.ingressautoservice.service.AdService;
import az.ingress.ingressautoservice.util.RestApiResponseBuilder;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("/accounts")
@RestController
public class AccountController implements RestApiResponseBuilder {
    AccountService accountService;
    AdService adService;

    @PostMapping
    public BaseRestApiResponseDto<AccountResponseDto> create(@RequestBody @Valid CreateAccountRequestDto request) {
        return generateResponse(accountService.create(request));
    }

    //TODO: security not implemented yet
    @GetMapping("/{id}")
    public BaseRestApiResponseDto<AccountResponseDto> getById(@PathVariable Long id) {
        return generateResponse(accountService.getById(id));
    }

    //TODO: security not implemented yet
    @GetMapping("/{id}/ads")
    public BaseRestApiResponseDto<List<AdShortResponseDto>> getAds(@PathVariable Long id,
                                                                   @PageableDefault(
                                                                           size = 24,
                                                                           sort = Ad_.CREATED_AT,
                                                                           direction = Sort.Direction.DESC)
                                                                   Pageable pageable) {
        accountService.ensureExistsById(id);
        FindAdsRequestParams requestParams = FindAdsRequestParams.builder()
                .accountId(id)
                .build();
        return generateResponse(adService.find(requestParams, pageable));
    }

    //TODO: security not implemented yet
    @PostMapping("/{id}/ads")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void createAd(@PathVariable("id") Long accountId,
                         @RequestBody @Valid CreateAdRequestDto request) {
        adService.createAd(accountId, request);
    }
}
