package ru.lighthouse.mobile.main.rest.service.realtyreview.impl.dao;

import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import ru.lighthouse.mobile.main.rest.service.realtyreview.WebRealtyReviewService;
import ru.lighthouse.mobile.main.rest.service.realtyreview.dto.RealtyReviewScheduleRequestDto;

@RequiredArgsConstructor
public class WebRealtyReviewDaoService implements WebRealtyReviewService {
    private final MapperFacade mapper;

    @Override
    public void scheduleReview(RealtyReviewScheduleRequestDto scheduleRequestDto) {

    }
}
