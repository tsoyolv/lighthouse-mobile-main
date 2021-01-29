package ru.lighthouse.mobile.main.core.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public abstract class AbstractEntityService<T extends EntityModel, R extends JpaRepository<T, Long> & JpaSpecificationExecutor<T>> implements EntityService<T> {
    protected final R repository;

    @Override
    @Transactional
    public T create(T toCreate) {
        return repository.save(toCreate);
    }

    @Override
    @Transactional
    public Iterable<T> createAll(Iterable<T> entities) {
        return repository.saveAll(entities);
    }

    @Override
    public Optional<T> get(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<T> getAll() {
        return repository.findAll();
    }

    @Override
    public List<T> getAllById(Iterable<Long> ids) {
        return repository.findAllById(ids);
    }

    @Override
    public Page<T> getPage(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Page<T> getPage(Specification<T> specification, Pageable pageable) {
        return repository.findAll(specification, pageable);
    }

    @Override
    @Transactional
    public T update(T fromUpdate) {
        T toUpdate = repository.getOne(fromUpdate.getId());
        updateFields(toUpdate, fromUpdate);
        return repository.save(toUpdate);
    }

    @Override
    @Transactional
    public Iterable<T> updateAll(Iterable<T> entities) {
        return repository.saveAll(entities);
    }

    @Override
    public void delete(T toDelete) {
        repository.delete(toDelete);
    }

    @Override
    public void delete(Long toDeleteId) {
        repository.deleteById(toDeleteId);
    }

    protected void updateFields(T toUpdate, T fromUpdate) {}
}
