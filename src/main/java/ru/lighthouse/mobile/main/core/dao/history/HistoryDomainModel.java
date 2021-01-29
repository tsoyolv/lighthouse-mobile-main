package ru.lighthouse.mobile.main.core.dao.history;

import ru.lighthouse.mobile.main.core.dao.DomainModel;

import java.util.Date;

public interface HistoryDomainModel extends DomainModel {
    Date getModifyDate();
    void setModifyDate(Date date);
    Long getOriginId();
    void setOriginId(Long originId);
}
