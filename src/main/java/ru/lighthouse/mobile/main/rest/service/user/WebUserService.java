package ru.lighthouse.mobile.main.rest.service.user;

import ru.lighthouse.mobile.main.rest.service.user.dto.UserIntegrationDto;

public interface WebUserService {

    UserIntegrationDto createOrUpdateIntUser(UserIntegrationDto userIntegrationDto);
}
