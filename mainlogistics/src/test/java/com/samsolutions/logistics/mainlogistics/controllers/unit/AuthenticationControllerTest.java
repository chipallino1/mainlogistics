package com.samsolutions.logistics.mainlogistics.controllers.unit;

import com.samsolutions.logistics.mainlogistics.services.signup.ContactsSignUpService;
import com.samsolutions.logistics.mainlogistics.services.user.FirmsService;
import com.samsolutions.logistics.mainlogistics.services.signup.FirmsSignUpService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AuthenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private FirmsSignUpService firmsSignUpServiceMock;
    @MockBean
    private ContactsSignUpService contactsSignUpServiceMock;

    @Test
    public void shouldSetServices(){
        assertThat(firmsSignUpServiceMock).isNotNull();
        assertThat(contactsSignUpServiceMock).isNotNull();
    }

    @Test
    public void shouldReturnHtmlAndStatusOk(){
        try {
            mockMvc.perform(get("/auth")).andExpect(status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shouldReturnHtmlAndResponseStatusIsRedirect(){
        try {
            mockMvc.perform(get("/auth/contact")).andExpect(status().is3xxRedirection());
            mockMvc.perform(get("/auth/firm")).andExpect(status().is3xxRedirection());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void shouldReturnOkOrRedirect(){
        try {
            mockMvc.perform(MockMvcRequestBuilders.post("/auth/contact"))
                    .andExpect(status().isOk());
            mockMvc.perform(MockMvcRequestBuilders.post("/auth/firm"))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
