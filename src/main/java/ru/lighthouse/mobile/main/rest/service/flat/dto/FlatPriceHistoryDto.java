package ru.lighthouse.mobile.main.rest.service.flat.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import ru.lighthouse.mobile.main.core.rest.dto.DtoModel;

import java.math.BigDecimal;
import java.util.Date;


@Data
@AllArgsConstructor
public class FlatPriceHistoryDto implements DtoModel {

    @ApiModelProperty(value = "Цена", example = "10000000")
    private BigDecimal price;

    @ApiModelProperty(value = "Дата изменения")
    private Date modifyDate;
}