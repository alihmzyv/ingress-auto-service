package az.ingress.ingressautoservice.repository;

import az.ingress.ingressautoservice.dto.ad.AdShortResponseDto;
import az.ingress.ingressautoservice.dto.ad.FindAdsRequestParams;
import az.ingress.ingressautoservice.entity.Ad;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface AdRepository {
    List<AdShortResponseDto> find(FindAdsRequestParams requestParams, Pageable pageable);
    Ad save(Ad ad);
    Optional<Ad> findById(Long id);

    int deleteById(Long id);

    Long getTotalNumOfPages(FindAdsRequestParams requestParams, Pageable pageable);

    List<AdShortResponseDto> find(Long accountId, Pageable pageable);

    Long getTotalNumOfPages(Long accountId, Pageable pageable);
}
