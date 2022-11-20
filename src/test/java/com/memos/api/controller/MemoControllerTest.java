package com.memos.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.memos.api.model.Memo;
import com.memos.api.service.MemoService;
import org.junit.jupiter.api.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@RunWith(SpringRunner.class)
@ComponentScan(basePackages = {"com.memos.api"})
@SpringBootTest
@AutoConfigureMockMvc
public class MemoControllerTest {
    private String userId = "";
    private String token = "";
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MemoService memoService;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void checkAuthWhenCreateMemo() throws Exception {

        // given - precondition or setup
        Memo newMemo = new Memo();

        given(memoService.createMemo(this.userId, newMemo)).willAnswer((invocation) -> invocation.getArgument(0));

        // when - action or behaviour that we are going test
        ResultActions response = mockMvc.perform(post("/memos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newMemo)));
        // then - verify the result or output using assert statements
        response.andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void checkAuthWhenUpdateMemo() throws Exception {


        Memo updatedMemo = new Memo();
        updatedMemo.setContent("Content");

        given(memoService.updateMemo("1", "1", updatedMemo))
                .willAnswer((invocation) -> invocation.getArgument(0));

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(put("/memos/{id}", "1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedMemo)));


        response.andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void checkAuthWhenDeleteMemo() throws Exception {
        willDoNothing().given(memoService).deleteMemoById("1", "1");

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(delete("/employees/{id}", "1"));

        response.andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void givenMemoObject_whenCreateMemo_thenReturnSavedMemo() throws Exception {

        // given - precondition or setup
        Memo newMemo = new Memo();
        newMemo.setContent("Content");

        given(memoService.createMemo(this.userId, newMemo)).willAnswer((invocation) -> invocation.getArgument(0));

        // when - action or behaviour that we are going test
        ResultActions response = mockMvc.perform(post("/memos").header(HttpHeaders.AUTHORIZATION, this.token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newMemo)));
        // then - verify the result or output using assert statements
        response.andDo(print())
                .andExpect(status().isOk());
    }
}
