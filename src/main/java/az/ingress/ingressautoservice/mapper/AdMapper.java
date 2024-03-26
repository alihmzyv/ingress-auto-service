package az.ingress.ingressautoservice.mapper;

import az.ingress.ingressautoservice.constant.MileageType;
import az.ingress.ingressautoservice.dto.ad.AdResponseDto;
import az.ingress.ingressautoservice.dto.ad.CreateAdRequestDto;
import az.ingress.ingressautoservice.entity.Ad;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Component
public class AdMapper {
    ModelMapper modelMapper;

    public AdResponseDto toResponse(Ad ad) {
        AdResponseDto adResponse = modelMapper.map(ad, AdResponseDto.class);
        Optional.ofNullable(ad.getCarDetails().getEngineCapacity().getCapacityInSm3())
                .ifPresent(sm3 -> {
                    BigDecimal litres = BigDecimal.valueOf(sm3).divide(BigDecimal.valueOf(1000), 2, RoundingMode.HALF_UP);
                    adResponse.getCarDetails().setEngineCapacityInLitres(litres);
                });
        Optional.ofNullable(ad.getAccount())
                .ifPresent(account -> {
                    adResponse.setAccountPhoneNumber(account.getPhoneNumber());
                });
        Optional.ofNullable(ad.getCarDetails().getMileage())
                .map(mileage -> {
                    if (mileage.equals(0L)) {
                        return MileageType.WITHOUT;
                    } else {
                        return MileageType.WITH;
                    }
                })
                .ifPresent(mileageType -> adResponse.getCarDetails().setMileageType(mileageType));
        return adResponse;
    }

    public Ad toEntity(CreateAdRequestDto request) {
        return modelMapper.map(request, Ad.class);
    }
}
