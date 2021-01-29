package ru.lighthouse.mobile.main.service.flat;

import ru.lighthouse.mobile.main.service.flat.entity.FlatImage;
import ru.lighthouse.mobile.main.service.flat.entity.FlatInfo;

import java.util.List;

public interface FlatService {
    FlatInfo getFlatInfo(Long id);
    List<FlatImage> getImagesByFlatId(Long flatId);
}
