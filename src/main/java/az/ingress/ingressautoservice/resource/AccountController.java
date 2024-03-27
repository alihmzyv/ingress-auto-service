package az.ingress.ingressautoservice.resource;

import az.ingress.ingressautoservice.dto.BaseRestApiResponseDto;
import az.ingress.ingressautoservice.dto.account.AccountResponseDto;
import az.ingress.ingressautoservice.dto.account.CreateAccountRequestDto;
import az.ingress.ingressautoservice.dto.ad.AdShortResponseDto;
import az.ingress.ingressautoservice.dto.ad.FindAdsRequestParams;
import az.ingress.ingressautoservice.entity.Ad_;
import az.ingress.ingressautoservice.service.AccountService;
import az.ingress.ingressautoservice.service.AdService;
import az.ingress.ingressautoservice.util.RestApiResponseBuilder;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static az.ingress.ingressautoservice.constant.ApiConstants.REQ_HEADER_USER_ID_NAME;

@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("/accounts")
@RestController
public class AccountController implements RestApiResponseBuilder {
    AccountService accountService;
    AdService adService;

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping
    public void create(@RequestBody @Valid CreateAccountRequestDto request) {
        accountService.create(request);
    }

    //TODO: security not implemented yet
    @GetMapping
    public BaseRestApiResponseDto<AccountResponseDto> getById(@RequestHeader(REQ_HEADER_USER_ID_NAME) Long id) {
        return generateResponse(accountService.getById(id));
    }

    //TODO: security not implemented yet
    @GetMapping("/{id}/ads")
    public BaseRestApiResponseDto<List<AdShortResponseDto>> getAds(@PathVariable Long id,
                                                                   @ParameterObject @PageableDefault(
                                                                           size = 24,
                                                                           sort = Ad_.CREATED_AT,
                                                                           direction = Sort.Direction.DESC)
                                                                   Pageable pageable) {
        accountService.ensureExistsById(id);
        return generateResponse(adService.find(id, pageable),
                adService.getTotalNumOfPages(id, pageable));
    }
}
