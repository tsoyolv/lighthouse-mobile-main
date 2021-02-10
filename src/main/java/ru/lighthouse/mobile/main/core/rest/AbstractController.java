package ru.lighthouse.mobile.main.core.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.lighthouse.mobile.main.core.dao.EntityModel;
import ru.lighthouse.mobile.main.core.dao.EntityService;
import ru.lighthouse.mobile.main.core.rest.dto.DtoModel;
import ru.lighthouse.mobile.main.core.rest.dto.PageRequestDto;
import ru.lighthouse.mobile.main.core.rest.dto.PageResponseDto;
import ru.lighthouse.mobile.main.core.rest.mapper.PageDtoMapper;
import ru.lighthouse.mobile.main.core.rest.mapper.SearchCriteriaMapper;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.nonNull;
import static ru.lighthouse.mobile.main.core.rest.CommonUri.URI_PART_ID;
import static ru.lighthouse.mobile.main.core.rest.CommonUri.URI_PART_PAGE;
import static ru.lighthouse.mobile.main.config.security.SecurityRole.ROLE_ADMIN_STR;

@RequiredArgsConstructor
public abstract class AbstractController<D extends EntityModel, DS extends EntityService<D>, DTO extends DtoModel> {
    protected final DS domainService;
    
    @PostMapping
    public DTO create(@RequestBody @Valid DTO dto) {
        D mapped = mapDtoToEntityModel(dto);
        beforeCreate(dto, mapped);
        D created = domainService.create(mapped);
        afterCreate(dto, created);
        return mapEntityModelToDto(created);
    }

    @GetMapping(URI_PART_ID)
    public DTO getById(@PathVariable @Min(1) Long id) {
        Optional<D> opt = domainService.get(id);
        return opt.map(this::mapEntityModelToDto).orElse(null);
    }

    @PostMapping(URI_PART_PAGE)
    public PageResponseDto<DTO> getPage(@RequestBody(required = false) @Valid PageRequestDto pageRequest) {
        Pageable pageable = PageDtoMapper.map(pageRequest);
        Specification<D> filter = SearchCriteriaMapper.getFromPage(pageRequest);
        Page<D> page;
        if (nonNull(filter)) {
            page = domainService.getPage(filter, pageable);
        } else {
            page = domainService.getPage(pageable);
        }
        List<DTO> mapped = mapEntityList(page.getContent());
        return PageDtoMapper.map(page, mapped);
    }

    @PutMapping
    public DTO update(@RequestBody @Valid DTO dto) {
        D mapped = mapDtoToEntityModel(dto);
        beforeUpdate(dto, mapped);
        D updated = domainService.update(mapped);
        afterUpdate(dto, updated);
        return mapEntityModelToDto(updated);
    }

    @DeleteMapping(URI_PART_ID)
    @Secured({ROLE_ADMIN_STR})
    public void delete(@PathVariable @Min(1) Long id) {
        domainService.delete(id);
    }

    protected abstract D mapDtoToEntityModel(DTO dto);
    protected abstract DTO mapEntityModelToDto(D domainModel);
    protected abstract List<DTO> mapEntityList(List<D> domainModels);
    protected abstract List<D> mapDtoList(List<DTO> dtos);

    protected void beforeCreate(DTO dto, D mapped) {}
    protected void afterCreate(DTO dto, D created) {}

    protected void beforeUpdate(DTO dto, D mapped) {}
    protected void afterUpdate(DTO dto, D updated) {}
}
