package az.ingress.ingressautoservice.controller;

import az.ingress.ingressautoservice.constant.AdProperty;
import az.ingress.ingressautoservice.dto.BaseRestApiResponseDto;
import az.ingress.ingressautoservice.dto.PropertyDto;
import az.ingress.ingressautoservice.repository.AdPropertyRepository;
import az.ingress.ingressautoservice.util.RestApiResponseBuilder;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("/static")
@RestController
public class StaticDataController implements RestApiResponseBuilder {
    AdPropertyRepository adPropertyRepository;

    @GetMapping("/ad")
    public BaseRestApiResponseDto<List<PropertyDto<?, ?>>> getAdProperties(
            @RequestParam AdProperty adProperty
    ) {
        return generateResponse(adPropertyRepository.get(adProperty));
    }
}
