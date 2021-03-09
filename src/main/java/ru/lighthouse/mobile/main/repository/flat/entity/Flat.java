package ru.lighthouse.mobile.main.repository.flat.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.lighthouse.mobile.main.core.dao.EntityModel;
import ru.lighthouse.mobile.main.repository.building.entity.Building;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static ru.lighthouse.mobile.main.core.DbDataTypeConstants.MONEY_SCALING;

@Entity
@Table(name = "FLAT")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Flat implements EntityModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    @ToString.Exclude
    private Long id;

    /**
     * Детальная таблица квартир
     */
    @OneToOne(mappedBy = "flat", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    @ToString.Exclude
    private FlatDetails flatDetails;

    @ManyToOne
    @JoinColumn(name = "BUILDING_ID", nullable = false)
    @ToString.Exclude
    private Building building;

    /**
     * История изменения цены
     */
    @OneToMany(mappedBy = "flat")
    @ToString.Exclude
    private List<FlatPriceHistory> priceHistory = new ArrayList<>();

    /**
     * фотографии
     */
    @OneToMany(mappedBy = "flat")
    @ToString.Exclude
    private Set<FlatImage> images = new HashSet<>();

    /**
     * Цена - рубли
     */
    @Column(name = "PRICE", nullable = false)
    private BigDecimal price;

    /**
     * Цена квадратного метра - тыс.руб / м2
     */
    @Column(name = "SQUARE_METRE_PRICE", nullable = false)
    private BigDecimal squareMetrePrice;

    /**
     * Площадь - m2
     */
    @Column(name = "AREA", nullable = false)
    private BigDecimal area;

    /**
     * Количество комнат - шт.
     */
    @Column(name = "ROOMS_AMOUNT", nullable = false)
    private Integer roomsAmount;

    /**
     * Этаж
     */
    @Column(name = "FLOOR", nullable = false)
    private Integer floor;

    /**
     * Дата и время время обновления
     */
    @Column(name = "LAST_MODIFY_DATE", nullable = false)
    private Date lastModifyDate = new Date();

    /**
     * Объявление активно/снято с публикации
     */
    @Column(name = "ACTIVE", nullable = false)
    private Boolean active;

    /**
     * Тип объекта (Вторичка/Новостройка)
     */
    @Column(name = "OBJECT_TYPE", nullable = false)
    private String objectType;

    /**
     * Адрес - Улица / номер дома / корпус / строение.
     */
    @Column(name = "ADDRESS")
    private String address;

    /**
     * Полный Адрес
     */
    @Column(name = "FULL_ADDRESS", nullable = false)
    private String fullAddress;

    /**
     * Широта
     */
    @Column(name = "LATITUDE", nullable = false)
    private BigDecimal latitude;

    /**
     * Долгота
     */
    @Column(name = "LONGITUDE", nullable = false)
    private BigDecimal longitude;

    /**
     * Этажность
     */
    @Column(name = "TOTAL_FLOORS", nullable = false)
    private Integer totalFloors;

    /**
     * Год постройки
     */
    @Column(name = "CONSTRUCTION_YEAR")
    private Integer constructionYear;

    /**
     * Материал стен
     */
    @Column(name = "WALL_MATERIAL")
    private String wallMaterial;

    /**
     * Метро
     */
    @Column(name = "METRO")
    private String metro;

    /**
     * Расстояние до ближайшего метро в километрах.
     */
    @Column(name = "METRO_DISTANCE")
    private Integer metroDistance;

    /**
     * Время до ближайшего метро
     */
    @Column(name = "METRO_TIME")
    private Integer metroTime;

    /**
     * Площадь кухни -  m2
     */
    @Column(name = "KITCHEN_AREA")
    private BigDecimal kitchenArea;

    /**
     * Санузел - совмещенный / раздельный
     */
    @Column(name = "BATHROOM")
    private String bathroom;

    public interface Columns {
        int AREA_SCALING = 1;
        int SQUARE_METRE_PRICE_SCALING = MONEY_SCALING;
        int PRICE_SCALING = MONEY_SCALING;
    }
}
