package ru.lighthouse.mobile.main.service.flat.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.lighthouse.mobile.main.core.dao.AbstractEntityService;
import ru.lighthouse.mobile.main.service.flat.entity.Flat;
import ru.lighthouse.mobile.main.service.flat.entity.FlatPriceHistory;
import ru.lighthouse.mobile.main.service.flat.repository.FlatPriceHistoryRepository;
import ru.lighthouse.mobile.main.service.flat.repository.FlatRepository;
import ru.lighthouse.mobile.main.service.flat.FlatService;

import java.util.List;

@Service
public class FlatServiceImpl extends AbstractEntityService<Flat, FlatRepository> implements FlatService {

    private final FlatPriceHistoryRepository priceHistoryRepository;

    public FlatServiceImpl(FlatRepository repository,
                           FlatPriceHistoryRepository priceHistoryRepository) {
        super(repository);
        this.priceHistoryRepository = priceHistoryRepository;
    }

    @Override
    public Page<Flat> getByBuildingIdsAndFilter(Pageable page, List<Long> buildingIds, Specification<Flat> filter) {
        return null;
    }

    private void fetchFlatPriceHistories(List<Flat> content) {
        content.forEach(f -> f.setPriceHistory(getFlatPriceHistory(f)));
    }

    @Override
    public List<FlatPriceHistory> getFlatPriceHistory(Flat fLat) {
        return priceHistoryRepository.findAllByFlatOrderByModifyDateDesc(fLat);
    }

}
