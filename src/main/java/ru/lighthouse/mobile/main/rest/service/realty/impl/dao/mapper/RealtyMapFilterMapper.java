package ru.lighthouse.mobile.main.rest.service.realty.impl.dao.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import ru.lighthouse.mobile.main.core.dao.search.SearchCriteria;
import ru.lighthouse.mobile.main.core.dao.search.SearchSpecification;
import ru.lighthouse.mobile.main.core.rest.mapper.BboxDtoMapper;
import ru.lighthouse.mobile.main.core.rest.mapper.SearchCriteriaMapper;
import ru.lighthouse.mobile.main.core.rest.mapper.SortedFieldDtoMapper;
import ru.lighthouse.mobile.main.rest.service.realty.dto.RealtyMapFilterDto;
import ru.lighthouse.mobile.main.repository.flat.entity.Flat;
import org.springframework.data.domain.Sort;

public class RealtyMapFilterMapper {

    public static RealtyMapFilter map(RealtyMapFilterDto filterDto) {
        Specification<Flat> specification = BboxDtoMapper.createBboxFilter(filterDto.getBbox(), "latitude", "longitude", RealtyMapFilterMapper::createSpec);
        specification = specification.and(SearchCriteriaMapper.map(filterDto.getFilter()));
        Sort sort = SortedFieldDtoMapper.map(filterDto.getSortedFields());
        Pageable pageable;
        if (sort == null) {
            pageable = PageRequest.of(0, 1000);
        } else {
            pageable = PageRequest.of(0, 1000, sort);
        }
        return new RealtyMapFilter(specification, pageable);
    }

    private static Specification<Flat> createSpec(String fieldName, String operation, Object value) {
        return new SearchSpecification<>(new SearchCriteria(fieldName, operation, value));
    }

    @RequiredArgsConstructor
    public static class RealtyMapFilter {
        public final Specification<Flat> specification;
        public final Pageable pageable;
    }
}
