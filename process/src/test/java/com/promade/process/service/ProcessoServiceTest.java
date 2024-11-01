package com.promade.process.service;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import com.promade.process.model.Processo;
import com.promade.process.model.Reu;
import com.promade.process.repository.ProcessoRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProcessoServiceTest {

    @MockBean
    private ProcessoRepository processoRepository;

    @MockBean
    private ReuService reuService;

    @Autowired
    private ProcessoService processoService;

    @Test
    void testSalvarProcesso() {
        Processo processo = new Processo();
        processo.setNumero("123456789");

        when(processoRepository.findByNumero(anyString())).thenReturn(Optional.empty());
        when(processoRepository.save(any(Processo.class))).thenReturn(new Processo());

        Processo savedProcesso = processoService.salvar(processo);

        assertNotNull(savedProcesso);
        verify(processoRepository).save(any(Processo.class));
    }

    @Test
    void testSalvarProcessoComNumeroExistente() {
        Processo processo = new Processo();
        processo.setNumero("123456789");

        when(processoRepository.findByNumero(anyString())).thenReturn(Optional.of(new Processo()));

        assertThrows(IllegalArgumentException.class, () -> processoService.salvar(processo));
    }

    @Test
    void testListarProcessos() {
        List<Processo> processos = List.of(new Processo(), new Processo());

        when(processoRepository.findAll()).thenReturn(processos);

        List<Processo> result = processoService.listar();

        assertEquals(2, result.size());
        verify(processoRepository).findAll();
    }

    @Test
    void testExcluirProcesso() {
        long id = 1L;
        doNothing().when(processoRepository).deleteById(id);

        processoService.excluir(id);

        verify(processoRepository).deleteById(id);
    }

    @Test
    void testAdicionarReuAoProcesso() {
        Processo processo = new Processo();
        processo.setId(1L);

        Reu reu = new Reu();
        reu.setId(1L);

        when(processoRepository.findById(anyLong())).thenReturn(Optional.of(processo));
        when(reuService.buscarPorId(anyLong())).thenReturn(reu);

        processoService.adicionarReu(1L, 1L);

        verify(processoRepository).save(processo);
    }
}
