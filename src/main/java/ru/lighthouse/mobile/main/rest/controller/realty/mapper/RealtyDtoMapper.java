package ru.lighthouse.mobile.main.rest.controller.realty.mapper;

import ma.glasnost.orika.MapperFactory;
import org.springframework.stereotype.Component;
import ru.lighthouse.mobile.main.core.rest.mapper.MapperRegister;
import ru.lighthouse.mobile.main.rest.controller.realty.dto.RealtyDto;
import ru.lighthouse.mobile.main.service.flat.entity.Flat;

@Component
public class RealtyDtoMapper implements MapperRegister {

    @Override
    public void register(MapperFactory mapperFactory) {
        mapperFactory.classMap(RealtyDto.class, Flat.class).
                field("id", "id").
                             field("point[0]", "longitude").
                             field("point[1]", "latitude").
                             field("address", "address").
                             field("price", "price").
                             field("squareMetrePrice", "squareMetrePrice").
                             field("area", "area").
                             field("roomsAmount", "roomsAmount").
                             field("floor", "floor").
                             field("totalFloors", "totalFloors").
                             field("constructionYear", "constructionYear").
                             field("metro", "metro").
                             field("metroTime", "metroTime").
                             field("objectType", "objectType").
                             field("active", "active").
                             field("lastModifyDate", "lastModifyDate").
                             register();
    }
}
