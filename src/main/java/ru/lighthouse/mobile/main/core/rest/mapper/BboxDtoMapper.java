package ru.lighthouse.mobile.main.core.rest.mapper;

import org.springframework.data.jpa.domain.Specification;
import ru.lighthouse.mobile.main.core.rest.dto.BboxDto;

import javax.validation.ValidationException;
import java.math.BigDecimal;
import java.math.RoundingMode;

import static java.util.Objects.isNull;

public class BboxDtoMapper {

    public static final int LATITUDE_SCALING = 6;
    public static final int LONGITUDE_SCALING = 6;

    public static void validateBbox(BboxDto bbox) {
        if (bbox == null || isNull(bbox.getLeftLowerCorner()) || isNull(bbox.getRightUpperCorner())
                || bbox.getLeftLowerCorner().size() != 2 || bbox.getRightUpperCorner().size() != 2) {
            throw new ValidationException("Bbox invalid!");
        }
    }

    public static <T, X, Y, Z> Specification<T> createBboxFilter(BboxDto bbox, String latFieldName, String lonFieldName, ThreeParameterFunction<String, String, BigDecimal, Specification<T>> createSpecFunc) {
        validateBbox(bbox);
        final BigDecimal leftLat = bbox.getLeftLowerCorner().get(1).setScale(LATITUDE_SCALING, RoundingMode.CEILING);
        final BigDecimal leftLon = bbox.getLeftLowerCorner().get(0).setScale(LONGITUDE_SCALING, RoundingMode.CEILING);
        final BigDecimal rightLat = bbox.getRightUpperCorner().get(1).setScale(LATITUDE_SCALING, RoundingMode.CEILING);
        final BigDecimal rightLon = bbox.getRightUpperCorner().get(0).setScale(LONGITUDE_SCALING, RoundingMode.CEILING);
        Specification<T> bboxFilter = createSpecFunc.apply(latFieldName, ">=", leftLat);
        bboxFilter = bboxFilter.and(createSpecFunc.apply(latFieldName, "<=", rightLat));
        bboxFilter = bboxFilter.and(createSpecFunc.apply(lonFieldName, ">=", leftLon));
        bboxFilter = bboxFilter.and(createSpecFunc.apply(lonFieldName, "<=", rightLon));
        return bboxFilter;
    }

    @FunctionalInterface
    public static interface ThreeParameterFunction<T, U, V, R> {
        public R apply(T t, U u, V v);
    }
}
