package ru.lighthouse.mobile.main.service.recommendation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lighthouse.mobile.main.service.recommendation.entity.RecommendationFlat;

import java.util.stream.Stream;

public interface RecommendationFlatRepository extends JpaRepository<RecommendationFlat, Long> {
    Stream<RecommendationFlat> findAllByUserId(Long userId);
}
