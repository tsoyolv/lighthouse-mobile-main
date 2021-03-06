package ru.lighthouse.mobile.main.testdata.generator;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import ru.lighthouse.mobile.main.core.dao.EntityModel;
import ru.lighthouse.mobile.main.core.dao.EntityService;
import ru.lighthouse.mobile.main.testdata.TestDataGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Scope(value = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS)
public abstract class AbstractGenerator<T extends EntityModel, S extends EntityService<T>> implements TestDataGenerator<T> {
    private final List<T> generatedEntities = new ArrayList<>();

    private final S service;

    public AbstractGenerator(S service) {
        this.service = service;
    }

    @Override
    public T generate(T entity) {
        final T generated = service.create(entity);
        generatedEntities.add(generated);
        return generated;
    }

    @Override
    public Iterable<T> generate(T... entities) {
        final Iterable<T> generated = service.createAll(Arrays.asList(entities));
        generated.forEach(generatedEntities::add);
        return generated;
    }

    @Override
    public T generateRandom() {
        T randomEntity = initRandomEntity();
        final T generated = service.create(randomEntity);
        generatedEntities.add(generated);
        return generated;
    }

    @Override
    public Iterable<T> generateRandom(int count) {
        List<T> randomEntities = new ArrayList<>(count);
        while (count-- > 0) {
            randomEntities.add(initRandomEntity());
        }
        final Iterable<T> generated = service.createAll(randomEntities);
        generated.forEach(generatedEntities::add);
        return generated;
    }

    @Override
    public void deleteGenerated() {
        generatedEntities.forEach(service::delete);
    }

    @Override
    public void deleteByIds(List<Long> createdIds) {
        createdIds.forEach(service::delete);
    }

    protected abstract T initRandomEntity();
}
