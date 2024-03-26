package az.ingress.ingressautoservice.resource;

import az.ingress.ingressautoservice.dto.BaseRestApiResponseDto;
import az.ingress.ingressautoservice.dto.ad.AdResponseDto;
import az.ingress.ingressautoservice.dto.ad.AdShortResponseDto;
import az.ingress.ingressautoservice.dto.ad.FindAdsRequestParams;
import az.ingress.ingressautoservice.entity.Ad_;
import az.ingress.ingressautoservice.service.AdService;
import az.ingress.ingressautoservice.util.RestApiResponseBuilder;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("/ads")
@RestController
public class AdsController implements RestApiResponseBuilder {
    AdService adService;

    @GetMapping
    public BaseRestApiResponseDto<List<AdShortResponseDto>> getAds(FindAdsRequestParams findAdsRequestParams,
                                                                   @PageableDefault(
                                                                   size = 24,
                                                                   sort = Ad_.CREATED_AT,
                                                                   direction = Sort.Direction.DESC) Pageable pageable) {
        return generateResponse(adService.find(findAdsRequestParams, pageable));
    }

    @GetMapping("/{id}")
    public BaseRestApiResponseDto<AdResponseDto> findById(@PathVariable Long id) {
        return generateResponse(adService.findById(id));
    }
}
