package ru.lighthouse.mobile.logic.recommendation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.lighthouse.mobile.logic.recommendation.entity.RecommendationFlat;
import ru.lighthouse.mobile.logic.recommendation.repository.RecommendationFlatRepository;

import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class RecommendationServiceImpl implements RecommendationService {
    private final RecommendationFlatRepository repository;

    @Override
    public Stream<RecommendationFlat> getAll(Long userId) {
        return repository.findAllByUserId(userId);
    }
}