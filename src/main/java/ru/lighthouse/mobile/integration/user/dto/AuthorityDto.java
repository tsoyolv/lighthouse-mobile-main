package ru.lighthouse.mobile.integration.user.dto;

import lombok.Data;
import ru.lighthouse.mobile.mapper.model.DtoModel;


@Data
public class AuthorityDto implements DtoModel {
    private String name;
    private String systemName;
}
