package ru.lighthouse.mobile.logic.recommendation.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.lighthouse.mobile.mapper.model.EntityModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity @Table(name = "RECOMMENDATION_FLAT")
@Getter @Setter @NoArgsConstructor @ToString
public class RecommendationFlat implements EntityModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    @ToString.Exclude
    private Long id;

    /** Идентификатор квартиры */
    @Column(name = "FLAT_ID")
    private Long flatId;

    /** Описание объявления */
    @Column(name = "DESCRIPTION")
    private String description;

    /** Площадь кухни -  m2 */
    @Column(name = "KITCHEN_AREA")
    private Float kitchenArea;

    /** Площадь жилая -  m2 */
    @Column(name = "LIVING_AREA")
    private Float livingArea;

    /** Высота потолков - см. */
    @Column(name = "CEILING_HEIGHT")
    private Integer ceilingHeight;

    /** Ремонт - задачи для машин лёнинга и компьютерного зрения (полнотекстовый парсинг косметический / евро / дизайнерский) */
    @Column(name = "REDECORATION")
    private String redecoration;

    /** Санузел - совмещенный / раздельный */
    @Column(name = "BATHROOM")
    private String bathroom;

    /** Санузел - шт. */
    @Column(name = "BATHROOM_AMOUNT")
    private Integer bathroomAmount;

    /** Год постройки */
    @Column(name = "CONSTRUCTION_YEAR")
    private Integer constructionYear;

    /** Материал стен */
    @Column(name = "WALL_MATERIAL")
    private String wallMaterial;

    /** Кол-во обычных лифтов */
    @Column(name = "ELEVATOR_AMOUNT")
    private Integer elevatorAmount;

    /** Кол-во грузовых лифтов */
    @Column(name = "SERVICE_ELEVATOR_AMOUNT")
    private Integer serviceElevatorAmount;

    /** Кол-во подъездов */
    @Column(name = "PORCH_AMOUNT")
    private Integer porchAmount;

    /** Отопление */
    @Column(name = "HEATING")
    private String heating;

    /** Парковка */
    @Column(name = "PARKING")
    private String parking;

    /** Комментарий эксперта */
    @Column(name = "EXPERT_COMMENT")
    private String expertComment;

    /** Идентификатор эксперта */
    @Column(name = "EXPERT_ID")
    private Long expertId;

    /** Идентификатор пользователя */
    @Column(name = "USER_ID")
    private Long userId;
}
