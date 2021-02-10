package ru.lighthouse.mobile.main.inttest.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import ru.lighthouse.mobile.main.core.dao.EntityModel;
import ru.lighthouse.mobile.main.core.dao.EntityService;
import ru.lighthouse.mobile.main.core.rest.dto.PageRequestDto;
import ru.lighthouse.mobile.main.core.rest.dto.SearchCriteriaDto;
import ru.lighthouse.mobile.main.core.rest.dto.SortedFieldDto;
import ru.lighthouse.mobile.main.config.security.jwt.JWTService;
import ru.lighthouse.mobile.main.service.user.UserService;
import ru.lighthouse.mobile.main.service.user.entity.User;
import ru.lighthouse.mobile.main.inttest.AbstractIntegrationTest;
import ru.lighthouse.mobile.main.testdata.TestDataGenerator;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractRestControllerTest<T extends EntityModel, S extends EntityService<T>> extends AbstractIntegrationTest {
    protected AbstractRestControllerTest(S service, JWTService jwtService, UserService userService, TestDataGenerator<T> generator) {
        this.service = service;
        this.jwtService = jwtService;
        this.userService = userService;
        this.generator = generator;
        this.token = createJwtToken();
    }

    protected final S service;
    protected final JWTService jwtService;
    protected final UserService userService;
    protected final String token;
    protected final TestDataGenerator<T> generator;

    private final List<Long> createdEntitiesIds = new ArrayList<>();

    @BeforeEach
    protected void beforeEachTests() {}

    @AfterEach
    protected void afterEachTests() {
        generator.deleteGenerated();
        generator.deleteByIds(createdEntitiesIds);
    }

    protected void addCreatedEntities(Long id) {
        createdEntitiesIds.add(id);
    }

    protected PageRequestDto initPageRequest(int page, int size) {
        return initPageRequest(page, size, null);
    }

    protected PageRequestDto initPageRequest(int page, int size, List<SortedFieldDto> sortedFields) {
        PageRequestDto pageDto = new PageRequestDto();
        pageDto.setPage(page);
        pageDto.setSize(size);
        pageDto.setSortedFields(sortedFields);
        return pageDto;
    }

    protected PageRequestDto initPageRequestFilter(int page, int size, SearchCriteriaDto filter) {
        PageRequestDto pageDto = new PageRequestDto();
        pageDto.setPage(page);
        pageDto.setSize(size);
        pageDto.setFilter(filter);
        return pageDto;
    }

    protected PageRequestDto initPageRequest(int page, int size, List<SortedFieldDto> sortedFields, SearchCriteriaDto filter) {
        PageRequestDto pageDto = new PageRequestDto();
        pageDto.setPage(page);
        pageDto.setSize(size);
        pageDto.setSortedFields(sortedFields);
        pageDto.setFilter(filter);
        return pageDto;
    }

    protected String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected <T> T asObject(String from, Class<T> clazz) {
        try {
            return new ObjectMapper().readValue(from, clazz);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected <T> T asObject(String from, TypeReference<T> valueTypeRef) {
        try {
            return new ObjectMapper().readValue(from, valueTypeRef);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String createJwtToken() {
        LinkedHashMap<String, Object> details = new LinkedHashMap<>();
        User user = userService.getByPhoneNumber(DEFAULT_PHONE_NUMBER);
        details.put(jwtService.getClaimDetailsUserId(), user.getId());
        details.put(jwtService.getClaimDetailsUserBirthDate(), user.getBirthDate());
        details.put(jwtService.getClaimDetailsUserFirstName(), user.getFirstName());
        details.put(jwtService.getClaimDetailsUserSecondName(), user.getSecondName());
        details.put(jwtService.getClaimDetailsUserLastName(), user.getLastName());
        List<SimpleGrantedAuthority> authorities = user.getAuthorities().stream().map(a -> new SimpleGrantedAuthority(a.getSystemName())).collect(Collectors.toList());
        return jwtService.createJWTToken(DEFAULT_PHONE_NUMBER, authorities, details);
    }

    /*@BeforeAll
    private static void beforeAllTests() {}*/
}
