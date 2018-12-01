package com.samsolutions.logistics.mainlogistics.controllers.unit;

import com.samsolutions.logistics.mainlogistics.services.user.FirmsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class FirmControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private FirmsService firmsService;

    @Test
    public void shouldSetService(){
        assertThat(firmsService).isNotNull();
    }

    @Test
    public void ShouldReadAllFirmsLike() {
        String firmName="itra";
       // mockMvc.perform(get("/firm/"+firmName+"/readall"));
    }

    @Test
    public void ShouldAddContactToFirm() {
    }

    @Test
    public void ShouldDeleteContact() {
    }
}