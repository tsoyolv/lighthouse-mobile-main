package ru.lighthouse.mobile.main.core.rest.dto;

import lombok.Data;

import java.util.List;


@Data
public class PageResponseDto<DTO extends DtoModel> implements DtoModel {
    private Integer currentPage;

    private Integer pageSize;

    private Integer totalPages;

    private Long totalElements;

    private List<DTO> elements;
}