package az.ingress.ingressautoservice.controller;

import az.ingress.ingressautoservice.constant.error.AdError;
import az.ingress.ingressautoservice.exception.NotFoundException;
import az.ingress.ingressautoservice.service.AdService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AdsController.class)
class AdsControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AdService adService;

    @Test
    void whenAdNotFoundByIdShouldReturnError() throws Exception {
        Long id = 1L;
        when(adService.findById(id))
                .thenThrow(NotFoundException.of(AdError.AD_NOT_FOUND.getCode(), AdError.AD_NOT_FOUND.buildMessage(id)));
        mockMvc.perform(MockMvcRequestBuilders.get("/ads/{id}", id))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.data").doesNotExist())
                .andExpect(jsonPath("$.errors[0].code").value(AdError.AD_NOT_FOUND.getCode()))
                .andExpect(jsonPath("$.errors[0].message").value(String.format("Ad not found by id: %d", id)));
    }
}