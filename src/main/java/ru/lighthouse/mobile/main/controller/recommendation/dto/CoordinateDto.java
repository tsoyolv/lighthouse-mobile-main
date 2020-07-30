package ru.lighthouse.mobile.main.controller.recommendation.dto;

import lombok.Data;
import ru.lighthouse.mobile.main.mapper.model.DtoModel;

import java.math.BigDecimal;

@Data
public class CoordinateDto implements DtoModel {
    private BigDecimal latitude;
    private BigDecimal longitude;
}
