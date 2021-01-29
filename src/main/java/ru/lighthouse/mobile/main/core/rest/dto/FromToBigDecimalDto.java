package ru.lighthouse.mobile.main.core.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data @NoArgsConstructor @AllArgsConstructor
public class FromToBigDecimalDto implements DtoModel {

    @NotNull
    private BigDecimal from;

    @NotNull
    private BigDecimal to;
}
