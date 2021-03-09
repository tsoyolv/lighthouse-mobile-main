package ru.lighthouse.mobile.main.rest.service.realtyreview.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import ru.lighthouse.mobile.main.core.rest.dto.DtoModel;


@Data
public class RealtyReviewScheduleRequestDto implements DtoModel {

    @ApiModelProperty(value = "Идентификатор объявления", position = -1)
    private Long realtyId;

    @ApiModelProperty(value = "", example = "", position = 1)
    private String str;
}