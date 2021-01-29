package ru.lighthouse.mobile.main.core.rest.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;


@Data
public class BboxPageDto<DTO extends DtoModel> implements DtoModel {

    @ApiModelProperty(value = "Bounding box for filtering elements", position = 0)
    private BboxDto bbox;

    @ApiModelProperty(value = "Total elements amount", example = "1", position = 1)
    private Long totalElements;

    @ApiModelProperty(value = "Elements", position = 2)
    private List<DTO> elements;
}