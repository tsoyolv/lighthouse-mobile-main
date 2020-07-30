package ru.lighthouse.mobile.main.integration.user.dto;

import lombok.Data;
import ru.lighthouse.mobile.main.mapper.model.DtoModel;


@Data
public class AuthorityDto implements DtoModel {
    private String name;
    private String systemName;
}
