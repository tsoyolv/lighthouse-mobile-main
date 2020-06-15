package ru.lighthouse.mobile.logic.recommendation;

import ru.lighthouse.mobile.logic.recommendation.entity.RecommendationFlat;

import java.util.stream.Stream;

public interface RecommendationService {
    Stream<RecommendationFlat> getAll(Long userId);
}
