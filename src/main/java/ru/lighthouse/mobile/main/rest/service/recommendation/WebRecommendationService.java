package ru.lighthouse.mobile.main.rest.service.recommendation;

import ru.lighthouse.mobile.main.core.rest.dto.PageRequestDto;
import ru.lighthouse.mobile.main.core.rest.dto.PageResponseDto;
import ru.lighthouse.mobile.main.rest.service.recommendation.dto.RecommendationDto;
import ru.lighthouse.mobile.main.rest.service.recommendation.dto.RecommendationRequestDto;

public interface WebRecommendationService {

    void request(RecommendationRequestDto request);

    PageResponseDto<RecommendationDto> get(PageRequestDto pageRequest);

    RecommendationDto getById(Long id);
}
