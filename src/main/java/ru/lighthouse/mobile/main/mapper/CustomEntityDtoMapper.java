package ru.lighthouse.mobile.main.mapper;

import ru.lighthouse.mobile.main.mapper.model.DtoModel;
import ru.lighthouse.mobile.main.mapper.model.EntityModel;

public interface CustomEntityDtoMapper<ENTITY extends EntityModel, DTO extends DtoModel> {
    ENTITY map(DTO dto);
    DTO map(ENTITY entity);
}
