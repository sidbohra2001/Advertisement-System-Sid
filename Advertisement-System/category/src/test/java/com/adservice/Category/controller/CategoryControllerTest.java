package com.adservice.Category.controller;

import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.adservice.Category.dto.CategoryDto;
import com.adservice.Category.model.Category;
import com.adservice.Category.service.CategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {CategoryController.class})
@ExtendWith(SpringExtension.class)
class CategoryControllerTest {
    @Autowired
    private CategoryController categoryController;

    @MockBean
    private CategoryService categoryService;

    /**
     * Method under test: {@link CategoryController#add(CategoryDto)}
     */
    @Test
    void testAdd() throws Exception {
        Category category = new Category();
        category.setId(1);
        category.setName("Name");
        when(categoryService.add(Mockito.<Category>any())).thenReturn(category);

        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName("Name");
        String content = (new ObjectMapper()).writeValueAsString(categoryDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(categoryController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"Id\":1,\"Name\":\"Name\"}"));
    }

    /**
     * Method under test: {@link CategoryController#update(Category)}
     */
    @Test
    void testUpdate() throws Exception {
        Category category = new Category();
        category.setId(1);
        category.setName("Name");
        when(categoryService.update(Mockito.<Category>any())).thenReturn(category);

        Category category2 = new Category();
        category2.setId(1);
        category2.setName("Name");
        String content = (new ObjectMapper()).writeValueAsString(category2);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(categoryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"Id\":1,\"Name\":\"Name\"}"));
    }

    /**
     * Method under test: {@link CategoryController#get(int)}
     */
    @Test
    void testGet() throws Exception {
        Category category = new Category();
        category.setId(1);
        category.setName("Name");
        when(categoryService.get(anyInt())).thenReturn(category);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/{id}", 1);
        MockMvcBuilders.standaloneSetup(categoryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"Id\":1,\"Name\":\"Name\"}"));
    }

    /**
     * Method under test: {@link CategoryController#delete(int)}
     */
    @Test
    void testDelete() throws Exception {
        doNothing().when(categoryService).delete(anyInt());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/{id}", 1);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(categoryController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}

