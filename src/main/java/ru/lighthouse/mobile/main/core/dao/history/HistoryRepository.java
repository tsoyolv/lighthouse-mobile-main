package ru.lighthouse.mobile.main.core.dao.history;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface HistoryRepository<T extends HistoryDomainModel> extends JpaRepository<T, Long> {
    List<T> findAllByOriginId(Long originId);
}
