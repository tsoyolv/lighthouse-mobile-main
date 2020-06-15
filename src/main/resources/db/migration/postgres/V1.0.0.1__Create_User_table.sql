CREATE TABLE USERS
(
    ID                 BIGSERIAL PRIMARY KEY NOT NULL,
    PHONE_NUMBER       VARCHAR(50)           NOT NULL,
    ENABLED            BOOLEAN               NOT NULL,
    ACCOUNT_NON_LOCKED BOOLEAN               NOT NULL,
    FIRST_NAME         VARCHAR(500),
    SECOND_NAME        VARCHAR(500),
    LAST_NAME          VARCHAR(500),
    BIRTH_DATE         DATE,
    REGISTRATION_DATE  TIMESTAMP             NOT NULL,
    LAST_LOGIN         TIMESTAMP             NOT NULL
);

COMMENT ON TABLE USERS IS 'Пользователь';
COMMENT ON COLUMN USERS.PHONE_NUMBER IS 'Номер телефона';
COMMENT ON COLUMN USERS.ENABLED IS 'Активен';
COMMENT ON COLUMN USERS.ACCOUNT_NON_LOCKED IS 'Не заблокирован';
COMMENT ON COLUMN USERS.FIRST_NAME IS 'Имя';
COMMENT ON COLUMN USERS.SECOND_NAME IS 'Фамилия';
COMMENT ON COLUMN USERS.LAST_NAME IS 'Отчество';
COMMENT ON COLUMN USERS.BIRTH_DATE IS 'Дата рождения';
COMMENT ON COLUMN USERS.REGISTRATION_DATE IS 'Дата регистрации';

--====================================================================================================================--

CREATE TABLE AUTHORITY
(
    ID          BIGSERIAL PRIMARY KEY NOT NULL,
    USER_ID     BIGINT                NOT NULL,
    NAME        VARCHAR(200)          NOT NULL,
    SYSTEM_NAME VARCHAR(100)          NOT NULL
);

COMMENT ON TABLE AUTHORITY IS 'Права пользователя';
COMMENT ON COLUMN AUTHORITY.USER_ID IS 'Идентификатор пользователя';
COMMENT ON COLUMN AUTHORITY.NAME IS 'Наименование';
COMMENT ON COLUMN AUTHORITY.SYSTEM_NAME IS 'Системное наименование';