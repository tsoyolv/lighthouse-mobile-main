package ru.lighthouse.mobile.main.service.flat.impl;

import org.springframework.stereotype.Service;
import ru.lighthouse.mobile.main.core.dao.AbstractEntityService;
import ru.lighthouse.mobile.main.service.flat.FlatImageService;
import ru.lighthouse.mobile.main.service.flat.entity.FlatImage;
import ru.lighthouse.mobile.main.service.flat.repository.FlatImageRepository;

import java.util.List;

@Service
public class FlatImageServiceImpl extends AbstractEntityService<FlatImage, FlatImageRepository> implements FlatImageService {
    public FlatImageServiceImpl(FlatImageRepository repository) {
        super(repository);
    }

    @Override
    public List<FlatImage> getByFlatId(Long flatId) {
        return repository.getAllByFlat_Id(flatId);
    }
}
