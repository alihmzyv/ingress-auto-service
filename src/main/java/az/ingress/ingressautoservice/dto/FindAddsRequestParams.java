package az.ingress.ingressautoservice.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Set;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FindAddsRequestParams {
    public enum MileageType {
        ALL,
        NEW,
        USED
    }


    Long brandId;
    Set<Long> modelIds;
    List<Long> cityIds;
    @Min(1)
    Long minPrice;
    @Min(1)
    Long maxPrice;
    @NotNull
    Long currencyId;
    Boolean eligibleForLoan;
    Boolean eligibleForBarter;
    List<Long> bodyStyleIds;
    Byte minYear;
    Byte maxYear;
    MileageType mileageType = MileageType.ALL;
}
