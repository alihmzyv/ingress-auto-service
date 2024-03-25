package az.ingress.ingressautoservice.service;

import az.ingress.ingressautoservice.dto.ad.AdShortResponseDto;
import az.ingress.ingressautoservice.dto.ad.FindAdsRequestParams;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdService {
    List<AdShortResponseDto> find(FindAdsRequestParams requestParams, Pageable pageable);
}
