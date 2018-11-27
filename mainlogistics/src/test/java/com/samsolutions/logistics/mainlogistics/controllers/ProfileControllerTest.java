package com.samsolutions.logistics.mainlogistics.controllers;


import com.samsolutions.logistics.mainlogistics.services.ContactsSignUpService;
import com.samsolutions.logistics.mainlogistics.services.FirmsService;
import com.samsolutions.logistics.mainlogistics.services.FirmsSignUpService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProfileControllerTest {

    private MockMvc mockMvc;

    @MockBean
    private FirmsSignUpService firmsSignUpServiceMock;
    @MockBean
    private ContactsSignUpService contactsSignUpServiceMock;
    @MockBean
    private FirmsService firmsServiceMock;

    @Before
    public void setup() {
        this.mockMvc = standaloneSetup(new ProfileController()).build();
    }

    @Test
    public void ShouldReturnHtmlAndStatusOk(){
        String profileFirm="firm";
        String profileFirmEmail="itra@itra.itra";
        String profileContact="contact";
        String profileContactEmail="egor@mail.ru";
        try {
            mockMvc.perform(get("/profile/"+profileFirm+"/"+profileFirmEmail)).andExpect(status().isOk());
            mockMvc.perform(get("/profile/"+profileContact+"/"+profileContactEmail)).andExpect(status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
