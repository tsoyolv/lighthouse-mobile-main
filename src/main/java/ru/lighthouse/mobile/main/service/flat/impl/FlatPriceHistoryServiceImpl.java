package ru.lighthouse.mobile.main.service.flat.impl;

import org.springframework.stereotype.Service;
import ru.lighthouse.mobile.main.core.dao.AbstractEntityService;
import ru.lighthouse.mobile.main.service.flat.entity.FlatPriceHistory;
import ru.lighthouse.mobile.main.service.flat.repository.FlatPriceHistoryRepository;
import ru.lighthouse.mobile.main.service.flat.FlatPriceHistoryService;

@Service
public class FlatPriceHistoryServiceImpl extends AbstractEntityService<FlatPriceHistory, FlatPriceHistoryRepository> implements FlatPriceHistoryService {
    public FlatPriceHistoryServiceImpl(FlatPriceHistoryRepository repository) {
        super(repository);
    }

    @Override
    protected void updateFields(FlatPriceHistory toUpdate, FlatPriceHistory fromUpdate) {
        toUpdate.setPrice(fromUpdate.getPrice());
        toUpdate.setModifyDate(fromUpdate.getModifyDate());
        toUpdate.setUrl(fromUpdate.getUrl());
    }
}
