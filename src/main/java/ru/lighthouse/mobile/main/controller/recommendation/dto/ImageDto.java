package ru.lighthouse.mobile.main.controller.recommendation.dto;

import lombok.Data;
import ru.lighthouse.mobile.main.mapper.model.DtoModel;

@Data
public class ImageDto implements DtoModel {
    private String url;
}
