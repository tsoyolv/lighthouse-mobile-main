package ru.lighthouse.mobile.main.repository.flat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.lighthouse.mobile.main.repository.flat.entity.FlatPriceHistory;

@Repository
public interface FlatPriceHistoryRepository extends JpaRepository<FlatPriceHistory, Long>, JpaSpecificationExecutor<FlatPriceHistory> {
}
