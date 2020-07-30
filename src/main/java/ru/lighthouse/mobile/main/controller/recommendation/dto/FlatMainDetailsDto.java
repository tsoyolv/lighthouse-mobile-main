package ru.lighthouse.mobile.main.controller.recommendation.dto;

import lombok.Data;
import ru.lighthouse.mobile.main.mapper.model.DtoModel;

@Data
public class FlatMainDetailsDto implements DtoModel {
    private Float livingArea;
    private Integer ceilingHeight;
    private String bathroom;
    private String redecoration;
}
