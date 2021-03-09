package ru.lighthouse.mobile.main.rest.service.flat;

import ru.lighthouse.mobile.main.rest.service.flat.dto.FlatDetailsDto;

public interface WebFlatService {

    FlatDetailsDto getById(Long id);
}
