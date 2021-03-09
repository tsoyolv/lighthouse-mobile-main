package ru.lighthouse.mobile.main.rest.service.realty.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.lighthouse.mobile.main.core.rest.dto.BboxDto;
import ru.lighthouse.mobile.main.core.rest.dto.FilterDto;
import ru.lighthouse.mobile.main.core.rest.dto.SearchCriteriaPlainDto;

import javax.validation.constraints.NotNull;
import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class RealtyMapFilterDto extends FilterDto {

    @ApiModelProperty(position = 0)
    @NotNull
    private BboxDto bbox;

    @ApiModelProperty(value = "Filter", example =
            "[\n" +
                    "    {\n" +
                    "      \"key\": \"objectType\",\n" +
                    "      \"operation\": \"=\",\n" +
                    "      \"value\": \"Новостройка\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"key\": \"address\",\n" +
                    "      \"operation\": \"like\",\n" +
                    "      \"value\": \"Кутузовс\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"key\": \"roomsAmount\",\n" +
                    "      \"operation\": \">=\",\n" +
                    "      \"value\": \"1\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"key\": \"roomsAmount\",\n" +
                    "      \"operation\": \"<=\",\n" +
                    "      \"value\": \"3\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"key\": \"price\",\n" +
                    "      \"operation\": \">=\",\n" +
                    "      \"value\": \"5000000\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"key\": \"price\",\n" +
                    "      \"operation\": \"<=\",\n" +
                    "      \"value\": \"15000000\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"key\": \"area\",\n" +
                    "      \"operation\": \">=\",\n" +
                    "      \"value\": \"40\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"key\": \"area\",\n" +
                    "      \"operation\": \"<=\",\n" +
                    "      \"value\": \"100\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"key\": \"kitchenArea\",\n" +
                    "      \"operation\": \">=\",\n" +
                    "      \"value\": \"10\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"key\": \"kitchenArea\",\n" +
                    "      \"operation\": \"<=\",\n" +
                    "      \"value\": \"20\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"key\": \"floor\",\n" +
                    "      \"operation\": \">=\",\n" +
                    "      \"value\": \"1\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"key\": \"floor\",\n" +
                    "      \"operation\": \"<=\",\n" +
                    "      \"value\": \"20\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"key\": \"wallMaterial\",\n" +
                    "      \"operation\": \"in\",\n" +
                    "      \"value\": [\"Монолитный\"]\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"key\": \"constructionYear\",\n" +
                    "      \"operation\": \">=\",\n" +
                    "      \"value\": \"1990\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"key\": \"constructionYear\",\n" +
                    "      \"operation\": \"<=\",\n" +
                    "      \"value\": \"2030\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"key\": \"bathroom\",\n" +
                    "      \"operation\": \"=\",\n" +
                    "      \"value\": \"Раздельный\"\n" +
                    "    }\n" +
                    "  ]"
            , position = 1)
    private List<SearchCriteriaPlainDto> filter;
}