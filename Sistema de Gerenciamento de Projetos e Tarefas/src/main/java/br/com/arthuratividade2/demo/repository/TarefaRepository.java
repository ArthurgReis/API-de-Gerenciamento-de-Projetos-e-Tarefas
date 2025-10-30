package br.com.arthuratividade2.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.arthuratividade2.demo.model.Tarefa;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long>{
    Optional<Tarefa> findByDescricao(String descricao);
    List<Tarefa> findByProjetoId(Long id);
}

