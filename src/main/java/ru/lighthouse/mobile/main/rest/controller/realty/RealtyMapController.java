package ru.lighthouse.mobile.main.rest.controller.realty;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.lighthouse.mobile.main.core.rest.dto.BboxPageDto;
import ru.lighthouse.mobile.main.config.tracktime.TrackExecutionTime;
import ru.lighthouse.mobile.main.rest.controller.realty.dto.RealtyDto;
import ru.lighthouse.mobile.main.rest.controller.realty.dto.RealtyMapFilterDto;
import ru.lighthouse.mobile.main.rest.controller.realty.mapper.RealtyMapFilterMapper;
import ru.lighthouse.mobile.main.service.flat.FlatService;
import ru.lighthouse.mobile.main.service.flat.entity.Flat;

import javax.validation.Valid;
import java.util.List;


@Validated
@RestController
@RequestMapping("/map")
@RequiredArgsConstructor
@Api(tags = "1. Покупка. Карта объявлений. REST для работы картой объявлений")
public class RealtyMapController {
    private final MapperFacade mapper;
    private final FlatService flatService;

    @PostMapping("/realty")
    @ApiOperation("Карта в мобиле. Получение объектов по карте(BBOX). Можно применять фильтры.")
    @TrackExecutionTime
    public BboxPageDto<RealtyDto> getRealtyMap(@RequestBody @Valid RealtyMapFilterDto filterRequest) {
        RealtyMapFilterMapper.RealtyMapFilter filter = RealtyMapFilterMapper.map(filterRequest);
        final Page<Flat> page = flatService.getPage(filter.specification, filter.pageable);
        BboxPageDto<RealtyDto> pageResp = new BboxPageDto<>();
        pageResp.setBbox(filterRequest.getBbox());
        pageResp.setTotalElements(page.getTotalElements());
        pageResp.setElements(mapList(page));
        return pageResp;
    }

    private List<RealtyDto> mapList(Page<Flat> page) {
        return mapper.mapAsList(page.getContent(), RealtyDto.class);
    }
}
