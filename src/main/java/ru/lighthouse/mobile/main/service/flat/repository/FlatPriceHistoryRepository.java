package ru.lighthouse.mobile.main.service.flat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.lighthouse.mobile.main.service.flat.entity.FlatPriceHistory;

@Repository
public interface FlatPriceHistoryRepository extends JpaRepository<FlatPriceHistory, Long>, JpaSpecificationExecutor<FlatPriceHistory> {
}
