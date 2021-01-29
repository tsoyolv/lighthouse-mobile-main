package ru.lighthouse.mobile.main.service.recommendation;

import ru.lighthouse.mobile.main.service.recommendation.entity.RecommendationFlat;

import java.util.stream.Stream;

public interface RecommendationService {
    Stream<RecommendationFlat> getAll(Long userId);
}
