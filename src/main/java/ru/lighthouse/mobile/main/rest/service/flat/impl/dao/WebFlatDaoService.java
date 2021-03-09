package ru.lighthouse.mobile.main.rest.service.flat.impl.dao;

import lombok.RequiredArgsConstructor;
import ru.lighthouse.mobile.main.rest.service.flat.WebFlatService;
import ru.lighthouse.mobile.main.rest.service.flat.dto.FlatDetailsDto;
import ru.lighthouse.mobile.main.rest.service.flat.impl.dao.mapper.FlatDetailsDtoMapper;
import ru.lighthouse.mobile.main.repository.flat.entity.Flat;
import ru.lighthouse.mobile.main.repository.flat.FlatRepository;

import javax.transaction.Transactional;
import java.util.NoSuchElementException;
import java.util.Optional;

@RequiredArgsConstructor
public class WebFlatDaoService implements WebFlatService {
    private final FlatRepository repository;
    private final FlatDetailsDtoMapper detailsMapper;

    @Transactional
    @Override
    public FlatDetailsDto getById(Long id) {
        Optional<Flat> flatOpt = repository.findById(id);
        if (flatOpt.isPresent()) {
            Flat flat = flatOpt.get();
            flat.getPriceHistory().iterator().hasNext();
            flat.getImages().iterator().hasNext();
            flat.getFlatDetails().getDescription();
            return detailsMapper.map(flat);
        } else {
            throw new NoSuchElementException("No value present");
        }
    }
}
