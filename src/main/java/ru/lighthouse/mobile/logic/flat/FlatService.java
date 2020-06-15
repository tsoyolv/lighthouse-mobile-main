package ru.lighthouse.mobile.logic.flat;

import ru.lighthouse.mobile.logic.flat.entity.FlatImage;
import ru.lighthouse.mobile.logic.flat.entity.FlatInfo;

import java.util.List;

public interface FlatService {
    FlatInfo getFlatInfo(Long id);
    List<FlatImage> getImagesByFlatId(Long flatId);
}
