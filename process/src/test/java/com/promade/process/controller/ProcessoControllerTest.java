package com.promade.process.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.promade.process.dto.ProcessoDTO;
import com.promade.process.model.Processo;
import com.promade.process.service.ProcessoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ProcessoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProcessoService processoService;

    // Teste para o método POST (criação de um processo)
    @Test
    public void testSalvarProcesso() throws Exception {
        ProcessoDTO processoDTO = new ProcessoDTO();
        processoDTO.setNumero("123456");

        Processo processoSalvo = new Processo();
        processoSalvo.setId(1L);
        processoSalvo.setNumero("123456");

        when(processoService.salvar(any(Processo.class))).thenReturn(processoSalvo);

        mockMvc.perform(post("/api/processos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(processoDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.numero").value("123456"));

        verify(processoService, times(1)).salvar(any(Processo.class));
    }

    // Teste para o método GET (listar todos os processos)
    @Test
    public void testListarProcessos() throws Exception {
        Processo processo1 = new Processo();
        processo1.setId(1L);
        processo1.setNumero("123456");

        Processo processo2 = new Processo();
        processo2.setId(2L);
        processo2.setNumero("789101");

        when(processoService.listar()).thenReturn(List.of(processo1, processo2));

        mockMvc.perform(get("/api/processos")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].numero").value("123456"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].numero").value("789101"));

        verify(processoService, times(1)).listar();
    }

    // Teste para o método DELETE (exclusão de um processo)
    @Test
    public void testExcluirProcesso() throws Exception {
        Long processoId = 1L;
        doNothing().when(processoService).excluir(processoId);

        mockMvc.perform(delete("/api/processos/{id}", processoId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(processoService, times(1)).excluir(processoId);
    }

    // Teste para o método PUT (adicionar réu a um processo)
    @Test
    public void testAdicionarReu() throws Exception {
        Long processoId = 1L;
        Long reuId = 2L;
        doNothing().when(processoService).adicionarReu(processoId, reuId);

        mockMvc.perform(put("/api/processos/{processoId}/reus/{reuId}", processoId, reuId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(processoService, times(1)).adicionarReu(processoId, reuId);
    }
}
