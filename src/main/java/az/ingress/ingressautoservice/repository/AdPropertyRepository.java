package az.ingress.ingressautoservice.repository;

import az.ingress.ingressautoservice.constant.AdProperty;
import az.ingress.ingressautoservice.dto.PropertyDto;

import java.util.List;

public interface AdPropertyRepository {
    List<PropertyDto<?, ?>> get(AdProperty adProperty);
}
