package ru.lighthouse.mobile.main.rest.controller.flat;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.lighthouse.mobile.main.core.tracktime.TrackExecutionTime;
import ru.lighthouse.mobile.main.rest.controller.flat.dto.FlatDetailsDto;
import ru.lighthouse.mobile.main.rest.controller.flat.mapper.FlatDetailsDtoMapper;
import ru.lighthouse.mobile.main.service.flat.FlatService;

import javax.validation.constraints.Min;

import static ru.lighthouse.mobile.main.core.rest.CommonUri.URI_PART_ID;


@Validated
@RestController
@RequestMapping("/flat")
@RequiredArgsConstructor
@Api(tags = "2. Покупка. Объявления о квартирах. REST для работы с квартирами")
public class FlatController {
    private final FlatService flatService;
    private final FlatDetailsDtoMapper flatDetailsDtoMapper;

    @ApiOperation("Получение всей информации по объявлению")
    @GetMapping(URI_PART_ID)
    @TrackExecutionTime
    public FlatDetailsDto getById(@PathVariable @Min(1) Long id) {
        return flatService.getDetails(id)
                .map(flatDetailsDtoMapper::map)
                .orElseThrow();
    }
}
