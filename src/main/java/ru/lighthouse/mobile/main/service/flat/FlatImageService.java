package ru.lighthouse.mobile.main.service.flat;

import ru.lighthouse.mobile.main.service.flat.entity.FlatImage;

import java.util.List;

public interface FlatImageService {
    List<FlatImage> getByFlatId(Long flatId);
    FlatImage getById(Long id);
}
