package ru.lighthouse.mobile.main.core.rest.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
public class BboxDto implements DtoModel {

    @ApiModelProperty(value = "[LONGITUDE, LATITUDE]", example = "[37.54945,55.6622]")
    @NotNull
    private List<BigDecimal> leftLowerCorner;

    @ApiModelProperty(value = "[LONGITUDE, LATITUDE]", example = "[37.6494,55.6822]")
    @NotNull
    private List<BigDecimal> rightUpperCorner;
}
