package ru.lighthouse.mobile.main.repository.flat.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.lighthouse.mobile.main.core.dao.EntityModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

import static ru.lighthouse.mobile.main.core.DbDataTypeConstants.MONEY_SCALING;


@Entity
@Table(name = "FLAT_DETAILS")
@Getter
@Setter
@NoArgsConstructor
public class FlatDetails implements EntityModel {
    @Id
    @Column(name = "ID")
    private Long id;

    @MapsId
    @OneToOne
    @JoinColumn(name = "ID")
    private Flat flat;

    /**
     * Дата публикации
     */
    @Column(name = "PUBLICATION_DATE", nullable = false)
    private Date publicationDate;

    /**
     * Дата снятия с публикации
     */
    @Column(name = "PUBLICATION_DATE_END")
    private Date publicationDateEnd;

    /**
     * Площадь кухни -  m2
     */
    @Column(name = "KITCHEN_AREA")
    private BigDecimal kitchenArea;

    /**
     * Площадь жилая -  m2
     */
    @Column(name = "LIVING_AREA")
    private BigDecimal livingArea;

    /**
     * Ремонт - задачи для машин лёнинга и компьютерного зрения (полнотекстовый парсинг косметический / евро / дизайнерский)
     */
    @Column(name = "REDECORATION")
    private String redecoration;

    /**
     * Балкон - есть / нет
     */
    @Column(name = "BALCONY")
    private Boolean balcony;

    /**
     * Площадь балкона -m2
     */
    @Column(name = "BALCONY_AREA")
    private BigDecimal balconyArea;

    /**
     * Лоджия - есть / нет
     */
    @Column(name = "LOGGIA")
    private Boolean loggia;

    /**
     * Лоджия площадь  -  m2
     */
    @Column(name = "LOGGIA_AREA")
    private BigDecimal loggiaArea;

    /**
     * Санузел - совмещенный / раздельный
     */
    @Column(name = "BATHROOM")
    private String bathroom;

    /**
     * Санузел - шт.
     */
    @Column(name = "BATHROOM_AMOUNT")
    private Integer bathroomAmount;

    /**
     * Вид из окон - парсинг текста
     */
    @Column(name = "VIEW_FROM_WINDOWS")
    private String viewFromWindows;

    /**
     * Тип продажи - свободная / альтернатива / обмен
     */
    @Column(name = "SALE_TYPE")
    private String saleType;

    /**
     * Обременения - есть / нет парсинг текста какие
     */
    @Column(name = "ENCUMBRANCE")
    private String encumbrance;

    /**
     * Цена сдачи в аренду - тыс. руб. в месяц\ (-)
     */
    @Column(name = "RENTING_PRICE")
    private BigDecimal rentingPrice;

    /**
     * Перепланировка
     */
    @Column(name = "REDEVELOPMENT")
    private Boolean redevelopment;

    /**
     * Для поля "Недооцененность / переоцененность". Верхная граница площади квартиры для сравнения аналогичных квартир
     */
    @Column(name = "AREA_UPPER_BOUND")
    private BigDecimal areaUpperBound;

    /**
     * Для поля "Недооцененность / переоцененность". Нижняя граница площади квартиры для сравнения аналогичных квартир
     */
    @Column(name = "AREA_LOWER_BOUND")
    private BigDecimal areaLowerBound;

    /** ---------------------------- Ads-api ---------------------------------------------------------------------------- */

    /**
     * Идентификатор записи из сторонней БД
     */
    @Column(name = "EXTERNAL_ID")
    private String externalId;

    /**
     * Url объявления на сайте-источнике
     */
    @Column(name = "URL")
    private String url;

    /**
     * Заголовок
     */
    @Column(name = "TITLE")
    private String title;

    /**
     * Телефон
     */
    @Column(name = "PHONE")
    private String phone;

    /**
     * Название мобильного оператора
     */
    @Column(name = "PHONE_OPERATOR")
    private String phoneOperator;

    /**
     * Персона для контактов, автор объявления
     */
    @Column(name = "PERSON")
    private String person;

    /**
     * Контактное лицо. В основном бывает указано, если поле person содержит имя какой-нибудь компании.
     */
    @Column(name = "CONTACT_NAME")
    private String contactName;

    /**
     * Тип персоны для контактов. "Частное лицо", "Агентство" или "Частное лицо (фильтр)"
     */
    @Column(name = "PERSON_TYPE")
    private String personType;

    /**
     * Описание объявления
     */
    @Column(name = "DESCRIPTION")
    private String description;

    /**
     * Сайт-источник
     */
    @Column(name = "SOURCE")
    private String source;

    /**
     * Количество объявлений с тем же номером. Имеет значение только для категории Недвижимость и подкатегорий, для других равно null.
     */
    @Column(name = "ADVERTISEMENTS_WITH_SAME_PHONE")
    private Integer advertisementsWithSamePhone;

    /**
     * Показывает, защищен (подменён) ли телефон, актуально для объявлений с avito, realty.yandex.ru и cian, для других источников равно null. Возможные значения: 1 - телефон защищен, 0 - не защищен, null - параметр недоступен.
     */
    @Column(name = "PHONE_PROTECTED")
    private Boolean phoneProtected;

    public interface Columns {
        int KITCHEN_AREA_SCALING = 1;
        int LIVING_AREA_SCALING = 1;
        int BALCONY_AREA_SCALING = 1;
        int LOGGIA_AREA_SCALING = 1;
        int AREA_LOWER_BOUND_SCALING = 1;
        int AREA_UPPER_BOUND_SCALING = 1;
        int RENTING_PRICE_SCALING = MONEY_SCALING;
    }
}
