package com.promade.process.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.promade.process.model.Processo;
import com.promade.process.model.Reu;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProcessoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testSalvarProcesso() throws Exception {
        Processo processo = new Processo();
        processo.setNumero("234123");

        mockMvc.perform(post("/api/processos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(processo)))
            .andExpect(status().isCreated());
    }

    @Test
    public void testSalvarReu() throws Exception {
        Reu reu = new Reu();
        reu.setNome("neto");

        mockMvc.perform(post("/api/reus")
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(reu)))
            .andExpect(status().isCreated());
    }

    @Test
    public void testAdicionarReuAoProcesso() throws Exception {
        Reu reu = new Reu();
        reu.setNome("Mizael");

        String reuResponse = mockMvc.perform(post("/api/reus")
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(reu)))
            .andExpect(status().isCreated())
            .andReturn().getResponse().getContentAsString();

        // Extrair o ID do réu recém-criado
        Long reuId = new ObjectMapper().readValue(reuResponse, Reu.class).getId();

        Processo processo = new Processo();
        processo.setNumero("321352");

        String processoResponse = mockMvc.perform(post("/api/processos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(processo)))
            .andExpect(status().isCreated())
            .andReturn().getResponse().getContentAsString();

        Long processoId = new ObjectMapper().readValue(processoResponse, Processo.class).getId();

        mockMvc.perform(post("/api/processos/" + processoId + "/reu?reuId=" + reuId)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    @Test
    public void testSalvarProcessoComNumeroDuplicado() throws Exception {
        Processo processo1 = new Processo();
        processo1.setNumero("8797");

        mockMvc.perform(post("/api/processos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(processo1)))
            .andExpect(status().isCreated());

        Processo processo2 = new Processo();
        processo2.setNumero("08934");

        mockMvc.perform(post("/api/processos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(processo2)))
            .andExpect(status().isBadRequest());
    }
}
