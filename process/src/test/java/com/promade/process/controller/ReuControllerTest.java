package com.promade.process.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.promade.process.dto.ReuDTO;
import com.promade.process.model.Reu;
import com.promade.process.service.ReuService;

@WebMvcTest(ReuController.class)
class ReuControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReuService reuService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testSalvarReu() throws Exception {
        ReuDTO reuDTO = new ReuDTO();
        reuDTO.setNome("João TESTE Silva");
        reuDTO.setCpf("23213123123");
        reuDTO.setTelefone("81991443303");

        Reu savedReu = new Reu();
        savedReu.setId(1L);
        savedReu.setNome("João TESTE Silva");
        savedReu.setCpf("23213123123");
        savedReu.setTelefone("81991443303");

        when(reuService.salvar(any(Reu.class))).thenReturn(savedReu);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/reus")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(reuDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("João TESTE Silva"))
                .andExpect(jsonPath("$.cpf").value("23213123123"))
                .andExpect(jsonPath("$.telefone").value("81991443303"));
    }

    @Test
    void testListarReus() throws Exception {
        List<Reu> reus = List.of(new Reu(), new Reu());
        when(reuService.listar()).thenReturn(reus);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/reus"))
                .andExpect(status().isOk());
    }

    @Test
    void testAtualizarReu() throws Exception {
        Long id = 1L;
        ReuDTO reuDTO = new ReuDTO();
        reuDTO.setNome("João Silva Atualizado");
        reuDTO.setCpf("23213123123");
        reuDTO.setTelefone("81991443303");

        Reu updatedReu = new Reu();
        updatedReu.setId(id);
        updatedReu.setNome("João Silva Atualizado");
        updatedReu.setCpf("23213123123");
        updatedReu.setTelefone("81991443303");

        when(reuService.atualizar(eq(id), any(ReuDTO.class))).thenReturn(updatedReu);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/reus/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(reuDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.nome").value("João Silva Atualizado"))
                .andExpect(jsonPath("$.cpf").value("23213123123"))
                .andExpect(jsonPath("$.telefone").value("81991443303"));
    }

    @Test
    void testDeletarReu() throws Exception {
        Long id = 1L;
        doNothing().when(reuService).excluir(id);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/reus/{id}", id))
                .andExpect(status().isNoContent());

        verify(reuService, times(1)).excluir(id);
    }
}
