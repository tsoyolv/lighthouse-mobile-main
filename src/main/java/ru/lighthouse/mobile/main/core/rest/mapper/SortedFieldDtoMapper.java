package ru.lighthouse.mobile.main.core.rest.mapper;

import org.springframework.data.domain.Sort;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import ru.lighthouse.mobile.main.core.rest.dto.SortedFieldDto;

import java.util.List;

public class SortedFieldDtoMapper {

    public static Sort map(List<SortedFieldDto> sortedFields) {
        if (CollectionUtils.isEmpty(sortedFields)) {
            return null;
        }
        return sortedFields.stream()
                           .map(SortedFieldDtoMapper::mapFieldToSort)
                           .reduce(SortedFieldDtoMapper::reduce).orElse(null);
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
