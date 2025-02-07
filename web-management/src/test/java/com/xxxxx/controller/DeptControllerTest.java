package com.xxxxx.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class DeptControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testList_success() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/depts")
                .header("Authorization", "vhtrg3t54t45yv54vyw54y54vy54");
        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.code", equalTo(1)))
                .andExpect(jsonPath("$.data", notNullValue()));
    }

    @Test
    @Transactional
    public void testDelete_validData_success() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/depts")
                .header("Authorization", "vhtrg3t54t45yv54vyw54y54vy54")
                .param("id", "1");
        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.code", equalTo(1)))
                .andExpect(jsonPath("$.data", nullValue()));
    }

    @Test
    @Transactional
    public void testAdd_validData_success() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/depts")
                .header("Authorization", "vhtrg3t54t45yv54vyw54y54vy54")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                        "  \"name\": \"工程部\"" +
                        "}");
        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.code", equalTo(1)))
                .andExpect(jsonPath("$.data", nullValue()));
    }

    @Test
    public void testGetInfo_validData_success() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/depts/{id}", 1)
                .header("Authorization", "vhtrg3t54t45yv54vyw54y54vy54");
        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.code", equalTo(1)))
                .andExpect(jsonPath("$.data.id", equalTo(1)))
                .andExpect(jsonPath("$.data.name", notNullValue()))
            .andExpect(jsonPath("$.data.createTime", notNullValue()))
                .andExpect(jsonPath("$.data.updateTime", notNullValue()));
    }

    @Test
    @Transactional
    public void testUpdate_validData_success() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/depts")
                .header("Authorization", "vhtrg3t54t45yv54vyw54y54vy54")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                        "  \"id\": 2,\n" +
                        "  \"name\": \"開發部\"" +
                        "}");
        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.code", equalTo(1)))
                .andExpect(jsonPath("$.data", nullValue()));
    }

}