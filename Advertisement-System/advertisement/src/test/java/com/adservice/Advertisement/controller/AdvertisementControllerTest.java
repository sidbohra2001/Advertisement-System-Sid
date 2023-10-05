package com.adservice.Advertisement.controller;

import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.adservice.Advertisement.dto.AdvertisementDto;
import com.adservice.Advertisement.enums.Type;
import com.adservice.Advertisement.model.Advertisement;
import com.adservice.Advertisement.service.AdvertisementService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

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

@ContextConfiguration(classes = {AdvertisementController.class})
@ExtendWith(SpringExtension.class)
class AdvertisementControllerTest {
    @Autowired
    private AdvertisementController advertisementController;

    @MockBean
    private AdvertisementService advertisementService;

    /**
     * Method under test: {@link AdvertisementController#add(AdvertisementDto)}
     */
//      descriptive method names
    @Test
    void testAdd() throws Exception {
        Advertisement advertisement = new Advertisement();
        advertisement.setCategory(1);
        advertisement.setId(1);
        advertisement.setPrice(10.0d);
        advertisement.setProduct("Product");
        advertisement.setType(Type.BUY);
        advertisement.setUser(1);
        when(advertisementService.add(Mockito.<Advertisement>any())).thenReturn(advertisement);

        AdvertisementDto advertisementDto = new AdvertisementDto();
        advertisementDto.setCategory(1);
        advertisementDto.setPrice(10.0d);
        advertisementDto.setProduct("Product");
        advertisementDto.setType(Type.BUY);
        advertisementDto.setUser(1);
        String content = (new ObjectMapper()).writeValueAsString(advertisementDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(advertisementController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"Id\":1,\"Category\":1,\"Product\":\"Product\",\"User\":1,\"Price\":10.0,\"Type\":\"BUY\"}"));
    }

    /**
     * Method under test: {@link AdvertisementController#update(Advertisement)}
     */
    @Test
    void testUpdate() throws Exception {
        //We can use a helper method here or in another class to generate these objects, redundant code.
        Advertisement advertisement = new Advertisement();
        advertisement.setCategory(1);
        advertisement.setId(1);
        advertisement.setPrice(10.0d);
        advertisement.setProduct("Product");
        advertisement.setType(Type.BUY);
        advertisement.setUser(1);
        when(advertisementService.update(Mockito.<Advertisement>any())).thenReturn(advertisement);

        Advertisement advertisement2 = new Advertisement();
        advertisement2.setCategory(1);
        advertisement2.setId(1);
        advertisement2.setPrice(10.0d);
        advertisement2.setProduct("Product");
        advertisement2.setType(Type.BUY);
        advertisement2.setUser(1);
        String content = (new ObjectMapper()).writeValueAsString(advertisement2);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(advertisementController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"Id\":1,\"Category\":1,\"Product\":\"Product\",\"User\":1,\"Price\":10.0,\"Type\":\"BUY\"}"));
    }

    /**
     * Method under test: {@link AdvertisementController#get(int)}
     */
    @Test
    void testGet() throws Exception {
        Advertisement advertisement = new Advertisement();
        advertisement.setCategory(1);
        advertisement.setId(1);
        advertisement.setPrice(10.0d);
        advertisement.setProduct("Product");
        advertisement.setType(Type.BUY);
        advertisement.setUser(1);
        when(advertisementService.get(anyInt())).thenReturn(advertisement);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/{id}", 1);
        MockMvcBuilders.standaloneSetup(advertisementController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"Id\":1,\"Category\":1,\"Product\":\"Product\",\"User\":1,\"Price\":10.0,\"Type\":\"BUY\"}"));
    }

    /**
     * Method under test: {@link AdvertisementController#getByCategory(int)}
     */
    @Test
    void testGetByCategory() throws Exception {
        when(advertisementService.getAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/?category=42");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("category", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(advertisementController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link AdvertisementController#getAll()}
     */
    @Test
    void testGetAll() throws Exception {
        when(advertisementService.getAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/");
        MockMvcBuilders.standaloneSetup(advertisementController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link AdvertisementController#delete(int)}
     */
    @Test
    void testDelete() throws Exception {
        doNothing().when(advertisementService).delete(anyInt());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/{id}", 1);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(advertisementController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}

