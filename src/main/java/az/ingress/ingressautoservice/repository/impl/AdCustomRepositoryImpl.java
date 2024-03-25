package az.ingress.ingressautoservice.repository.impl;

import az.ingress.ingressautoservice.dto.ad.AdShortResponseDto;
import az.ingress.ingressautoservice.dto.ad.FindAdsRequestParams;
import az.ingress.ingressautoservice.entity.Ad;
import az.ingress.ingressautoservice.entity.Ad_;
import az.ingress.ingressautoservice.entity.addetails.City_;
import az.ingress.ingressautoservice.entity.addetails.Currency_;
import az.ingress.ingressautoservice.entity.cardetails.BodyStyle_;
import az.ingress.ingressautoservice.entity.cardetails.Brand_;
import az.ingress.ingressautoservice.entity.cardetails.EngineCapacity_;
import az.ingress.ingressautoservice.entity.cardetails.Model_;
import az.ingress.ingressautoservice.entity.helper.CarDetails_;
import az.ingress.ingressautoservice.repository.AdCustomRepository;
import az.ingress.ingressautoservice.util.CriteriaPathResolver;
import jakarta.persistence.PersistenceUnit;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.hibernate.query.SortDirection;
import org.hibernate.query.criteria.CriteriaDefinition;
import org.hibernate.query.criteria.JpaExpression;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Repository
public class AdCustomRepositoryImpl implements AdCustomRepository {
    @PersistenceUnit
    private SessionFactory sessionFactory;

    @Override
    public List<AdShortResponseDto> find(FindAdsRequestParams requestParams, Pageable pageable) {
        CriteriaQuery<AdShortResponseDto> criteriaQuery = getCriteriaQuery(requestParams, pageable);
        return sessionFactory.fromSession(session -> session.createSelectionQuery(criteriaQuery)
                .getResultList());
    }

    private CriteriaQuery<AdShortResponseDto> getCriteriaQuery(FindAdsRequestParams requestParams, Pageable pageable) {
        return new CriteriaDefinition<>(sessionFactory, AdShortResponseDto.class) {{
            Root<Ad> root = from(Ad.class);

            multiselect(root.get(Ad_.id),
                    root.get(Ad_.priceVal),
                    root.get(Ad_.priceCurrency).get(Currency_.name),
                    root.get(Ad_.carDetails).get(CarDetails_.model).get(Model_.brand).get(Brand_.name),
                    root.get(Ad_.carDetails).get(CarDetails_.model).get(Model_.name),
                    root.get(Ad_.carDetails).get(CarDetails_.yearOfCar),
                    root.get(Ad_.carDetails).get(CarDetails_.engineCapacity).get(EngineCapacity_.capacityInSm3),
                    root.get(Ad_.carDetails).get(CarDetails_.mileage),
                    root.get(Ad_.city).get(City_.name),
                    root.get(Ad_.createdAt));

            Optional.ofNullable(requestParams.getBrandId())
                    .ifPresent(brandId -> {
                        restrict(equal(root.get(Ad_.carDetails).get(CarDetails_.model).get(Model_.brand).get(Brand_.id), brandId));
                    });
            Optional.ofNullable(requestParams.getModelIds())
                    .ifPresent(modelIds -> {
                        restrict(root.get(Ad_.carDetails).get(CarDetails_.model).get(Model_.id).in(modelIds));
                    });
            Optional.ofNullable(requestParams.getCityIds())
                    .ifPresent(cityIds -> {
                        restrict(root.get(Ad_.city).get(City_.ID).in(cityIds));
                    });
            Optional.ofNullable(requestParams.getMinPrice())
                    .ifPresent(minPrice -> {
                        restrict(ge(root.get(Ad_.priceVal), minPrice));
                    });
            Optional.ofNullable(requestParams.getMaxPrice())
                    .ifPresent(maxPrice -> {
                        restrict(le(root.get(Ad_.priceVal), maxPrice));
                    });
            Optional.ofNullable(requestParams.getCurrencyId())
                    .ifPresent(currencyId -> {
                        restrict(equal(root.get(Ad_.priceCurrency).get(Currency_.id), currencyId));
                    });
            Optional.ofNullable(requestParams.getEligibleForLoan())
                    .ifPresent(eligibleForLoan -> {
                        restrict(equal(root.get(Ad_.eligibleForLoan), eligibleForLoan));
                    });
            Optional.ofNullable(requestParams.getEligibleForBarter())
                    .ifPresent(eligibleForBarter -> {
                        restrict(equal(root.get(Ad_.eligibleForBarter), eligibleForBarter));
                    });
            Optional.ofNullable(requestParams.getBodyStyleIds())
                    .ifPresent(bodyStyleIds -> {
                        restrict(root.get(Ad_.carDetails).get(CarDetails_.bodyStyle).get(BodyStyle_.id).in(bodyStyleIds));
                    });
            Optional.ofNullable(requestParams.getMinYear())
                    .ifPresent(minYear -> {
                        restrict(ge(root.get(Ad_.carDetails).get(CarDetails_.yearOfCar), minYear));
                    });
            Optional.ofNullable(requestParams.getMaxYear())
                    .ifPresent(maxYear -> {
                        restrict(le(root.get(Ad_.carDetails).get(CarDetails_.yearOfCar), maxYear));
                    });
            Optional.ofNullable(requestParams.getMileageType())
                    .ifPresent(mileageType -> {
                        Path<Long> mileage = root.get(Ad_.carDetails).get(CarDetails_.mileage);
                        switch (mileageType) {
                            case WITH -> restrict(gt(mileage, 0));
                            case WITHOUT -> restrict(equal(mileage, 0));
                        }
                    });
            offset(pageable.getPageSize() * pageable.getPageNumber());
            fetch(pageable.getPageSize());
            Sort sortOr = pageable.getSortOr(pageable.getSortOr(Sort.by(Sort.Order.desc(Ad_.CREATED_AT))));
            List<Order> orders = sortOr.stream()
                    .map(order -> sort((JpaExpression<?>) CriteriaPathResolver.getPath(root, order.getProperty()), order.getDirection().isAscending() ? SortDirection.ASCENDING : SortDirection.DESCENDING))
                    .collect(Collectors.toList());
            orderBy(orders);
        }};
    }

    @Override
    public Ad save(Ad ad) {
        return sessionFactory.fromTransaction(session -> {
            session.persist(ad);
            return ad;
        });
    }
}
