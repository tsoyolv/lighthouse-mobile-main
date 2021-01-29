package ru.lighthouse.mobile.main.service.flat.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.lighthouse.mobile.main.core.mapper.model.EntityModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

@Entity @Table(name = "FLAT")
@Getter @Setter @NoArgsConstructor @ToString
public class FlatInfo implements EntityModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    @ToString.Exclude
    private Long id;

    /** Цена - рубли */
    @Column(name = "PRICE")
    private BigDecimal price = new BigDecimal(-1);

    /** Цена квадратного метра - тыс.руб / м2 */
    @Column(name = "SQUARE_METRE_PRICE")
    private BigDecimal squareMetrePrice;

    /** Адрес - Улица / номер дома / корпус / строение. */
    @Column(name = "ADDRESS")
    private String address = "NO_ADDRESS";

    /** Метро или район */
    @Column(name = "METRO")
    private String metro;

    /** Площадь - m2 */
    @Column(name = "AREA")
    private BigDecimal area;

    /** Количество комнат - шт. */
    @Column(name = "ROOMS_AMOUNT")
    private Integer roomsAmount = -1;

    /** Этаж */
    @Column(name = "FLOOR")
    private Integer floor = -1;

    /** Всего этажей */
    @Column(name = "TOTAL_FLOORS")
    private Integer totalFloors;

    /** Широта */
    @Column(name = "LATITUDE")
    private BigDecimal latitude;

    /** Долгота */
    @Column(name = "LONGITUDE")
    private BigDecimal longitude;

    /** Дата и время время обновления */
    @Column(name = "LAST_MODIFY_DATE")
    private Date lastModifyDate = new Date();

    /** Расстояние до ближайшего метро в километрах. Если 0, то расстояние неизвестно. */
    @Column(name = "METRO_DISTANCE")
    private Integer metroDistance;

    /** Идентификатор дома */
    @Column(name = "BUILDING_ID")
    private Long buildingId;

    public static class Columns {
        public static final int COORDINATE_SCALING = 6;
        public static final int PRICE_SCALING = 2;
        public static final int AREA_SCALING = 1;
        public static final RoundingMode COMMON_ROUND_MODE = RoundingMode.CEILING;
    }
}
