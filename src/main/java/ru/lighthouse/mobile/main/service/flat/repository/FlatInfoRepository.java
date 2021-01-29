package ru.lighthouse.mobile.main.service.flat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lighthouse.mobile.main.service.flat.entity.FlatInfo;

public interface FlatInfoRepository extends JpaRepository<FlatInfo, Long> {
}
