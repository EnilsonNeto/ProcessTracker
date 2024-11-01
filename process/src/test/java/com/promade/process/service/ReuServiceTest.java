package com.promade.process.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.promade.process.dto.ReuDTO;
import com.promade.process.model.Reu;
import com.promade.process.repository.ReuRepository;

import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@ActiveProfiles("test")
class ReuServiceTest {

    @Autowired
    private ReuService reuService;

    @MockBean
    private ReuRepository reuRepository;

    @Test
    void testSalvarReu() {
        Reu reu = new Reu();
        reu.setNome("João Silva");

        when(reuRepository.save(any(Reu.class))).thenReturn(new Reu());

        Reu savedReu = reuService.salvar(reu);

        assertNotNull(savedReu);
        verify(reuRepository).save(any(Reu.class));
    }

    @Test
    void testListarReus() {
        List<Reu> reus = List.of(new Reu(), new Reu());

        when(reuRepository.findAll()).thenReturn(reus);

        List<Reu> result = reuService.listar();

        assertEquals(2, result.size());
        verify(reuRepository).findAll();
    }

    @Test
    void testBuscarReuPorId() {
        Long id = 1L;
        Reu reu = new Reu();
        reu.setId(id);

        when(reuRepository.findById(id)).thenReturn(Optional.of(reu));

        Reu foundReu = reuService.buscarPorId(id);

        assertNotNull(foundReu);
        assertEquals(id, foundReu.getId());
    }

    @Test
    void testBuscarReuPorIdNaoEncontrado() {
        Long id = 1L;

        when(reuRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> reuService.buscarPorId(id));
    }
    
    @Test
    void testAtualizarReu() {
        Long id = 1L;
        ReuDTO reuDTO = new ReuDTO();
        reuDTO.setNome("João Silva Atualizado");
        reuDTO.setCpf("23213123123");
        reuDTO.setTelefone("81991443303");

        Reu reu = new Reu();
        reu.setId(id);
        when(reuRepository.findById(id)).thenReturn(Optional.of(reu));
        when(reuRepository.save(any(Reu.class))).thenReturn(reu);

        Reu updatedReu = reuService.atualizar(id, reuDTO);

        assertNotNull(updatedReu);
        assertEquals("João Silva Atualizado", updatedReu.getNome());
        verify(reuRepository).save(any(Reu.class));
    }

    @Test
    void testAtualizarReuNaoEncontrado() {
        Long id = 1L;
        ReuDTO reuDTO = new ReuDTO();

        when(reuRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> reuService.atualizar(id, reuDTO));
    }

    @Test
    void testExcluirReu() {
        Long id = 1L;
        Reu reu = new Reu();
        reu.setId(id);

        when(reuRepository.findById(id)).thenReturn(Optional.of(reu));
        doNothing().when(reuRepository).delete(any(Reu.class));

        reuService.excluir(id);

        verify(reuRepository, times(1)).delete(any(Reu.class));
    }

    @Test
    void testExcluirReuNaoEncontrado() {
        Long id = 1L;

        when(reuRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> reuService.excluir(id));
    }
}
