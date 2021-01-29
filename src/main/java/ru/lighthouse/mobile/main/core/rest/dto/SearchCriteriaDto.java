package ru.lighthouse.mobile.main.core.rest.dto;


import lombok.Data;

import java.util.List;

@Data
public class SearchCriteriaDto implements DtoModel {
    private String key;
    private String operation;
    private Object value;

    private List<SearchCriteriaDto> and;
    private List<SearchCriteriaDto> or;
}
