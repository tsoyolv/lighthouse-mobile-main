package ru.lighthouse.mobile.main.service.flat.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.lighthouse.mobile.main.service.flat.entity.FlatImage;
import ru.lighthouse.mobile.main.service.flat.repository.FlatImageRepository;
import ru.lighthouse.mobile.main.service.flat.FlatImageService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FlatImageServiceImpl implements FlatImageService {
    private final FlatImageRepository repository;

    @Override
    public List<FlatImage> getByFlatId(Long flatId) {
        return repository.getAllByFlat_Id(flatId);
    }

    @Override
    public FlatImage getById(Long id) {
        return repository.getOne(id);
    }
}
