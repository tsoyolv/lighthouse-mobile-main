package ru.lighthouse.mobile.main.rest.controller.flat.mapper;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import ru.lighthouse.mobile.main.boot.property.DomainProperties;
import ru.lighthouse.mobile.main.rest.controller.flat.dto.FlatDetailsDto;
import ru.lighthouse.mobile.main.rest.controller.flat.dto.FlatImageDto;
import ru.lighthouse.mobile.main.rest.controller.flat.dto.FlatPriceHistoryDto;
import ru.lighthouse.mobile.main.service.flat.entity.Flat;
import ru.lighthouse.mobile.main.service.flat.entity.FlatDetails;
import ru.lighthouse.mobile.main.service.flat.entity.FlatImage;
import ru.lighthouse.mobile.main.service.flat.entity.FlatPriceHistory;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static ru.lighthouse.mobile.main.rest.controller.image.ImageController.IMAGES_URI;

@Component
@RequiredArgsConstructor
public class FlatDetailsDtoMapper {

    private final DomainProperties domainProperties;

    public FlatDetailsDto map(Flat flat) {
        FlatDetailsDto dto = new FlatDetailsDto();
        dto.setId(flat.getId());
        dto.setPoint(List.of(flat.getLongitude(), flat.getLatitude()));
        dto.setAddress(flat.getAddress());
        dto.setPrice(flat.getPrice());
        dto.setSquareMetrePrice(flat.getSquareMetrePrice());
        dto.setArea(flat.getArea());
        dto.setRoomsAmount(flat.getRoomsAmount());
        dto.setFloor(flat.getFloor());
        dto.setTotalFloors(flat.getTotalFloors());
        dto.setConstructionYear(flat.getConstructionYear());
        dto.setMetro(flat.getMetro());
        dto.setMetroTime(flat.getMetroTime());
        dto.setObjectType(flat.getObjectType());
        dto.setActive(flat.getActive());
        dto.setLastModifyDate(flat.getLastModifyDate());
        dto.setPriceHistory(mapPriceHistory(flat.getPriceHistory()));
        dto.setImages(mapImages(flat.getImages()));

        FlatDetails details = flat.getFlatDetails();
        dto.setTitle(details.getTitle());
        dto.setDescription(details.getDescription());
        dto.setKitchenArea(details.getKitchenArea());
        dto.setLivingArea(details.getLivingArea());
        dto.setBalcony(details.getBalcony());
        dto.setBalconyArea(details.getBalconyArea());
        dto.setLoggia(details.getLoggia());
        dto.setLoggiaArea(details.getLoggiaArea());
        dto.setBathroom(details.getBathroom());
        dto.setBathroomAmount(details.getBathroomAmount());
        return dto;
    }

    private List<FlatImageDto> mapImages(Set<FlatImage> images) {
        return images.stream()
                     .map(i -> new FlatImageDto(i.getName(),
                                                makeUrl(i.getUrl(), i.getFilePath())))
                     .collect(Collectors.toList());
    }

    private String makeUrl(String url, String filePath) {
        if (StringUtils.isNotEmpty(filePath)) {
            return domainProperties.getUrl()
                    + domainProperties.getServiceContextPath()
                    + IMAGES_URI
                    + "/" + filePath;
        } else {
            return url;
        }
    }

    private List<FlatPriceHistoryDto> mapPriceHistory(List<FlatPriceHistory> priceHistory) {
        return priceHistory.stream()
                           .map(p -> new FlatPriceHistoryDto(p.getPrice(), p.getModifyDate()))
                           .collect(Collectors.toList());
    }
}
