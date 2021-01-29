package ru.lighthouse.mobile.main.testdata;

import ru.lighthouse.mobile.main.core.dao.EntityModel;

import java.util.List;

public interface TestDataGenerator<T extends EntityModel> {
    T generate(T entity);
    Iterable<T> generate(T ... entities);
    T generateRandom();
    Iterable<T> generateRandom(int count);
    void deleteGenerated();
    void deleteByIds(List<Long> createdIds);
}
