package ru.lighthouse.mobile.main.service.flat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.lighthouse.mobile.main.service.flat.entity.Flat;
import ru.lighthouse.mobile.main.service.flat.entity.FlatPriceHistory;

import java.util.List;

@Repository
public interface FlatPriceHistoryRepository extends JpaRepository<FlatPriceHistory, Long>, JpaSpecificationExecutor<FlatPriceHistory> {
    List<FlatPriceHistory> findAllByFlatOrderByModifyDateDesc(Flat flat);
}
