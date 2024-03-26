package az.ingress.ingressautoservice.service.impl;

import az.ingress.ingressautoservice.dto.ad.AdResponseDto;
import az.ingress.ingressautoservice.dto.ad.AdShortResponseDto;
import az.ingress.ingressautoservice.dto.ad.CreateAdRequestDto;
import az.ingress.ingressautoservice.dto.ad.FindAdsRequestParams;
import az.ingress.ingressautoservice.entity.Ad;
import az.ingress.ingressautoservice.exception.NotFoundException;
import az.ingress.ingressautoservice.mapper.AdMapper;
import az.ingress.ingressautoservice.repository.AdRepository;
import az.ingress.ingressautoservice.service.AccountService;
import az.ingress.ingressautoservice.service.AdService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static az.ingress.ingressautoservice.constant.error.AdError.AD_NOT_FOUND;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Service
public class AdServiceImpl implements AdService {
    AdRepository adRepository;
    AccountService accountService;
    AdMapper adMapper;

    @Override
    public List<AdShortResponseDto> find(FindAdsRequestParams requestParams, Pageable pageable) {
        return adRepository.find(requestParams, pageable);
    }

    @Override
    public List<AdShortResponseDto> find(Long accountId, Pageable pageable) {
        return adRepository.find(accountId, pageable);
    }

    @Override
    public Long getTotalNumOfPages(FindAdsRequestParams requestParams, Pageable pageable) {
        return adRepository.getTotalNumOfPages(requestParams, pageable);
    }

    @Override
    public Long getTotalNumOfPages(Long accountId, Pageable pageable) {
        return adRepository.getTotalNumOfPages(accountId, pageable);
    }

    @SneakyThrows
    @Override
    public AdResponseDto findById(Long id) {
        return adRepository.findById(id)
                .map(adMapper::toResponse)
                .orElseThrow(() -> NotFoundException.of(AD_NOT_FOUND.getCode(), AD_NOT_FOUND.buildMessage(id)));
    }

    @Override
    public void createAd(Long accountId, CreateAdRequestDto request) {
        accountService.ensureExistsById(accountId);
        Ad ad = adMapper.toEntity(accountId, request);
        adRepository.save(ad);
    }

    @Override
    public void deleteById(Long id) {
        int numOfRowsDeleted = adRepository.deleteById(id);
        if (numOfRowsDeleted == 0) {
            throw NotFoundException.of(AD_NOT_FOUND.getCode(), AD_NOT_FOUND.buildMessage(id));
        }
    }
}
