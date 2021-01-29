package ru.lighthouse.mobile.main.integration.inbound.user.dto;

import lombok.Data;
import ru.lighthouse.mobile.main.core.mapper.model.DtoModel;


@Data
public class AuthorityDto implements DtoModel {
    private String name;
    private String systemName;
}
