package ru.lighthouse.mobile.main.service.flat.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.lighthouse.mobile.main.core.dao.AbstractEntityService;
import ru.lighthouse.mobile.main.boot.tracktime.TrackExecutionTime;
import ru.lighthouse.mobile.main.service.flat.FlatService;
import ru.lighthouse.mobile.main.service.flat.entity.Flat;
import ru.lighthouse.mobile.main.service.flat.repository.FlatRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class FlatServiceImpl extends AbstractEntityService<Flat, FlatRepository> implements FlatService {

    public FlatServiceImpl(FlatRepository repository) {
        super(repository);
    }

    @TrackExecutionTime
    @Override
    public Page<Flat> getPage(Specification<Flat> specification, Pageable pageable) {
        return super.getPage(specification, pageable);
    }

    @Override
    @Transactional
    @TrackExecutionTime
    public Optional<Flat> getDetails(Long id) {
        Optional<Flat> flatOpt = get(id);
        if (flatOpt.isPresent()) {
            Flat flat = flatOpt.get();
            flat.getPriceHistory().iterator().hasNext();
            flat.getImages().iterator().hasNext();
            flat.getFlatDetails().getDescription();
            return Optional.of(flat);
        } else {
            return flatOpt;
        }
    }
}
