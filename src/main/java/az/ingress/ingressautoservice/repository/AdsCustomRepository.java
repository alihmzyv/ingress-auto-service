package az.ingress.ingressautoservice.repository;

import az.ingress.ingressautoservice.dto.AdShortResponseDto;
import az.ingress.ingressautoservice.dto.FindAdsRequestParams;
import az.ingress.ingressautoservice.entity.Ad;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdsCustomRepository {
    List<AdShortResponseDto> find(FindAdsRequestParams requestParams, Pageable pageable);
    Ad save(Ad ad);
}
