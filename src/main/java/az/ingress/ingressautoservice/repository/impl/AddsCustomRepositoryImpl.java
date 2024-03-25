package az.ingress.ingressautoservice.repository.impl;

import az.ingress.ingressautoservice.dto.AdShortResponseDto;
import az.ingress.ingressautoservice.dto.FindAddsRequestParams;
import az.ingress.ingressautoservice.entity.Ads;
import az.ingress.ingressautoservice.entity.Ads_;
import az.ingress.ingressautoservice.entity.adsdetails.City_;
import az.ingress.ingressautoservice.entity.adsdetails.Currency_;
import az.ingress.ingressautoservice.entity.cardetails.Brand_;
import az.ingress.ingressautoservice.entity.cardetails.EngineCapacity_;
import az.ingress.ingressautoservice.entity.cardetails.Model_;
import az.ingress.ingressautoservice.entity.helper.CarDetails_;
import az.ingress.ingressautoservice.repository.AddsCustomRepository;
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
public class AddsCustomRepositoryImpl implements AddsCustomRepository {

    @PersistenceUnit
    private SessionFactory sessionFactory;

    @Override
    public List<AdShortResponseDto> findAdds(FindAddsRequestParams requestParams, Pageable pageable) {
        CriteriaQuery<AdShortResponseDto> criteriaQuery = new CriteriaDefinition<>(sessionFactory, AdShortResponseDto.class) {{
            Root<Ads> root = from(Ads.class);
            multiselect(root.get(Ads_.id),
                    root.get(Ads_.priceVal),
                    root.get(Ads_.priceCurrency).get(Currency_.name),
                    root.get(Ads_.carDetails).get(CarDetails_.model).get(Model_.brand).get(Brand_.name),
                    root.get(Ads_.carDetails).get(CarDetails_.model).get(Model_.name),
                    root.get(Ads_.carDetails).get(CarDetails_.yearOfCar),
                    root.get(Ads_.carDetails).get(CarDetails_.engineCapacity).get(EngineCapacity_.capacityInSm3),
                    root.get(Ads_.carDetails).get(CarDetails_.mileage),
                    root.get(Ads_.city).get(City_.name)/*,
                    root.get(Ads_.createdAt).alias("createdAt")*/);
            Optional.ofNullable(requestParams.getBrandId())
                    .ifPresent(brandId -> {
                        restrict(equal(root.get(Ads_.carDetails).get(CarDetails_.model).get(Model_.brand).get(Brand_.id), requestParams.getBrandId()));
                    });

        }};
        return sessionFactory.fromSession(session -> session.createSelectionQuery(criteriaQuery)
                .getResultList());
    }

    @Override
    public Ads save(Ads ads) {
        return sessionFactory.fromTransaction(session -> {
            session.persist(ads);
            return ads;
        });
    }
}
