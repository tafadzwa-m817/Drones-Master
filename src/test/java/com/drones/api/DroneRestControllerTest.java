package com.drones.api;

import static org.mockito.Mockito.when;

import com.drones.service.DroneService;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {DroneRestController.class})
@ExtendWith(SpringExtension.class)
class DroneRestControllerTest {
    @Autowired
    private DroneRestController droneRestController;

    @MockBean
    private DroneService droneService;

    /**
     * Method under test:
     * {@link DroneRestController#checkAvailableDronesForLoading()}
     */
    @Test
    void checkAvailableDronesForLoadingCheckAvailableDronesForLoading() throws Exception {
        // Arrange
        when(droneService.checkAvailableDronesForLoading()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/v1/drone/all/available");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(droneRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test:
     * {@link DroneRestController#checkAvailableDronesForLoading()}
     */
    @Test
    void checkAvailableDronesForLoadingCheckAvailableDronesForLoading2() throws Exception {
        // Arrange
        when(droneService.checkAvailableDronesForLoading()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/v1/drone/all/available");
        requestBuilder.characterEncoding("Encoding");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(droneRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}
