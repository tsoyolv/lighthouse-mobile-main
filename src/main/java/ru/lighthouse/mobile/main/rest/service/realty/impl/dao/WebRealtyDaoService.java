package ru.lighthouse.mobile.main.rest.service.realty.impl.dao;

import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.data.domain.Page;
import ru.lighthouse.mobile.main.core.rest.dto.BboxPageDto;
import ru.lighthouse.mobile.main.repository.flat.FlatRepository;
import ru.lighthouse.mobile.main.repository.flat.entity.Flat;
import ru.lighthouse.mobile.main.rest.service.realty.WebRealtyService;
import ru.lighthouse.mobile.main.rest.service.realty.dto.RealtyDto;
import ru.lighthouse.mobile.main.rest.service.realty.dto.RealtyMapFilterDto;
import ru.lighthouse.mobile.main.rest.service.realty.impl.dao.mapper.RealtyMapFilterMapper;

@RequiredArgsConstructor
public class WebRealtyDaoService implements WebRealtyService {
    private final FlatRepository repository;
    private final MapperFacade mapper;

    @Override
    public BboxPageDto<RealtyDto> getRealtyMap(RealtyMapFilterDto filterRequest) {
        RealtyMapFilterMapper.RealtyMapFilter filter = RealtyMapFilterMapper.map(filterRequest);
        final Page<Flat> page = repository.findAll(filter.specification, filter.pageable);
        BboxPageDto<RealtyDto> pageResp = new BboxPageDto<>();
        pageResp.setBbox(filterRequest.getBbox());
        pageResp.setTotalElements(page.getTotalElements());
        pageResp.setElements(mapper.mapAsList(page.getContent(), RealtyDto.class));
        return pageResp;
    }
}
