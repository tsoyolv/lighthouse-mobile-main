package ru.lighthouse.mobile.main.rest.service.realty.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.lighthouse.mobile.main.boot.property.ServiceProperties;
import ru.lighthouse.mobile.main.core.rest.JwtRestTemplate;
import ru.lighthouse.mobile.main.core.rest.dto.BboxPageDto;
import ru.lighthouse.mobile.main.rest.service.realty.WebRealtyService;
import ru.lighthouse.mobile.main.rest.service.realty.dto.RealtyDto;
import ru.lighthouse.mobile.main.rest.service.realty.dto.RealtyMapFilterDto;

@RequiredArgsConstructor
public class WebRealtyIntegrationService implements WebRealtyService {

    private final ServiceProperties serviceProperties;
    private final JwtRestTemplate jwtRestTemplate;

    @Override
    public BboxPageDto<RealtyDto> getRealtyMap(RealtyMapFilterDto filterRequest) {
        ResponseEntity<BboxPageDto<RealtyDto>> exchange = jwtRestTemplate.exchange(serviceProperties.getCrm().getUrl() + "/map/realty",
                                                                                   HttpMethod.POST,
                                                                                   new HttpEntity<>(filterRequest),
                                                                                   new ParameterizedTypeReference<>() {});
        if (HttpStatus.OK == exchange.getStatusCode()) {
            return exchange.getBody();
        } else {
            throw new RuntimeException("Bad");
        }
    }
}
