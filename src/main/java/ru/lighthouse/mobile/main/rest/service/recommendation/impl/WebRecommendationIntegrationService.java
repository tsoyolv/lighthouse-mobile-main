package ru.lighthouse.mobile.main.rest.service.recommendation.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.lighthouse.mobile.main.core.rest.dto.PageRequestDto;
import ru.lighthouse.mobile.main.core.rest.dto.PageResponseDto;
import ru.lighthouse.mobile.main.rest.service.recommendation.WebRecommendationService;
import ru.lighthouse.mobile.main.rest.service.recommendation.dto.RecommendationDto;
import ru.lighthouse.mobile.main.rest.service.recommendation.dto.RecommendationRequestDto;

@Service
@RequiredArgsConstructor
public class WebRecommendationIntegrationService implements WebRecommendationService {


    @Override
    public void request(RecommendationRequestDto request) {

    }

    @Override
    public PageResponseDto<RecommendationDto> get(PageRequestDto pageRequest) {
        return null;
    }

    @Override
    public RecommendationDto getById(Long id) {
        return null;
    }
}
