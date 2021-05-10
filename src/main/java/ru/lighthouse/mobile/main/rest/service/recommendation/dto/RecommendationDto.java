package ru.lighthouse.mobile.main.rest.service.recommendation.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import ru.lighthouse.mobile.main.core.rest.dto.DtoModel;

import java.math.BigDecimal;
import java.util.List;


@Data
public class RecommendationDto implements DtoModel {

    @ApiModelProperty(value = "Идентификатор объявления", position = -1)
    private Long id;

    @ApiModelProperty(value = "Идентификатор рекомендованного объявления", example = "2")
    private Long realtyId;

    @ApiModelProperty(value = "Список URL фотографий объявления")
    private List<String> imagesUrls;

    @ApiModelProperty(example = "Кутузовский проспект 23")
    private String address;

    @ApiModelProperty(value = "Цена", example = "10000000")
    private BigDecimal price;

    @ApiModelProperty(value = "Площадь - m2", example = "45")
    private BigDecimal area;

    @ApiModelProperty(value = "Количество комнат", example = "2")
    private Integer roomsAmount;

    @ApiModelProperty(value = "Этаж", example = "12")
    private Integer floor;

    @ApiModelProperty(value = "Всего этажей в доме", example = "25")
    private Integer totalFloors;

    @ApiModelProperty(value = "Метро", example = "Кутузовская")
    private String metro;

    @ApiModelProperty(value = "Минут до метро", example = "15")
    private Integer metroTime;
}
