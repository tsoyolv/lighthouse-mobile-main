package ru.lighthouse.mobile.main.integration.inbound.user;

import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.lighthouse.mobile.main.integration.inbound.user.dto.UserIntegrationDto;
import ru.lighthouse.mobile.main.service.user.UserService;
import ru.lighthouse.mobile.main.service.user.entity.User;

import static ru.lighthouse.mobile.main.boot.security.SecurityRole.ROLE_INTEGRATION_STR;


@RestController
@RequiredArgsConstructor
public class UserIntegrationController {
    private final MapperFacade mapper;
    private final UserService userService;

    @PostMapping("${integration.uri.user}")
    @Secured({ROLE_INTEGRATION_STR})
    public UserIntegrationDto createOrUpdateUser(@RequestBody UserIntegrationDto userIntegrationDto) {
        User user = mapper.map(userIntegrationDto, User.class);
        User created = userService.getOrCreate(user);
        return mapper.map(created, UserIntegrationDto.class);
    }
}
