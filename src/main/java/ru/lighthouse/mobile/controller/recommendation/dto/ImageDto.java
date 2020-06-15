package ru.lighthouse.mobile.controller.recommendation.dto;

import lombok.Data;
import ru.lighthouse.mobile.mapper.model.DtoModel;

@Data
public class ImageDto implements DtoModel {
    private String url;
}
