package br.com.arthuratividade2.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.arthuratividade2.demo.model.Projeto;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Long>{
    Optional<Projeto> findByNome(String nome);
}

