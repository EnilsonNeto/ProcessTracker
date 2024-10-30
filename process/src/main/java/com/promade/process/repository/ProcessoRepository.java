package com.promade.process.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.promade.process.model.Processo;

public interface ProcessoRepository extends JpaRepository<Processo, Long> {
    Optional<Processo> findByNumero(String numero);
}
