package ru.lighthouse.mobile.main.core.rest.mapper;

import ru.lighthouse.mobile.main.core.mapper.model.DtoModel;
import ru.lighthouse.mobile.main.core.mapper.model.EntityModel;

public interface CustomEntityDtoMapper<ENTITY extends EntityModel, DTO extends DtoModel> {
    ENTITY map(DTO dto);
    DTO map(ENTITY entity);
}
