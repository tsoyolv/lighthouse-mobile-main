package ru.lighthouse.mobile.main.core.dao.history;

import ru.lighthouse.mobile.main.core.dao.EntityModel;

import java.util.Date;

public interface HistoryEntityModel extends EntityModel {
    Date getModifyDate();

    void setModifyDate(Date date);

    Long getOriginId();

    void setOriginId(Long originId);
}
