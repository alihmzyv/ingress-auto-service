package az.ingress.ingressautoservice.resource;

import az.ingress.ingressautoservice.dto.BaseRestApiResponseDto;
import az.ingress.ingressautoservice.dto.ad.AdResponseDto;
import az.ingress.ingressautoservice.dto.ad.AdShortResponseDto;
import az.ingress.ingressautoservice.dto.ad.CreateAdRequestDto;
import az.ingress.ingressautoservice.dto.ad.FindAdsRequestParams;
import az.ingress.ingressautoservice.entity.Ad_;
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
@RequestMapping("/ads")
@RestController
public class AdsController implements RestApiResponseBuilder {
    AdService adService;

    @GetMapping
    public BaseRestApiResponseDto<List<AdShortResponseDto>> getAds(@ParameterObject FindAdsRequestParams request,
                                                                   @ParameterObject
                                                                   @PageableDefault(
                                                                           size = 24,
                                                                           sort = Ad_.CREATED_AT,
                                                                           direction = Sort.Direction.DESC)
                                                                   Pageable pageable) {
        return generateResponse(
                adService.find(request, pageable),
                adService.getTotalNumOfPages(request, pageable));
    }

    @GetMapping("/{id}")
    public BaseRestApiResponseDto<AdResponseDto> findById(@PathVariable Long id) {
        return generateResponse(adService.findById(id));
    }


    //TODO: security not implemented yet
    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void create(@RequestHeader(REQ_HEADER_USER_ID_NAME) Long accountId,
                       @RequestBody @Valid CreateAdRequestDto request) {
        log.info("Create ad request: {}", request);
        adService.createAd(accountId, request);
    }

    //TODO: security not implemented yet
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        adService.deleteById(id);
    }
}
