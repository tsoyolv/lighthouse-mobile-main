package ru.lighthouse.mobile.main.rest.controller.flat;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.lighthouse.mobile.main.rest.controller.flat.dto.FlatDetailsDto;
import ru.lighthouse.mobile.main.service.flat.FlatService;

import javax.validation.constraints.Min;

import static ru.lighthouse.mobile.main.core.rest.CommonUri.URI_PART_ID;


@Validated
@RestController
@RequestMapping("/flat")
@RequiredArgsConstructor
@Api(tags = "2. Покупка. Объявления о квартирах. REST для работы с квартирами")
public class FlatController {
    private final MapperFacade mapper;
    private final FlatService flatService;

    @ApiOperation("В разработке!. Получение всей информации по объявлению")
    @GetMapping(URI_PART_ID)
    public FlatDetailsDto getById(@PathVariable @Min(1) Long id) {
        return flatService.get(id)
                .map(this::mapEntityToDto)
                .orElseThrow();
    }

    private FlatDetailsDto mapEntityToDto(ru.lighthouse.mobile.main.service.flat.entity.Flat f) {
        return mapper.map(f, FlatDetailsDto.class);
    }
}
