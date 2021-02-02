package ru.lighthouse.mobile.main.service.flat;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import ru.lighthouse.mobile.main.core.dao.EntityService;
import ru.lighthouse.mobile.main.service.flat.entity.Flat;
import ru.lighthouse.mobile.main.service.flat.entity.FlatPriceHistory;

import java.util.List;
import java.util.Optional;

public interface FlatService extends EntityService<Flat> {
    Page<Flat> getByBuildingIdsAndFilter(Pageable pageable, List<Long> buildingIds, Specification<Flat> filter);
    List<FlatPriceHistory> getFlatPriceHistory(Flat fLat);
    Optional<Flat> getDetails(Long id);
}
