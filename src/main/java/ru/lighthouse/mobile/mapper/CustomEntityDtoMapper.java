package ru.lighthouse.mobile.mapper;

import ru.lighthouse.mobile.mapper.model.DtoModel;
import ru.lighthouse.mobile.mapper.model.EntityModel;

public interface CustomEntityDtoMapper<ENTITY extends EntityModel, DTO extends DtoModel> {
    ENTITY map(DTO dto);
    DTO map(ENTITY entity);
}
