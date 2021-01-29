package ru.lighthouse.mobile.main.rest.controller.recommendation.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.lighthouse.mobile.main.rest.controller.recommendation.dto.CoordinateDto;
import ru.lighthouse.mobile.main.rest.controller.recommendation.dto.BuildingDto;
import ru.lighthouse.mobile.main.rest.controller.recommendation.dto.FlatMainDetailsDto;
import ru.lighthouse.mobile.main.rest.controller.recommendation.dto.ImageDto;
import ru.lighthouse.mobile.main.rest.controller.recommendation.dto.RecommendationFlatDto;
import ru.lighthouse.mobile.main.core.rest.mapper.CustomEntityDtoMapper;
import ru.lighthouse.mobile.main.service.flat.FlatService;
import ru.lighthouse.mobile.main.service.flat.entity.FlatImage;
import ru.lighthouse.mobile.main.service.flat.entity.FlatInfo;
import ru.lighthouse.mobile.main.service.recommendation.entity.RecommendationFlat;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RecommendationMapper implements CustomEntityDtoMapper<RecommendationFlat, RecommendationFlatDto> {
    private final FlatService flatService;

    @Override
    public RecommendationFlat map(RecommendationFlatDto dto) {
        return null;
    }

    @Override
    public RecommendationFlatDto map(RecommendationFlat recommendation) {
        final FlatInfo flat = flatService.getFlatInfo(recommendation.getFlatId());
        final List<FlatImage> images = flatService.getImagesByFlatId(flat.getId());
        RecommendationFlatDto dto = new RecommendationFlatDto();
        dto.setPrice(flat.getPrice());
        dto.setSquareMetrePrice(flat.getSquareMetrePrice());
        dto.setArea(flat.getArea());
        dto.setRoomsAmount(flat.getRoomsAmount());
        dto.setFloor(flat.getFloor());
        dto.setTotalFloors(flat.getTotalFloors());
        dto.setAddress(flat.getAddress());
        dto.setMetro(flat.getMetro());
        dto.setDescription(recommendation.getDescription());
        dto.setCoordinates(mapCoordinate(flat));
        dto.setBuilding(mapBuilding(recommendation));
        dto.setDetails(mapDetails(recommendation));
        dto.setTimeToMetro(recommendation.getMetroTime());
        dto.setImages(mapImages(images));
        return dto;
    }

    private List<ImageDto> mapImages(List<FlatImage> images) {
        return images.stream().map(i -> {
            ImageDto imageDto = new ImageDto();
            imageDto.setUrl(i.getUrl());
            return imageDto;
        }).collect(Collectors.toList());
    }

    private FlatMainDetailsDto mapDetails(RecommendationFlat recommendation) {
        FlatMainDetailsDto dto = new FlatMainDetailsDto();
        dto.setBathroom(recommendation.getBathroom());
        dto.setCeilingHeight(recommendation.getCeilingHeight());
        dto.setLivingArea(recommendation.getLivingArea());
        dto.setRedecoration(recommendation.getRedecoration());
        return dto;
    }

    private BuildingDto mapBuilding(RecommendationFlat recommendation) {
        BuildingDto dto = new BuildingDto();
        dto.setConstructionYear(recommendation.getConstructionYear());
        dto.setHeating(recommendation.getHeating());
        //dto.setLifts(recommendation.getElevatorAmount());
        dto.setParking(recommendation.getParking());
        dto.setPorchAmount(recommendation.getPorchAmount());
        dto.setWallMaterial(recommendation.getWallMaterial());
        return dto;
    }

    private CoordinateDto mapCoordinate(FlatInfo flat) {
        CoordinateDto dto = new CoordinateDto();
        dto.setLatitude(flat.getLatitude());
        dto.setLongitude(flat.getLongitude());
        return dto;
    }
}
