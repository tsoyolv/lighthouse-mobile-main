package ru.lighthouse.mobile.integration.user;

import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.lighthouse.mobile.integration.user.dto.UserIntegrationDto;
import ru.lighthouse.mobile.logic.user.UserService;
import ru.lighthouse.mobile.logic.user.entity.User;

import static ru.lighthouse.mobile.integration.IntegrationProperties.INTEGRATION_ROLE;
import static ru.lighthouse.mobile.integration.IntegrationProperties.USER_URI;


@RestController
@RequiredArgsConstructor
public class UserIntegrationController {
    private final MapperFacade mapper;
    private final UserService userService;

    @PostMapping(USER_URI)
    @Secured({INTEGRATION_ROLE})
    public UserIntegrationDto createOrUpdateUser(@RequestBody UserIntegrationDto userIntegrationDto){
        User user = mapper.map(userIntegrationDto, User.class);
        User created = userService.getOrCreate(user);
        return mapper.map(created, UserIntegrationDto.class);
    }
}
