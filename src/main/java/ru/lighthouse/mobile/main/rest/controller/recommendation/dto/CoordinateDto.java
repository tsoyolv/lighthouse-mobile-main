package ru.lighthouse.mobile.main.rest.controller.recommendation.dto;

import lombok.Data;
import ru.lighthouse.mobile.main.core.mapper.model.DtoModel;

import java.math.BigDecimal;

@Data
public class CoordinateDto implements DtoModel {
    private BigDecimal latitude;
    private BigDecimal longitude;
}
