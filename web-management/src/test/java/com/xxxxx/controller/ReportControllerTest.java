package com.xxxxx.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ReportControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetEmpJobData_success() throws Exception{
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/report/empJobData")
                .header("Authorization", "vhtrg3t54t45yv54vyw54y54vy54");
        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.code", equalTo(1)))
                .andExpect(jsonPath("$.data.jobList", notNullValue()))
                .andExpect(jsonPath("$.data.dataList", notNullValue()));
    }

    @Test
    public void testGetEmpGenderData_success() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/report/empGenderData")
                .header("Authorization", "vhtrg3t54t45yv54vyw54y54vy54");
        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.code", equalTo(1)))
                .andExpect(jsonPath("$.data[0].name", notNullValue()))
                .andExpect(jsonPath("$.data[0].value", notNullValue()))
                .andExpect(jsonPath("$.data[1].name", notNullValue()))
                .andExpect(jsonPath("$.data[1].value", notNullValue()));
    }
}