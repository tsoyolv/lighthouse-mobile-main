package ru.lighthouse.mobile.main.service.flat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.lighthouse.mobile.main.service.flat.entity.Flat;

import java.math.BigDecimal;
import java.util.stream.Stream;

@Repository
public interface FlatRepository extends JpaRepository<Flat, Long>, JpaSpecificationExecutor<Flat> {
    Stream<Flat> findAllByBuilding_IdAndFloor(Long buildingId, Integer floor);
    Stream<Flat> findAllByBuilding_IdAndFloorAndRoomsAmount(Long buildingId, Integer floor, Integer roomsAmount);
    Stream<Flat> findAllByBuilding_IdAndFloorAndRoomsAmountAndArea(Long buildingId, Integer floor, Integer roomsAmount, BigDecimal area);
    Stream<Flat> findAllByBuilding_IdAndFloorAndRoomsAmountAndAreaAndPrice(Long buildingId, Integer floor, Integer roomsAmount, BigDecimal area, BigDecimal price);
    //Optional<Flat> findByFlatDetails_ExternalIdOrFlatDetails_Url(String externalId, String url);
}
