package ru.lighthouse.mobile.main.repository.building.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.util.StringUtils;
import ru.lighthouse.mobile.main.core.dao.EntityModel;
import ru.lighthouse.mobile.main.repository.flat.entity.Flat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "BUILDING")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Building implements EntityModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    @ToString.Exclude
    private Long id;

    /**
     * История изменения цены
     */
    @OneToMany(mappedBy = "building")
    @ToString.Exclude
    private Set<Flat> flats = new HashSet<>();

    /**
     * Тип строения
     */
    @Column(name = "DISTRICT_ID")
    private Long districtId;

    /**
     * Тип строения
     */
    @Column(name = "BUILDING_TYPE_ID")
    private Long buildingTypeId;

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
     * Высота потолков - см.
     */
    @Column(name = "CEILING_HEIGHT")
    private Integer ceilingHeight;

    /**
     * Срок сдачи (для новостроек)
     */
    @Column(name = "DELIVERY_TERM")
    private String deliveryTerm;

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

    public interface Columns {
        int LATITUDE_SCALING = 6;
        int LONGITUDE_SCALING = 6;
    }

    public void setWallMaterial(String wallMaterial) {
        if (StringUtils.isEmpty(wallMaterial)) {
            return;
        }
        if (wallMaterial.toLowerCase().contains("кирпич")) {
            this.wallMaterial = "Кирпичный";
        }
        if (wallMaterial.toLowerCase().contains("монолит")) {
            this.wallMaterial = "Монолитный";
        }
        if (wallMaterial.toLowerCase().contains("сталин")) {
            this.wallMaterial = "Сталинка";
        }
        if (wallMaterial.toLowerCase().contains("панел")) {
            this.wallMaterial = "Панельный";
        }
        if (wallMaterial.toLowerCase().contains("блоч")) {
            this.wallMaterial = "Блочный";
        }
        if (wallMaterial.toLowerCase().contains("хрущев")) {
            this.wallMaterial = "Хрущевка";
        }
        this.wallMaterial = wallMaterial;
    }
}
