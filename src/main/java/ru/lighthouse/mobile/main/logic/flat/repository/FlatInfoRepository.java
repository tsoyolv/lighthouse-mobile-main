package ru.lighthouse.mobile.main.logic.flat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lighthouse.mobile.main.logic.flat.entity.FlatInfo;

public interface FlatInfoRepository extends JpaRepository<FlatInfo, Long> {
}
