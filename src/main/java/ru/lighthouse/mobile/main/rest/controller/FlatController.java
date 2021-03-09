package ru.lighthouse.mobile.main.rest.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.lighthouse.mobile.main.boot.tracktime.TrackExecutionTime;
import ru.lighthouse.mobile.main.rest.service.flat.WebFlatService;
import ru.lighthouse.mobile.main.rest.service.flat.dto.FlatDetailsDto;

import javax.validation.constraints.Min;

import static ru.lighthouse.mobile.main.core.rest.CommonUri.URI_PART_ID;


@Validated
@RestController
@RequestMapping("/flat")
@RequiredArgsConstructor
@Api(tags = "2. Покупка. Объявления о квартирах. REST для работы с квартирами")
public class FlatController {
    private final WebFlatService flatService;

    @ApiOperation("Получение всей информации по объявлению")
    @GetMapping(URI_PART_ID)
    @TrackExecutionTime
    public FlatDetailsDto getById(@PathVariable @Min(1) Long id) {
        return flatService.getById(id);
    }
}
