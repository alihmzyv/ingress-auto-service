package az.ingress.ingressautoservice.service;

import az.ingress.ingressautoservice.dto.ad.AdResponseDto;
import az.ingress.ingressautoservice.dto.ad.AdShortResponseDto;
import az.ingress.ingressautoservice.dto.ad.CreateAdRequestDto;
import az.ingress.ingressautoservice.dto.ad.FindAdsRequestParams;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdService {
    List<AdShortResponseDto> find(FindAdsRequestParams requestParams, Pageable pageable);
    List<AdShortResponseDto> find(Long accountId, Pageable pageable);
    Long getTotalNumOfPages(FindAdsRequestParams requestParams, Pageable pageable);
    Long getTotalNumOfPages(Long accountId, Pageable pageable);
    AdResponseDto findById(Long id);
    void createAd(Long accountId, CreateAdRequestDto request);

    void deleteById(Long id);
}
