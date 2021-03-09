package ru.lighthouse.mobile.main.rest.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.lighthouse.mobile.main.rest.service.realtyreview.WebRealtyReviewService;
import ru.lighthouse.mobile.main.rest.service.realtyreview.dto.RealtyReviewScheduleRequestDto;

import javax.validation.Valid;


@Validated
@RestController
@RequestMapping("/realty-review")
@RequiredArgsConstructor
@Api(tags = "3. Покупка. Просмотры объявлений. REST для работы с просмотрами объявлений")
public class RealtyReviewController {
    private final WebRealtyReviewService realtyReviewService;

    @PostMapping("/schedule")
    @ApiOperation("В разработке. Назначить просмотр объявления")
    public void scheduleReview(@RequestBody @Valid RealtyReviewScheduleRequestDto scheduleRequestDto) {
        realtyReviewService.scheduleReview(scheduleRequestDto);
    }
}
