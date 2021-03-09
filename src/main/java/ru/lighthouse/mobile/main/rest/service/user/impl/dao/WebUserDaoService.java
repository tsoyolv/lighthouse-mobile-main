package ru.lighthouse.mobile.main.rest.service.user.impl.dao;

import ma.glasnost.orika.MapperFacade;
import ru.lighthouse.mobile.main.repository.user.AuthorityRepository;
import ru.lighthouse.mobile.main.repository.user.UserRepository;
import ru.lighthouse.mobile.main.repository.user.entity.Authority;
import ru.lighthouse.mobile.main.repository.user.entity.User;
import ru.lighthouse.mobile.main.rest.service.user.WebUserService;
import ru.lighthouse.mobile.main.rest.service.user.dto.UserIntegrationDto;

import java.util.Collections;
import java.util.Set;

import static ru.lighthouse.mobile.main.boot.security.SecurityRole.MOBILE;


public class WebUserDaoService implements WebUserService {

    private final UserRepository repository;
    private final MapperFacade mapper;
    private final Set<Authority> defaultMobileAuthorities;

    public WebUserDaoService(UserRepository repository, MapperFacade mapper, AuthorityRepository authorityRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.defaultMobileAuthorities = Collections.singleton(authorityRepository.getBySystemName(MOBILE.getSystemName()));
    }

    @Override
    public UserIntegrationDto createOrUpdateIntUser(UserIntegrationDto userIntegrationDto) {
        User user = mapper.map(userIntegrationDto, User.class);
        User found = repository.findByPhoneNumber(user.getPhoneNumber());
        if (found == null) {
            user.setAuthorities(defaultMobileAuthorities);
            found = repository.save(user);
        } else {
            found.setUserAgent(user.getUserAgent());
            found.setLastLogin(user.getLastLogin());
            found = repository.save(found);
        }
        return mapper.map(found, UserIntegrationDto.class);
    }
}
