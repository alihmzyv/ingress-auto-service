package az.ingress.ingressautoservice.service.impl;

import az.ingress.ingressautoservice.dto.ad.AdResponseDto;
import az.ingress.ingressautoservice.dto.ad.AdShortResponseDto;
import az.ingress.ingressautoservice.dto.ad.FindAdsRequestParams;
import az.ingress.ingressautoservice.exception.NotFoundException;
import az.ingress.ingressautoservice.repository.AdCustomRepository;
import az.ingress.ingressautoservice.service.AdService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static az.ingress.ingressautoservice.constant.AdError.AD_NOT_FOUND;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Service
public class AdServiceImpl implements AdService {
    AdCustomRepository adCustomRepository;
    ModelMapper modelMapper;

    @Override
    public List<AdShortResponseDto> find(FindAdsRequestParams requestParams, Pageable pageable) {
        return adCustomRepository.find(requestParams, pageable);
    }

    @SneakyThrows
    @Override
    public AdResponseDto findById(Long id) {
        return adCustomRepository.findById(id)
                .map(ad -> modelMapper.map(ad, AdResponseDto.class))
                .orElseThrow(() -> NotFoundException.of(AD_NOT_FOUND.getCode(), AD_NOT_FOUND.buildMessage(id)));
    }
}
