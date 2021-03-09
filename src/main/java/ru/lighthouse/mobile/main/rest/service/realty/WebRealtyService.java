package ru.lighthouse.mobile.main.rest.service.realty;

import ru.lighthouse.mobile.main.core.rest.dto.BboxPageDto;
import ru.lighthouse.mobile.main.rest.service.realty.dto.RealtyDto;
import ru.lighthouse.mobile.main.rest.service.realty.dto.RealtyMapFilterDto;

public interface WebRealtyService {
    
    BboxPageDto<RealtyDto> getRealtyMap(RealtyMapFilterDto filterRequest);
}
