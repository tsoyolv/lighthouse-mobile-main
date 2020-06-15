package ru.lighthouse.mobile.controller.recommendation.dto;

import lombok.Data;
import ru.lighthouse.mobile.mapper.model.DtoModel;

import java.math.BigDecimal;
import java.util.List;

@Data
public class RecommendationFlatDto implements DtoModel {
    private List<ImageDto> images;
    private BigDecimal price;
    private BigDecimal squareMetrePrice;
    private BigDecimal area;
    private Integer roomsAmount;
    private Integer floor;
    private Integer totalFloors;
    private String address;
    private String metro;
    private Integer timeToMetro;
    private CoordinateDto coordinates;
    private CommentDto expertComment;
    private String description;
    private FlatMainDetailsDto details;
    private BuildingDto building;
}
