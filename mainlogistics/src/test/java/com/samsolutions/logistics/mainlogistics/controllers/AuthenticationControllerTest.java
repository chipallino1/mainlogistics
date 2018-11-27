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
public class AuthenticationControllerTest {

    private MockMvc mockMvc;

    @MockBean
    private FirmsSignUpService firmsSignUpServiceMock;
    @MockBean
    private ContactsSignUpService contactsSignUpServiceMock;
    @MockBean
    private FirmsService firmsServiceMock;

    @Before
    public void setup() {
        this.mockMvc = standaloneSetup(new AuthenticationController()).build();
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
            System.out.println("Error in shouldReturnHtmlAndResponseStatusOk: "+e.getMessage()+"\ncause: "+e.getCause());
        }
    }
    @Test
    public void shouldReturnOkOrRedirect(){
        assertThat(firmsSignUpServiceMock).isNotNull();
        assertThat(contactsSignUpServiceMock).isNotNull();
        try {
            mockMvc.perform(MockMvcRequestBuilders.post("/auth/contact"))
                    .andExpect(status().isOk());
            mockMvc.perform(MockMvcRequestBuilders.post("/auth/firm"))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @Test
    public void shouldReturnNotNullValue(){
        String partOfFirmName="i";
        assertThat(firmsServiceMock).isNotNull();
        try {
            mockMvc.perform(get("/firms/"+partOfFirmName+"/readall")).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
