package ru.lighthouse.mobile.main.rest.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.lighthouse.mobile.main.boot.tracktime.TrackExecutionTime;
import ru.lighthouse.mobile.main.core.rest.dto.BboxPageDto;
import ru.lighthouse.mobile.main.rest.service.realty.WebRealtyService;
import ru.lighthouse.mobile.main.rest.service.realty.dto.RealtyDto;
import ru.lighthouse.mobile.main.rest.service.realty.dto.RealtyMapFilterDto;

import javax.validation.Valid;


@Validated
@RestController
@RequestMapping("/map")
@RequiredArgsConstructor
@Api(tags = "1. Покупка. Карта объявлений. REST для работы картой объявлений")
public class RealtyMapController {
    private final WebRealtyService realtyService;

    @PostMapping("/realty")
    @ApiOperation("Карта в мобиле. Получение объектов по карте(BBOX). Можно применять фильтры.")
    @TrackExecutionTime
    public BboxPageDto<RealtyDto> getRealtyMap(@RequestBody @Valid RealtyMapFilterDto filterRequest) {
        return realtyService.getRealtyMap(filterRequest);
    }
}
