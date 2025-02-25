package br.com.zupacademy.joao.mercadolivre.controllertest;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;
import java.net.URISyntaxException;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CategoriaControllerTest {

    @Autowired
    MockMvc mockMvc;

    URI uri;
    JSONObject json;

    @Before
    public void before() throws URISyntaxException {
        uri = new URI("/categoria");
        json = new JSONObject();
    }

    @Test
    public void deveRetornarOkay() throws Exception {
        json.put("nome", "Smartphone");

        mockMvc.perform(MockMvcRequestBuilders
                .post(uri)
                .content(json.toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(200));

        json.put("nome", "Android");
        json.put("super", "1");

        mockMvc.perform(MockMvcRequestBuilders
                .post(uri)
                .content(json.toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(200));
    }

    @Test
    public void deveRetornarErroCasoNomeSejaRepetido() throws Exception {
        json.put("nome", "Iphone");

        mockMvc.perform(MockMvcRequestBuilders
                .post(uri)
                .content(json.toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(200));

        mockMvc.perform(MockMvcRequestBuilders
                .post(uri)
                .content(json.toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(400));
    }

    @Test
    public void deveRetornarErroCasoCategoriaSuperNaoExista() throws Exception {
        json.put("nome", "Smartphone");
        json.put("super", "15");

        mockMvc.perform(MockMvcRequestBuilders
                .post(uri)
                .content(json.toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(400));
    }
}
