package ru.lighthouse.mobile.main.rest.service.flat.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.lighthouse.mobile.main.core.rest.dto.DtoModel;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlatImageDto implements DtoModel {

    @ApiModelProperty(value = "Наименование изображения", example = "0", position = 1)
    private String name;

    @ApiModelProperty(value = "URL для скачивания", example = "/url", position = 2)
    private String url;
}