package ru.lighthouse.mobile.main.logic.flat;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.lighthouse.mobile.main.logic.flat.entity.FlatImage;
import ru.lighthouse.mobile.main.logic.flat.entity.FlatInfo;
import ru.lighthouse.mobile.main.logic.flat.repository.FlatImageRepository;
import ru.lighthouse.mobile.main.logic.flat.repository.FlatInfoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FlatServiceImpl implements FlatService {
    private final FlatImageRepository flatImageRepository;
    private final FlatInfoRepository flatInfoRepository;

    @Override
    public FlatInfo getFlatInfo(Long id) {
        return flatInfoRepository.getOne(id);
    }

    @Override
    public List<FlatImage> getImagesByFlatId(Long flatId) {
        return flatImageRepository.getAllByFlatId(flatId);
    }
}
