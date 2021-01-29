package ru.lighthouse.mobile.main.rest.controller.recommendation.dto;

import lombok.Data;
import ru.lighthouse.mobile.main.core.mapper.model.DtoModel;

@Data
public class ImageDto implements DtoModel {
    private String url;
}
