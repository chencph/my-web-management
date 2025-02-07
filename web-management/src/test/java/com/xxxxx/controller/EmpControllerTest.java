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
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EmpControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testPage_validData_success() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/emps")
                .header("Authorization", "vhtrg3t54t45yv54vyw54y54vy54")
                .param("page","1")
                .param("pageSize", "10")
                .param("name","黃")
                .param("gender","1")
                .param("begin","2020-01-01")
                .param("end","2020-12-31");

        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.code", equalTo(1)))
                .andExpect(jsonPath("$.data.total", equalTo(1)))
                .andExpect(jsonPath("$.data.rows[0].id", equalTo(9)))
                .andExpect(jsonPath("$.data.rows[0].username", notNullValue()))
                .andExpect(jsonPath("$.data.rows[0].password", notNullValue()))
                .andExpect(jsonPath("$.data.rows[0].name", notNullValue()))
                .andExpect(jsonPath("$.data.rows[0].gender", equalTo(1)))
                .andExpect(jsonPath("$.data.rows[0].phone", notNullValue()))
                .andExpect(jsonPath("$.data.rows[0].job", equalTo(1)))
                .andExpect(jsonPath("$.data.rows[0].salary", notNullValue()))
                .andExpect(jsonPath("$.data.rows[0].image", notNullValue()))
                .andExpect(jsonPath("$.data.rows[0].entryDate", notNullValue()))
                .andExpect(jsonPath("$.data.rows[0].deptId", equalTo(1)))
                .andExpect(jsonPath("$.data.rows[0].createTime", notNullValue()))
                .andExpect(jsonPath("$.data.rows[0].updateTime", notNullValue()))
                .andExpect(jsonPath("$.data.rows[0].deptName", notNullValue()))
                .andExpect(jsonPath("$.data.rows[0].exprList", nullValue()));
    }

    @Test
    @Transactional
    public void testSave_validData_success() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/emps")
                .header("Authorization", "vhtrg3t54t45yv54vyw54y54vy54")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"username\": \"Ella\",\n" +
                        "  \"name\": \"鍾寧欣\",\n" +
                        "  \"gender\": 2,\n" +
                        "  \"phone\": \"0910263847\",\n" +
                        "  \"job\": 3,\n" +
                        "  \"salary\": 52000,\n" +
                        "  \"image\": \"1.jpg\",\n" +
                        "  \"entryDate\": \"2024-10-13\",\n" +
                        "  \"deptId\": 3,\n" +
                        "  \"exprList\": [\n" +
                        "    {\n" +
                        "      \"begin\": \"2014-11-28\",\n" +
                        "      \"end\": \"2017-10-15\",\n" +
                        "      \"company\": \"高旗股份有限公司\",\n" +
                        "      \"job\": \"行銷人員\"\n" +
                        "    }, {\n" +
                        "      \"begin\": \"2018-02-22\",\n" +
                        "      \"end\": \"2024-12-19\",\n" +
                        "      \"company\": \"櫂豪股份有限公司\",\n" +
                        "      \"job\":\"行銷人員\"\n" +
                        "    }\n" +
                        "  ]\n" +
                        "}");
        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.code", equalTo(1)))
                .andExpect(jsonPath("$.data", nullValue()));
    }

    @Test
    @Transactional
    public void testDelete_validData_success() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/emps")
                .header("Authorization", "vhtrg3t54t45yv54vyw54y54vy54")
                .param("ids", "1", "2", "3");
        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.code", equalTo(1)))
                .andExpect(jsonPath("$.data", nullValue()));
    }

    @Test
    public void testGetInfo_validData_success() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/emps/{id}", 1)
                .header("Authorization", "vhtrg3t54t45yv54vyw54y54vy54");
        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.code", equalTo(1)))
                .andExpect(jsonPath("$.data.id", equalTo(1)))
                .andExpect(jsonPath("$.data.username", notNullValue()))
                .andExpect(jsonPath("$.data.password", notNullValue()))
                .andExpect(jsonPath("$.data.name", notNullValue()))
                .andExpect(jsonPath("$.data.gender", equalTo(1)))
                .andExpect(jsonPath("$.data.phone", notNullValue()))
                .andExpect(jsonPath("$.data.job", equalTo(2)))
                .andExpect(jsonPath("$.data.salary", notNullValue()))
                .andExpect(jsonPath("$.data.image", notNullValue()))
                .andExpect(jsonPath("$.data.entryDate", notNullValue()))
                .andExpect(jsonPath("$.data.deptId", equalTo(2)))
                .andExpect(jsonPath("$.data.createTime", notNullValue()))
                .andExpect(jsonPath("$.data.updateTime", notNullValue()));
    }

    @Test
    @Transactional
    public void testUpdate_validData_success() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/emps")
                .header("Authorization", "vhtrg3t54t45yv54vyw54y54vy54")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                        "  \"id\": 2,\n" +
                        "  \"username\": \"Jenny\",\n" +
                        "  \"name\": \"趙小莫\",\n" +
                        "  \"gender\": 2,\n" +
                        "  \"phone\": \"0910293845\",\n" +
                        "  \"job\": 3,\n" +
                        "  \"salary\": 55000,\n" +
                        "  \"image\": \"1.jpg\",\n" +
                        "  \"deptId\": 3,\n" +
                        "  \"exprList\": [\n" +
                        "    {\n" +
                        "      \"begin\": \"2014-11-28\",\n" +
                        "      \"end\": \"2017-10-15\",\n" +
                        "      \"company\": \"高旗股份有限公司\",\n" +
                        "      \"job\": \"行銷人員\"\n" +
                        "    }, {\n" +
                        "      \"begin\": \"2018-02-22\",\n" +
                        "      \"end\": \"2024-12-19\",\n" +
                        "      \"company\": \"櫂豪股份有限公司\",\n" +
                        "      \"job\":\"行銷人員\"\n" +
                        "    }\n" +
                        "  ]\n" +
                        "}");
        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.code", equalTo(1)))
                .andExpect(jsonPath("$.data", nullValue()));
    }

}