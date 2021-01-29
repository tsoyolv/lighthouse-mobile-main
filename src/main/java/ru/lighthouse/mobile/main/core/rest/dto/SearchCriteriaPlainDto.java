package ru.lighthouse.mobile.main.core.rest.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SearchCriteriaPlainDto implements DtoModel {

    @ApiModelProperty(value = "Entity field name", example = "address", position = 1)
    private String key;

    @ApiModelProperty(value = "Compare operation", example = "like", position = 2, allowableValues = ">,<,=,like,<=,>=")
    private String operation;

    @ApiModelProperty(value = "Value", example = "Черемушкинская", position = 3)
    private Object value;
}
