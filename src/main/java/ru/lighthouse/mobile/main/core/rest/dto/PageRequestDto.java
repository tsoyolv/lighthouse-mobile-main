package ru.lighthouse.mobile.main.core.rest.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

import static ru.lighthouse.mobile.main.core.rest.mapper.PageDtoMapper.MAX_PAGE_SIZE;

@Data
@NoArgsConstructor
public class PageRequestDto implements DtoModel {
    public PageRequestDto(int page, int size) {
        this.page = page;
        this.size = size;
    }

    @PositiveOrZero
    @NotNull
    private Integer page;

    @PositiveOrZero
    @NotNull
    @Max(MAX_PAGE_SIZE)
    private Integer size;

    private SearchCriteriaDto filter;

    private List<SortedFieldDto> sortedFields;
}
