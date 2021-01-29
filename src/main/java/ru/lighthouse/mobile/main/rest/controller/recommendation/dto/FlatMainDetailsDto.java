package ru.lighthouse.mobile.main.rest.controller.recommendation.dto;

import lombok.Data;
import ru.lighthouse.mobile.main.core.mapper.model.DtoModel;

@Data
public class FlatMainDetailsDto implements DtoModel {
    private Float livingArea;
    private Integer ceilingHeight;
    private String bathroom;
    private String redecoration;
}
