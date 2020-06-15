CREATE TABLE FLAT
(
    ID                 BIGSERIAL PRIMARY KEY NOT NULL,
    PRICE              NUMERIC(18, 2) NOT NULL,
    SQUARE_METRE_PRICE NUMERIC(18, 2),
    ADDRESS            VARCHAR(500)   NOT NULL,
    METRO              VARCHAR(100),
    AREA               NUMERIC(6, 1)  NOT NULL,
    ROOMS_AMOUNT       SMALLINT       NOT NULL,
    FLOOR              SMALLINT       NOT NULL,
    TOTAL_FLOORS       SMALLINT,
    LATITUDE           NUMERIC(9, 6),
    LONGITUDE          NUMERIC(9, 6),
    LAST_MODIFY_DATE   TIMESTAMP      NOT NULL,
    METRO_DISTANCE     SMALLINT,
    BUILDING_ID        BIGINT
);

CREATE INDEX FLAT_BUILDING_ID_INDEX ON FLAT (BUILDING_ID);

COMMENT ON TABLE FLAT IS 'Квартира';
COMMENT ON COLUMN FLAT.PRICE IS 'Цена';
COMMENT ON COLUMN FLAT.SQUARE_METRE_PRICE IS 'Цена квадратного метра - тыс.руб / м2';
COMMENT ON COLUMN FLAT.ADDRESS IS 'Адрес - Улица / номер дома / корпус / строение';
COMMENT ON COLUMN FLAT.METRO IS 'Метро или район';
COMMENT ON COLUMN FLAT.AREA IS 'Площадь - m2';
COMMENT ON COLUMN FLAT.ROOMS_AMOUNT IS 'Количество комнат - шт.';
COMMENT ON COLUMN FLAT.FLOOR IS 'Этаж';
COMMENT ON COLUMN FLAT.TOTAL_FLOORS IS 'Всего этажей';
COMMENT ON COLUMN FLAT.LATITUDE IS 'Широта';
COMMENT ON COLUMN FLAT.LONGITUDE IS 'Долгота';
COMMENT ON COLUMN FLAT.LAST_MODIFY_DATE IS 'Дата и время время обновления';
COMMENT ON COLUMN FLAT.METRO_DISTANCE IS 'Расстояние до ближайшего метро в километрах';
COMMENT ON COLUMN FLAT.BUILDING_ID IS 'Идентификатор дома';

-- ================================================ DETAILS ============================================================

CREATE TABLE FLAT_DETAILS
(
    ID                             BIGSERIAL PRIMARY KEY NOT NULL,
    KITCHEN_AREA                   NUMERIC(6, 1),
    LIVING_AREA                    NUMERIC(6, 1),
    CEILING_HEIGHT                 SMALLINT,
    REDECORATION                   VARCHAR(250),
    BALCONY                        BOOLEAN,
    BALCONY_AREA                   NUMERIC(6, 1),
    LOGGIA                         BOOLEAN,
    LOGGIA_AREA                    NUMERIC(6, 1),
    BATHROOM                       VARCHAR(50),
    BATHROOM_AMOUNT                SMALLINT,
    VIEW_FROM_WINDOWS              VARCHAR(250),
    SALE_TYPE                      VARCHAR(251),
    ENCUMBRANCE                    VARCHAR(252),
    PUBLICATION_DATE               TIMESTAMP,
    PUBLICATION_DATE_END           TIMESTAMP,
    RENTING_PRICE                  NUMERIC(10, 2),
    REDEVELOPMENT                  BOOLEAN,
    INVESTMENT_ATTRACTION          NUMERIC(10, 2),
    ESTIMATION_PERCENT             SMALLINT,
    EXTERNAL_ID                    VARCHAR(150),
    URL                            VARCHAR(253),
    TITLE                          VARCHAR(254),
    PHONE                          VARCHAR(50),
    PHONE_OPERATOR                 VARCHAR(300),
    PERSON                         VARCHAR(301),
    CONTACT_NAME                   VARCHAR(302),
    PERSON_TYPE                    VARCHAR(50),
    DESCRIPTION                    VARCHAR(5000),
    SOURCE                         VARCHAR(200),
    ADVERTISEMENTS_WITH_SAME_PHONE INT,
    PHONE_PROTECTED                BOOLEAN
);

COMMENT ON TABLE FLAT_DETAILS IS 'Детальное описание квартиры';
COMMENT ON COLUMN FLAT_DETAILS.KITCHEN_AREA IS 'Площадь кухни -  m2';
COMMENT ON COLUMN FLAT_DETAILS.LIVING_AREA IS 'Площадь жилая -  m2';
COMMENT ON COLUMN FLAT_DETAILS.CEILING_HEIGHT IS 'Высота потолков - см.';
COMMENT ON COLUMN FLAT_DETAILS.REDECORATION IS 'Ремонт - задачи для машин лёнинга';
COMMENT ON COLUMN FLAT_DETAILS.BALCONY IS 'Балкон - есть / нет';
COMMENT ON COLUMN FLAT_DETAILS.BALCONY_AREA IS 'Площадь балкона -m2';
COMMENT ON COLUMN FLAT_DETAILS.LOGGIA IS 'Лоджия - есть / нет';
COMMENT ON COLUMN FLAT_DETAILS.LOGGIA_AREA IS 'Лоджия площадь  -  m2';
COMMENT ON COLUMN FLAT_DETAILS.BATHROOM IS 'Санузел - совмещенный / раздельный';
COMMENT ON COLUMN FLAT_DETAILS.BATHROOM_AMOUNT IS 'Санузел - шт.';
COMMENT ON COLUMN FLAT_DETAILS.VIEW_FROM_WINDOWS IS 'Вид из окон - парсинг текста';
COMMENT ON COLUMN FLAT_DETAILS.SALE_TYPE IS 'Тип продажи - свободная / альтернатива / обмен';
COMMENT ON COLUMN FLAT_DETAILS.ENCUMBRANCE IS 'Обременения - есть / нет парсинг текста какие';
COMMENT ON COLUMN FLAT_DETAILS.PUBLICATION_DATE IS 'Дата публикации';
COMMENT ON COLUMN FLAT_DETAILS.PUBLICATION_DATE_END IS 'Дата снятия с публикации';
COMMENT ON COLUMN FLAT_DETAILS.RENTING_PRICE IS 'Цена сдачи в аренду - тыс. руб. в месяц\ (-)';
COMMENT ON COLUMN FLAT_DETAILS.REDEVELOPMENT IS 'Перепланировка';
COMMENT ON COLUMN FLAT_DETAILS.INVESTMENT_ATTRACTION IS 'Инвестиционная привлекательность - окупаемость от сдачи в аренду в годах';
COMMENT ON COLUMN FLAT_DETAILS.ESTIMATION_PERCENT IS 'Недооцененность / переоцененность в процентах - отклонение от медианной цены в района среди аналогичных квартир';
----------------------------------- Ads-api ---------------------
COMMENT ON COLUMN FLAT_DETAILS.EXTERNAL_ID IS 'Идентификатор записи из сторонней БД';
COMMENT ON COLUMN FLAT_DETAILS.URL IS 'Url объявления на сайте-источнике';
COMMENT ON COLUMN FLAT_DETAILS.TITLE IS 'Заголовок';
COMMENT ON COLUMN FLAT_DETAILS.PHONE IS 'Телефон';
COMMENT ON COLUMN FLAT_DETAILS.PHONE_OPERATOR IS 'Название мобильного оператора';
COMMENT ON COLUMN FLAT_DETAILS.PERSON IS 'Персона для контактов, автор объявления';
COMMENT ON COLUMN FLAT_DETAILS.CONTACT_NAME IS 'Контактное лицо. В основном бывает указано, если поле person содержит имя какой-нибудь компании.';
COMMENT ON COLUMN FLAT_DETAILS.PERSON_TYPE IS 'Тип персоны для контактов. "Частное лицо", "Агентство" или "Частное лицо (фильтр)"';
COMMENT ON COLUMN FLAT_DETAILS.DESCRIPTION IS 'Описание объявления';
COMMENT ON COLUMN FLAT_DETAILS.SOURCE IS 'Сайт-источник';
COMMENT ON COLUMN FLAT_DETAILS.ADVERTISEMENTS_WITH_SAME_PHONE IS 'Количество объявлений с тем же номером';
COMMENT ON COLUMN FLAT_DETAILS.PHONE_PROTECTED IS 'Показывает, защищен (подменён) ли телефон, для объявлений с avito, realty.yandex.ru и cian';

-- ================================================ PRICE HISTORY ======================================================

CREATE TABLE FLAT_PRICE_HISTORY
(
    ID          BIGSERIAL PRIMARY KEY NOT NULL,
    PRICE       NUMERIC(18, 2)     NOT NULL,
    MODIFY_DATE TIMESTAMP          NOT NULL,
    FLAT_ID     INT,
    FOREIGN KEY (FLAT_ID) REFERENCES FLAT (ID)
);

COMMENT ON TABLE FLAT_PRICE_HISTORY IS 'История изменения цены';
COMMENT ON COLUMN FLAT_PRICE_HISTORY.PRICE IS 'Цена квартиры';
COMMENT ON COLUMN FLAT_PRICE_HISTORY.MODIFY_DATE IS 'Дата изменения';
COMMENT ON COLUMN FLAT_PRICE_HISTORY.FLAT_ID IS 'Идентификатор квартиры';
