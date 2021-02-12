package ru.lighthouse.mobile.main.service.flat;

import ru.lighthouse.mobile.main.core.dao.EntityService;
import ru.lighthouse.mobile.main.service.flat.entity.FlatImage;

import java.util.List;

public interface FlatImageService extends EntityService<FlatImage> {
    List<FlatImage> getByFlatId(Long flatId);
}
