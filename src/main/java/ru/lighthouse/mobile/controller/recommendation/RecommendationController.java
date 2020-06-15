package ru.lighthouse.mobile.controller.recommendation;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.lighthouse.mobile.controller.recommendation.dto.RecommendationFlatDto;
import ru.lighthouse.mobile.controller.recommendation.mapper.RecommendationMapper;
import ru.lighthouse.mobile.security.SecurityService;
import ru.lighthouse.mobile.logic.recommendation.RecommendationService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import static ru.lighthouse.mobile.Uri.FLAT_RECOMMENDATIONS_URI;

@RestController
@RequiredArgsConstructor
public class RecommendationController {
    private final RecommendationService recommendationService;
    private final RecommendationMapper recommendationMapper;
    private final SecurityService securityService;

    @GetMapping(FLAT_RECOMMENDATIONS_URI)
    @Transactional
    public List<RecommendationFlatDto> getRecommendationFlats() {
        return recommendationService.getAll(getUserIdFromContext()).map(recommendationMapper::map).collect(Collectors.toList());
    }

    private Long getUserIdFromContext() {
        final SecurityService.Token token = securityService.retrieveToken(SecurityContextHolder.getContext());
        return token.getUserId();
    }
}
