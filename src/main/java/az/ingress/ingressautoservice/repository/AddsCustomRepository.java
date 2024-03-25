package az.ingress.ingressautoservice.repository;

import az.ingress.ingressautoservice.dto.AdShortResponseDto;
import az.ingress.ingressautoservice.dto.FindAddsRequestParams;
import az.ingress.ingressautoservice.entity.Ads;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AddsCustomRepository {
    List<AdShortResponseDto> findAdds(FindAddsRequestParams requestParams, Pageable pageable);
    Ads save(Ads ads);
}
