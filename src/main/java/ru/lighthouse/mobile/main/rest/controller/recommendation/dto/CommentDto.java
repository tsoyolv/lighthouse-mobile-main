package ru.lighthouse.mobile.main.rest.controller.recommendation.dto;

import lombok.Data;
import ru.lighthouse.mobile.main.core.mapper.model.DtoModel;

import java.util.Date;

@Data
public class CommentDto implements DtoModel {
    private String comment;
    private String author;
    private Date date;
}
