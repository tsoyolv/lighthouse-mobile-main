package ru.lighthouse.mobile.main.rest.service;

import ma.glasnost.orika.MapperFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.lighthouse.mobile.main.boot.property.ServiceProperties;
import ru.lighthouse.mobile.main.core.rest.JwtRestTemplate;
import ru.lighthouse.mobile.main.repository.flat.FlatRepository;
import ru.lighthouse.mobile.main.repository.user.AuthorityRepository;
import ru.lighthouse.mobile.main.repository.user.UserRepository;
import ru.lighthouse.mobile.main.rest.service.flat.WebFlatService;
import ru.lighthouse.mobile.main.rest.service.flat.impl.dao.WebFlatDaoService;
import ru.lighthouse.mobile.main.rest.service.flat.impl.dao.mapper.FlatDetailsDtoMapper;
import ru.lighthouse.mobile.main.rest.service.realty.WebRealtyService;
import ru.lighthouse.mobile.main.rest.service.realty.impl.WebRealtyIntegrationService;
import ru.lighthouse.mobile.main.rest.service.realtyreview.WebRealtyReviewService;
import ru.lighthouse.mobile.main.rest.service.realtyreview.impl.dao.WebRealtyReviewDaoService;
import ru.lighthouse.mobile.main.rest.service.user.WebUserService;
import ru.lighthouse.mobile.main.rest.service.user.impl.dao.WebUserDaoService;

@Configuration
public class RestServiceConfig {

    @Bean
    public WebRealtyService webRealtyService(ServiceProperties serviceProperties, JwtRestTemplate jwtRestTemplate) {
        return new WebRealtyIntegrationService(serviceProperties, jwtRestTemplate);
    }

    @Bean
    public WebFlatService webFlatService(FlatRepository repository, FlatDetailsDtoMapper mapper) {
        return new WebFlatDaoService(repository, mapper);
    }

    @Bean
    public WebRealtyReviewService webRealtyReviewService(MapperFacade mapper) {
        return new WebRealtyReviewDaoService(mapper);
    }

    @Bean
    public WebUserService webUserService(UserRepository repository, MapperFacade mapper, AuthorityRepository authorityRepository) {
        return new WebUserDaoService(repository, mapper, authorityRepository);
    }
}
