package ru.lighthouse.mobile.main.logic.recommendation;

import ru.lighthouse.mobile.main.logic.recommendation.entity.RecommendationFlat;

import java.util.stream.Stream;

public interface RecommendationService {
    Stream<RecommendationFlat> getAll(Long userId);
}
