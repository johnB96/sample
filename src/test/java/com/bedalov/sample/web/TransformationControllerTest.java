package com.bedalov.sample.web;

import com.bedalov.sample.transformation.Transformer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static com.bedalov.sample.web.TransformationController.TRANSFORM_TO_ENGLISH;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class TransformationControllerTest {

    private static final String MOCK_REQUEST_FORMAT = "{\"input\":%d}";
    private static final String MOCK_RESULTS_FORMAT = "{\"input\":%d,\"output\":\"%s\"}";
    private static final String FIXED_RESULT = "foo";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private Transformer transformer;

    @Before
    public void setUp() {
        doReturn(FIXED_RESULT).when(transformer).transformToEnglish(anyInt());
    }

    @Test
    public void transformToEnglish_givenValidPostWithIntMin_thenResultsMatchExpected() throws Exception {
        Integer input = Integer.MIN_VALUE;

        mockMvc.perform(getPost(getMockRequest(input)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(getMockResult(input)));
    }

    @Test
    public void transformToEnglish_givenInvalidPostWithNull_thenBadRequest() throws Exception {
        Integer input = null;

        mockMvc.perform(getPost(getMockRequest(input)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void transformToEnglish_givenValidPostWithLeadingZero_thenZerosAreRemoved() throws Exception {
        mockMvc.perform(getPost("{\"input\":004}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(getMockResult(4)));
    }

    @Test
    public void transformToEnglish_givenInvalidPostWithString_thenBadRequest() throws Exception {
        mockMvc.perform(getPost("{\"input\":\"foo\"}"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    private String getMockRequest(Integer input) {
        return String.format(MOCK_REQUEST_FORMAT, input);
    }

    private String getMockResult(Integer input) {
        return String.format(MOCK_RESULTS_FORMAT, input, FIXED_RESULT);
    }

    private MockHttpServletRequestBuilder getPost(String request) {
        return post(TRANSFORM_TO_ENGLISH).content(request).contentType(MediaType.APPLICATION_JSON);
    }
}
