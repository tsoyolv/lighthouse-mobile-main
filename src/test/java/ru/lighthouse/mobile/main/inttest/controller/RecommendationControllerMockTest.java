package ru.lighthouse.mobile.main.inttest.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.lighthouse.mobile.main.boot.security.jwt.JWTService;
import ru.lighthouse.mobile.main.core.rest.dto.PageResponseDto;
import ru.lighthouse.mobile.main.repository.user.UserRepository;
import ru.lighthouse.mobile.main.rest.service.recommendation.WebRecommendationService;
import ru.lighthouse.mobile.main.rest.service.recommendation.dto.RecommendationDto;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.equalTo;

public class RecommendationControllerMockTest extends AbstractRestControllerTest{

    private static final String RECOMMENDATION_URL = "/recommendation";

    @Autowired
    protected RecommendationControllerMockTest(JWTService jwtService, UserRepository userRepository) {
        super(jwtService, userRepository);
    }

    @MockBean
    private WebRecommendationService webRecommendationService;

    @Test
    public void get() throws Exception {
        Mockito.when(webRecommendationService.get(null)).thenReturn(mockPageResponse());

        final MvcResult mvcResult = mvc.perform(
                MockMvcRequestBuilders.get(RECOMMENDATION_URL)
                                      .header(jwtService.getHeader(), token)
                                      .contentType(MediaType.APPLICATION_JSON)
                                      .accept(MediaType.APPLICATION_JSON)
        ).andReturn();
        final MockHttpServletResponse mvcResponse = mvcResult.getResponse();
        assertThat(mvcResponse.getStatus(), equalTo(HttpStatus.OK.value()));

        String contentStr = mvcResponse.getContentAsString();
        assertThat(contentStr, is(not(emptyOrNullString())));

        PageResponseDto<RecommendationDto> pageResp = asObject(contentStr, new TypeReference<>() {});
        List<RecommendationDto> elements = pageResp.getElements();
        assertThat(elements, is(not(empty())));
        assertThat(elements.size(), is(equalTo(1)));
        assertThat(elements.get(0).getAddress(), equalTo("AAA"));

        Mockito.verify(webRecommendationService).get(null);
    }

    private PageResponseDto<RecommendationDto> mockPageResponse() {
        PageResponseDto<RecommendationDto> responseDto = new PageResponseDto<>();
        responseDto.setCurrentPage(0);
        responseDto.setPageSize(100);
        responseDto.setTotalPages(1);
        responseDto.setTotalElements(1L);
        responseDto.setElements(mockElements());
        return responseDto;
    }

    private List<RecommendationDto> mockElements() {
        List<RecommendationDto> elements = new ArrayList<>();
        RecommendationDto recommendationDto = new RecommendationDto();
        recommendationDto.setAddress("AAA");
        recommendationDto.setId(-1L);
        elements.add(recommendationDto);
        return elements;
    }
}
