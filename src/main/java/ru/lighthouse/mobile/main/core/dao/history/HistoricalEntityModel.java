package ru.lighthouse.mobile.main.core.dao.history;


import ru.lighthouse.mobile.main.core.dao.EntityModel;

import java.util.Date;

public interface HistoricalEntityModel extends EntityModel {
    void setVersion(Integer version);
    Integer getVersion();
    void setLastModifyDate(Date date);
    Date getLastModifyDate();
}
