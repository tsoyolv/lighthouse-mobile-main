package ru.lighthouse.mobile.main.rest.service.flat.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import ru.lighthouse.mobile.main.core.rest.dto.DtoModel;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


@Data
public class FlatDetailsDto implements DtoModel {

    @ApiModelProperty(value = "Идентификатор объявления", position = -1)
    private Long id;

    @ApiModelProperty(value = "[LONGITUDE, LATITUDE]", example = "[37.54945,55.6622]")
    private List<BigDecimal> point;

    @ApiModelProperty(example = "Кутузовский проспект 23")
    private String address;

    @ApiModelProperty(value = "Цена", example = "10000000")
    private BigDecimal price;

    @ApiModelProperty(value = "Цена квадратного метра", example = "300000")
    private BigDecimal squareMetrePrice;

    @ApiModelProperty(value = "Площадь - m2", example = "45")
    private BigDecimal area;

    @ApiModelProperty(value = "Количество комнат", example = "2")
    private Integer roomsAmount;

    @ApiModelProperty(value = "Этаж", example = "12")
    private Integer floor;

    @ApiModelProperty(value = "Всего этажей в доме", example = "25")
    private Integer totalFloors;

    @ApiModelProperty(value = "Год постройки дома", example = "1999")
    private Integer constructionYear;

    @ApiModelProperty(value = "Метро", example = "Кутузовская")
    private String metro;

    @ApiModelProperty(value = "Минут до метро", example = "15")
    private Integer metroTime;

    @ApiModelProperty(value = "Первичка/вторичка")
    private String objectType;

    @ApiModelProperty(value = "Дата и время время обновления")
    private Date lastModifyDate;

    @ApiModelProperty(value = "Объявление активно/снято с публикации")
    private Boolean active;

    @ApiModelProperty(value = "История изменения цены")
    private List<FlatPriceHistoryDto> priceHistory;

    @ApiModelProperty(value = "Изображения")
    private List<FlatImageDto> images;


    // DETAILS

    @ApiModelProperty(value = "Заголовок")
    private String title;

    @ApiModelProperty(value = "Описание объявления")
    private String description;

    @ApiModelProperty(value = "Площадь кухни -  m2", example = "15")
    private BigDecimal kitchenArea;

    @ApiModelProperty(value = "Площадь жилая -  m2", example = "35")
    private BigDecimal livingArea;

    @ApiModelProperty(value = "Балкон - есть / нет")
    private Boolean balcony;

    @ApiModelProperty(value = "Площадь балкона -m2", example = "5")
    private BigDecimal balconyArea;

    @ApiModelProperty(value = "Лоджия - есть / нет")
    private Boolean loggia;

    @ApiModelProperty(value = "Лоджия площадь  -  m2", example = "10")
    private BigDecimal loggiaArea;

    @ApiModelProperty(value = "Санузел - совмещенный / раздельный")
    private String bathroom;

    @ApiModelProperty(value = "Санузел - шт.", example = "1")
    private Integer bathroomAmount;
}