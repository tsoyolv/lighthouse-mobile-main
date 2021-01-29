package ru.lighthouse.mobile.main.core.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Data @NoArgsConstructor
@AllArgsConstructor
public class FromToIntegerDto implements DtoModel {

    @NotNull @PositiveOrZero
    private Integer from;

    @NotNull @PositiveOrZero
    private Integer to;
}
