package ru.lighthouse.mobile.main.core.rest.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class FilterDto implements DtoModel {

    @ApiModelProperty(value = "Filter for elements", position = 98)
    private List<SearchCriteriaPlainDto> filter;

    @ApiModelProperty(value = "Fields for sorting", position = 99)
    private List<SortedFieldDto> sortedFields;
}
