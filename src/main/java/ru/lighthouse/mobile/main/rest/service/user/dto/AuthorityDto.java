package ru.lighthouse.mobile.main.rest.service.user.dto;

import lombok.Data;
import ru.lighthouse.mobile.main.core.rest.dto.DtoModel;


@Data
public class AuthorityDto implements DtoModel {
    private String name;
    private String systemName;
}
