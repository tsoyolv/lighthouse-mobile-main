package ru.lighthouse.mobile.main.core.rest.mapper;


import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;
import ru.lighthouse.mobile.main.core.dao.EntityModel;
import ru.lighthouse.mobile.main.core.dao.search.SearchCriteria;
import ru.lighthouse.mobile.main.core.dao.search.SearchSpecification;
import ru.lighthouse.mobile.main.core.rest.dto.PageRequestDto;
import ru.lighthouse.mobile.main.core.rest.dto.SearchCriteriaDto;
import ru.lighthouse.mobile.main.core.rest.dto.SearchCriteriaPlainDto;

import java.util.Iterator;
import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;


public final class SearchCriteriaMapper {

    public static <T extends EntityModel> Specification<T> getFromPage(PageRequestDto page) {
        if (nonNull(page)) {
            if (nonNull(page.getFilter())) {
                return map(page.getFilter());
            }
        }
        return null;
    }

    public static <T extends EntityModel> Specification<T> map(SearchCriteriaDto dto) {
        if (isNull(dto)) {
            return null;
        }
        Specification<T> searchSpec = convert(dto);
        List<SearchCriteriaDto> and = dto.getAnd();
        List<SearchCriteriaDto> or = dto.getOr();
        if (!CollectionUtils.isEmpty(and)) {
            for (SearchCriteriaDto dt : and) {
                Specification<T> spec = map(dt);
                if (nonNull(spec)) {
                    searchSpec = searchSpec.and(spec);
                }
            }
        }
        if (!CollectionUtils.isEmpty(or)) {
            for (SearchCriteriaDto dt : or) {
                Specification<T> spec = map(dt);
                if (nonNull(spec)) {
                    searchSpec = searchSpec.or(spec);
                }
            }
        }
        return searchSpec;
    }

    public static <T extends EntityModel> Specification<T> map(List<SearchCriteriaPlainDto> dtos) {
        if (CollectionUtils.isEmpty(dtos)) {
            return null;
        }
        final Iterator<SearchCriteriaPlainDto> iterator = dtos.iterator();
        Specification<T> searchSpec = convert(iterator.next());
        while (iterator.hasNext()) {
            searchSpec = searchSpec.and(convert(iterator.next()));
        }
        return searchSpec;
    }

    private static<T extends EntityModel> Specification<T> convert(SearchCriteriaDto dto) {
        return new SearchSpecification<>(new SearchCriteria(dto.getKey(), dto.getOperation(), dto.getValue()));
    }

    private static<T extends EntityModel> Specification<T> convert(SearchCriteriaPlainDto dto) {
        return new SearchSpecification<>(new SearchCriteria(dto.getKey(), dto.getOperation(), dto.getValue()));
    }
}
