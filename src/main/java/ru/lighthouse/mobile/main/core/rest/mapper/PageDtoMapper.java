package ru.lighthouse.mobile.main.core.rest.mapper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import ru.lighthouse.mobile.main.core.dao.EntityModel;
import ru.lighthouse.mobile.main.core.rest.dto.DtoModel;
import ru.lighthouse.mobile.main.core.rest.dto.PageRequestDto;
import ru.lighthouse.mobile.main.core.rest.dto.PageResponseDto;
import ru.lighthouse.mobile.main.core.rest.dto.SortedFieldDto;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;


public class PageDtoMapper {
    public static final int MAX_PAGE_SIZE = 1000;

    public static Pageable map(PageRequestDto src) {
        if (isNull(src)) {
            src = new PageRequestDto(0, 10);
        }
        int size = src.getSize() > MAX_PAGE_SIZE ? MAX_PAGE_SIZE : src.getSize();
        int page = src.getPage();
        if (CollectionUtils.isEmpty(src.getSortedFields())) {
            return PageRequest.of(page, size);
        } else {
            Optional<Sort> reducedOpt = src.getSortedFields().stream()
                    .map(PageDtoMapper::mapFieldToSort)
                    .reduce(PageDtoMapper::reduce);
            return reducedOpt.
                    map(orders -> PageRequest.of(page, size, orders))
                    .orElseGet(() -> PageRequest.of(page, size));
        }
    }

    public static <S extends EntityModel, D extends DtoModel> PageResponseDto<D> map(Page<S> src, List<D> content){
        PageResponseDto<D> pageResponse = new PageResponseDto<>();
        pageResponse.setCurrentPage(src.getNumber());
        pageResponse.setPageSize(src.getSize());
        pageResponse.setTotalPages(src.getTotalPages());
        pageResponse.setTotalElements(src.getTotalElements());
        pageResponse.setElements(content);
        return pageResponse;
    }

    private static Sort reduce(Sort sort1, Sort sort2) {
        return sort1.and(sort2);
    }

    private static Sort mapFieldToSort(SortedFieldDto f) {
        if (StringUtils.isEmpty(f.getDirection())) {
            f.setDirection(Sort.Direction.ASC.name());
        }
        return Sort.by(Sort.Direction.valueOf(f.getDirection()), f.getFieldName());
    }
}
