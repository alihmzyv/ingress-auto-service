package az.ingress.ingressautoservice.dto.ad;

import az.ingress.ingressautoservice.constant.MileageType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FindAdsRequestParams {
    Long accountId;
    Long brandId;
    Set<Long> modelIds;
    Set<Long> cityIds;
    @Min(1)
    Long minPrice;
    @Min(1)
    Long maxPrice;
    @NotNull
    Long currencyId;
    Boolean eligibleForLoan;
    Boolean eligibleForBarter;
    Set<Long> bodyStyleIds;
    Byte minYear;
    Byte maxYear;
    MileageType mileageType;
}
