package ru.lighthouse.mobile.main.rest.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.lighthouse.mobile.main.core.rest.dto.PageRequestDto;
import ru.lighthouse.mobile.main.core.rest.dto.PageResponseDto;
import ru.lighthouse.mobile.main.rest.service.recommendation.WebRecommendationService;
import ru.lighthouse.mobile.main.rest.service.recommendation.dto.RecommendationDto;
import ru.lighthouse.mobile.main.rest.service.recommendation.dto.RecommendationRequestDto;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import static ru.lighthouse.mobile.main.core.rest.CommonUri.URI_PART_ID;


@Validated
@RestController
@RequestMapping("/recommendation")
@RequiredArgsConstructor
@Api(tags = "4. Рекомендации. REST для работы с рекомендуемыми объектами")
public class RecommendationController {

    private final WebRecommendationService webRecommendationService;

    @GetMapping
    @ApiOperation("Получить список рекомендованных объектов для текущего пользователя")
    public PageResponseDto<RecommendationDto> get(@RequestBody(required = false) @Valid PageRequestDto pageRequest) {
        return webRecommendationService.get(pageRequest);
    }

    @GetMapping(URI_PART_ID)
    @ApiOperation("Получить рекомендованный объект по идентификатору")
    public RecommendationDto getById(@PathVariable @Min(1) Long id) {
        return webRecommendationService.getById(id);
    }

    @PostMapping("/request")
    @ApiOperation("Запросить персональные рекомендации")
    public void request(@RequestBody(required = false) @Valid RecommendationRequestDto request) {
        webRecommendationService.request(request);
    }
}
