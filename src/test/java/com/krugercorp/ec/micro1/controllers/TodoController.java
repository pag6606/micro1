package com.krugercorp.ec.micro1.controllers;

import com.krugercorp.ec.micro1.Service.ToDoService;
import com.krugercorp.ec.micro1.bean.ToDoBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Date;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TodoController.class)
public class TodoController {


    @Autowired
    private MockMvc mvc;

    @MockBean
    private ToDoService service;

    @Test
    public void createTodo_withValidationError() throws Exception {
        ToDoBean mockTodo = new ToDoBean(4, "Jack",
                "Learn Spring MVC", new Date(), false);

        String todo;
        todo = "{\"user\":\"Jack\",\"desc\":\"Learn\",\"done\":false}";

        when( service.addTodo(
                anyString(), anyString(), isNull(), anyBoolean()))
                .thenReturn(mockTodo);

        MvcResult result = mvc.perform(
                MockMvcRequestBuilders.post("/users/Jack/todos")
                        .content(todo)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(
                        status().is4xxClientError()).andReturn();
    }
}
