package ru.lighthouse.mobile.main.rest.service.realtyreview;

import ru.lighthouse.mobile.main.rest.service.realtyreview.dto.RealtyReviewScheduleRequestDto;

public interface WebRealtyReviewService {

    void scheduleReview(RealtyReviewScheduleRequestDto scheduleRequestDto);
}
