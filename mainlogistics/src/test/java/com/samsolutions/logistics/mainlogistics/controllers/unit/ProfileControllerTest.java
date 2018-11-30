package com.samsolutions.logistics.mainlogistics.controllers.unit;


import com.samsolutions.logistics.mainlogistics.controllers.ProfileController;
import com.samsolutions.logistics.mainlogistics.services.signup.ContactsSignUpService;
import com.samsolutions.logistics.mainlogistics.services.user.ContactsService;
import com.samsolutions.logistics.mainlogistics.services.user.FirmsService;
import com.samsolutions.logistics.mainlogistics.services.signup.FirmsSignUpService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProfileControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FirmsService firmsServiceMock;
    @MockBean
    private ContactsService contactsServiceMock;

    @Test
    public void shouldSetServices(){
        assertThat(firmsServiceMock).isNotNull();
        assertThat(contactsServiceMock).isNotNull();
    }

    @Test
    public void ShouldReturnProfileHtmlAndStatusRedirection(){
        String profileFirm="firm";
        String profileFirmEmail="itra@itra.itra";
        String profileContact="contact";
        String profileContactEmail="egor@mail.ru";
        try {
            mockMvc.perform(get("/profile/"+profileFirm+"/"+profileFirmEmail)).andExpect(status().is3xxRedirection());
            mockMvc.perform(get("/profile/"+profileContact+"/"+profileContactEmail)).andExpect(status().is3xxRedirection());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void ShouldUpdateAndReturnProfileHtmlOrAuthHtmlAndStatusRedirection(){
        String profileFirm="firm";
        String profileContact="contact";
        try {
            mockMvc.perform(post("/profile/"+profileFirm+"/update")).andExpect(status().is3xxRedirection());
            mockMvc.perform(post("/profile/"+profileContact+"/update")).andExpect(status().is3xxRedirection());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
