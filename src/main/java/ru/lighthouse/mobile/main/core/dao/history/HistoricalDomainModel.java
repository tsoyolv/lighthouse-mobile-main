package ru.lighthouse.mobile.main.core.dao.history;


import ru.lighthouse.mobile.main.core.dao.DomainModel;

import java.util.Date;

public interface HistoricalDomainModel extends DomainModel {
    void setVersion(Integer version);
    Integer getVersion();
    void setLastModifyDate(Date date);
    Date getLastModifyDate();
}
