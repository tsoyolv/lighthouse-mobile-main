package ru.lighthouse.mobile.main.rest.integrationcontroller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.lighthouse.mobile.main.rest.service.user.WebUserService;
import ru.lighthouse.mobile.main.rest.service.user.dto.UserIntegrationDto;

import static ru.lighthouse.mobile.main.boot.security.SecurityRole.ROLE_INTEGRATION_STR;


@RestController
@RequiredArgsConstructor
public class UserIntegrationController {
    private final WebUserService userService;

    @PostMapping("${integration.uri.user}")
    @Secured({ROLE_INTEGRATION_STR})
    public UserIntegrationDto createOrUpdateUser(@RequestBody UserIntegrationDto userIntegrationDto) {
        return userService.createOrUpdateIntUser(userIntegrationDto);
    }
}
