package ru.lighthouse.mobile.main.core.rest.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SortedFieldDto implements DtoModel {

    @NotBlank
    private String fieldName;

    @NotBlank
    private String direction;
}
