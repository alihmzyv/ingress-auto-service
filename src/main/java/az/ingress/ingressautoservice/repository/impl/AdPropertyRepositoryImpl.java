package az.ingress.ingressautoservice.repository.impl;

import az.ingress.ingressautoservice.constant.AdProperty;
import az.ingress.ingressautoservice.dto.PropertyDto;
import az.ingress.ingressautoservice.entity.cardetails.EngineCapacity;
import az.ingress.ingressautoservice.entity.cardetails.EngineCapacity_;
import az.ingress.ingressautoservice.entity.helper.SimpleProperty;
import az.ingress.ingressautoservice.repository.AdPropertyRepository;
import jakarta.persistence.PersistenceUnit;
import jakarta.persistence.Tuple;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class AdPropertyRepositoryImpl implements AdPropertyRepository {

    @PersistenceUnit
    private SessionFactory sessionFactory;

    @Override
    public List<PropertyDto<?, ?>> get(AdProperty adProperty) {
        String selectionQuery = getSelectionQuery(adProperty);
        return sessionFactory.fromSession(session -> session.createSelectionQuery(selectionQuery, Tuple.class)
                .getResultList()
                .stream()
                .map(property -> PropertyDto.of(property.get(0), property.get(1)))
                .collect(Collectors.toList()));
    }

    private String getSelectionQuery(AdProperty adProperty) {
        return switch (adProperty) {
            case BODY_STYLE -> "select bs.id, bs.name from BodyStyle bs";
            case BRAND -> "select b.id, b.name from Brand b";
            case CITY -> "select c.id, c.name from City c";
            case COLOR -> "select c.id, c.name from Color c";
            case CURRENCY -> "select c.id, c.name from Currency c";
            case DRIVE_UNIT_TYPE -> "select dut.id, dut.name from DriveUnitType dut";
            case ENGINE_CAPACITY -> "select ec.id, ec.capacityInSm3 from EngineCapacity ec";
            case FUEL_TYPE -> "select ft.id, ft.name from FuelType ft";
            case MARKET_VERSION -> "select mv.id, mv.name from MarketVersion mv";
            case MODEL -> "select m.id, m.name from Model m";
            case OWNERSHIP_HISTORY -> "select oh.id, oh.name from OwnershipHistory oh";
            case TRANSMISSION_TYPE -> "select tt.id, tt.name from TransmissionType tt";
        };
    }
}
