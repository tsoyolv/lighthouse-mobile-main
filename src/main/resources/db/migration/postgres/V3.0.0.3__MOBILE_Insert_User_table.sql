INSERT INTO MOBILE_USER(PHONE_NUMBER, ENABLED, ACCOUNT_NON_LOCKED, FIRST_NAME, SECOND_NAME, REGISTRATION_DATE, LAST_LOGIN)
VALUES ('79779873676', TRUE, TRUE, 'Олег', 'Цой', '1993-12-08 19:10:25-07', '2016-06-22 19:10:25-07');
INSERT INTO MOBILE_AUTHORITY_TO_USER(AUTHORITY_ID, USERS_ID)
VALUES ((SELECT ID FROM MOBILE_AUTHORITY WHERE SYSTEM_NAME = 'ROLE_MOBILE'), (SELECT ID FROM MOBILE_USER WHERE PHONE_NUMBER = '79779873676'));
INSERT INTO MOBILE_AUTHORITY_TO_USER(AUTHORITY_ID, USERS_ID)
VALUES ((SELECT ID FROM MOBILE_AUTHORITY WHERE SYSTEM_NAME = 'ROLE_ADMIN'), (SELECT ID FROM MOBILE_USER WHERE PHONE_NUMBER = '79779873676'));
