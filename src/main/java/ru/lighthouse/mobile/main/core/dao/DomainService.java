package ru.lighthouse.mobile.main.core.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface DomainService<T extends DomainModel> {
    T create(T toCreate);
    Iterable<T> createAll(Iterable<T> entities);
    Optional<T> get(Long id);
    List<T> getAll();
    List<T> getAllById(Iterable<Long> ids);
    Page<T> getPage(Pageable pageable);
    Page<T> getPage(Specification<T> specification, Pageable pageable);
    T update(T toUpdate);
    Iterable<T> updateAll(Iterable<T> entities);
    void delete(T toDelete);
    void delete(Long toDeleteId);
}
