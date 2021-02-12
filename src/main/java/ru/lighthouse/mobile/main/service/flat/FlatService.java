package ru.lighthouse.mobile.main.service.flat;

import ru.lighthouse.mobile.main.core.dao.EntityService;
import ru.lighthouse.mobile.main.service.flat.entity.Flat;

import java.util.Optional;

public interface FlatService extends EntityService<Flat> {
    Optional<Flat> getDetails(Long id);
}
