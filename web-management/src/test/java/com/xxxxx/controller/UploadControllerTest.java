package com.xxxxx.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.InputStream;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UploadControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testUpload_success() throws Exception{
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("1.png");
        MockMultipartFile mockFile = new MockMultipartFile("file", "1.png", "image/png", inputStream);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .multipart("/upload")
                .file(mockFile)
                .header("Authorization", "vhtrg3t54t45yv54vyw54y54vy54")
                .contentType(MediaType.MULTIPART_FORM_DATA);

        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.code", equalTo(1)))
                .andExpect(jsonPath("$.data", notNullValue()));
    }

    @Test
    public void testUpload_emptyFile_failure() throws Exception{
        MockMultipartFile emptyFile = new MockMultipartFile("file", "empty.png", "image/png", new byte[0]);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .multipart("/upload")
                .file(emptyFile)
                .header("Authorization", "vhtrg3t54t45yv54vyw54y54vy54")
                .contentType(MediaType.MULTIPART_FORM_DATA);

        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.code", equalTo(0)))
                .andExpect(jsonPath("$.data", nullValue()));
    }

}