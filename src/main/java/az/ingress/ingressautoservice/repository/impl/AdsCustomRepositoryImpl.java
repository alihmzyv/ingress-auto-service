package az.ingress.ingressautoservice.repository.impl;

import az.ingress.ingressautoservice.dto.AdShortResponseDto;
import az.ingress.ingressautoservice.dto.FindAdsRequestParams;
import az.ingress.ingressautoservice.entity.Ad;
import az.ingress.ingressautoservice.entity.Ad_;
import az.ingress.ingressautoservice.entity.addetails.City_;
import az.ingress.ingressautoservice.entity.addetails.Currency_;
import az.ingress.ingressautoservice.entity.cardetails.Brand_;
import az.ingress.ingressautoservice.entity.cardetails.EngineCapacity_;
import az.ingress.ingressautoservice.entity.cardetails.Model_;
import az.ingress.ingressautoservice.entity.helper.CarDetails_;
import az.ingress.ingressautoservice.repository.AdsCustomRepository;
import jakarta.persistence.PersistenceUnit;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;
import org.hibernate.query.criteria.CriteriaDefinition;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Repository
public class AdsCustomRepositoryImpl implements AdsCustomRepository {
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
                        restrict(equal(root.get(Ad_.carDetails).get(CarDetails_.model).get(Model_.brand).get(Brand_.id), requestParams.getBrandId()));
                    });
            offset(pageable.getPageSize() * (pageable.getPageNumber() - 1));
            fetch(pageable.getPageSize());
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
