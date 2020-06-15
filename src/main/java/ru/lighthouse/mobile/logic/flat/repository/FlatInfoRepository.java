package ru.lighthouse.mobile.logic.flat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lighthouse.mobile.logic.flat.entity.FlatInfo;

public interface FlatInfoRepository extends JpaRepository<FlatInfo, Long> {
}
