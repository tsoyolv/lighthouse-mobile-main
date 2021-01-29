package ru.lighthouse.mobile.main.core.rest;

import ru.lighthouse.mobile.main.core.dao.DomainModel;
import ru.lighthouse.mobile.main.core.rest.dto.DtoModel;

public interface CustomEntityDtoMapper<ENTITY extends DomainModel, DTO extends DtoModel> {
    ENTITY map(DTO dto);
    DTO map(ENTITY entity);
}
