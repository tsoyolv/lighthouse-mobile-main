package ru.lighthouse.mobile.main.controller.recommendation.dto;

import lombok.Data;
import ru.lighthouse.mobile.main.mapper.model.DtoModel;

@Data
public class BuildingDto implements DtoModel {
    private Integer constructionYear;
    private String wallMaterial;
    private String lifts;
    private Integer porchAmount;
    private String heating;
    private String parking;
}
