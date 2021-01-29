package ru.lighthouse.mobile.main.core.dao.history;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.lighthouse.mobile.main.core.dao.AbstractEntityService;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import static java.util.Objects.isNull;

public abstract class AbstractEntityHistoryService
        <T extends HistoricalEntityModel, TR extends JpaRepository<T, Long> & JpaSpecificationExecutor<T>, H extends HistoryEntityModel, HR extends HistoryRepository<H>>
        extends AbstractEntityService<T, TR> {
    private final boolean historyEnabled;
    private final Integer historyLength;
    protected final HR historyRepository;

    public AbstractEntityHistoryService(TR repository, HR historyRepository, String historyEnabled, String historyLength) {
        super(repository);
        this.historyRepository = historyRepository;
        this.historyEnabled = Boolean.parseBoolean(historyEnabled);
        this.historyLength = Integer.parseInt(historyLength);
    }

    @Override
    @Transactional
    public T create(T toCreate) {
        updateVersion(toCreate);
        return super.create(toCreate);
    }

    @Override
    @Transactional
    public Iterable<T> createAll(Iterable<T> entities) {
        entities.forEach(this::updateVersion);
        return super.createAll(entities);
    }

    @Override
    @Transactional
    public Iterable<T> updateAll(Iterable<T> entities) {
        entities.forEach(this::updateVersion);
        return super.updateAll(entities);
    }

    @Override
    protected void updateFields(T toUpdate, T fromUpdate) {
        createHistory(toUpdate);
        updateVersion(toUpdate);
        super.updateFields(toUpdate, fromUpdate);
    }

    protected abstract H convertToHistory(T origin);

    private void updateVersion(T origin) {
        if (isNull(origin.getVersion())) {
            origin.setVersion(1);
        } else {
            origin.setVersion(origin.getVersion() + 1);
        }
        origin.setLastModifyDate(new Date());
    }

    protected void createHistory(T origin) {
        if (historyEnabled) {
            if (origin.getVersion() > historyLength) {
                List<H> histories = historyRepository.findAllByOriginId(origin.getId());
                histories.sort(Comparator.comparing(H::getId));
                historyRepository.delete(histories.get(0));
            }
            historyRepository.save(convertToHistory(origin));
        }
    }
}
