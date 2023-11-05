package com.yuzarsif.youcontribute.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yuzarsif.youcontribute.controllers.requests.CreateRepositoryRequest;
import com.yuzarsif.youcontribute.models.Repository;
import com.yuzarsif.youcontribute.service.RepositoryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = RepositoryController.class)
@AutoConfigureMockMvc(addFilters = false)
public class RepositoryControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private RepositoryService repositoryService;

    @Test
    public void it_should_list_repositories() throws Exception {
        Repository repository = Repository.builder().organization("yuzarsifkilic").repository("youcontribute").build();

        given(repositoryService.list()).willReturn(Collections.singletonList(repository));

        mockMvc.perform(get("/repositories"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("youcontribute"))
                .andExpect(jsonPath("$[0].organization").value("yuzarsifkilic"));
    }

    @Test
    public void it_should_create_repository() throws Exception {
        //given
        String organization = "github";
        String name = "octocat";
        CreateRepositoryRequest request = CreateRepositoryRequest
                .builder()
                .organization(organization)
                .repository(name)
                .build();

        doNothing().when(repositoryService).create(organization, name);
        //when
        mockMvc.perform(post("/repositories")
                .content(objectMapper.writeValueAsBytes(request))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());

        //then


    }

}