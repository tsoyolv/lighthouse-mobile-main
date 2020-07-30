package ru.lighthouse.mobile.main.controller.recommendation;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.lighthouse.mobile.main.controller.recommendation.dto.RecommendationFlatDto;
import ru.lighthouse.mobile.main.controller.recommendation.mapper.RecommendationMapper;
import ru.lighthouse.mobile.main.security.CurrentContext;
import ru.lighthouse.mobile.main.logic.recommendation.RecommendationService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import static ru.lighthouse.mobile.main.controller.Uri.FLAT_RECOMMENDATIONS_URI;

@RestController
@RequiredArgsConstructor
public class RecommendationController {
    private final RecommendationService recommendationService;
    private final RecommendationMapper recommendationMapper;
    private final CurrentContext context;

    @GetMapping(FLAT_RECOMMENDATIONS_URI)
    @Transactional
    public List<RecommendationFlatDto> getRecommendationFlats() {
        Long userId = context.getUserId();
        return recommendationService.getAll(userId).map(recommendationMapper::map).collect(Collectors.toList());
    }
}
