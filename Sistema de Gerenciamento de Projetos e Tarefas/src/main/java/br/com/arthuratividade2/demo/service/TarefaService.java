package br.com.arthuratividade2.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.arthuratividade2.demo.model.Projeto;
import br.com.arthuratividade2.demo.model.StatusTarefa;
import br.com.arthuratividade2.demo.model.Tarefa;
import br.com.arthuratividade2.demo.repository.ProjetoRepository;
import br.com.arthuratividade2.demo.repository.TarefaRepository;

@Service
public class TarefaService {

    private final TarefaRepository tarefaRepository;
    private final ProjetoRepository projetoRepository;

    
    public TarefaService(TarefaRepository tarefaRepository, ProjetoRepository projetoRepository) {
        this.tarefaRepository = tarefaRepository;
        this.projetoRepository = projetoRepository;
    }

    public Tarefa criarTarefa(Long id, Tarefa tarefa){
        Projeto projeto = projetoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Projeto não encontrado!"));

        tarefa.setProjeto(projeto);
        return tarefaRepository.save(tarefa);
    }


    public List<Tarefa> listarTarefasPorProjeto(Projeto projeto){
        return tarefaRepository.findByProjetoId(projeto.getId());
    }

    public Tarefa alterarTarefa(Long idProjeto, Long idTarefa, Tarefa novaTarefa) {
        Projeto projeto = projetoRepository.findById(idProjeto)
            .orElseThrow(() -> new RuntimeException("Projeto não encontrado!"));
    
        Tarefa tarefaAntiga = projeto.getTarefas().stream()
            .filter(tarefa -> tarefa.getId().equals(idTarefa))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Tarefa não encontrada!"));
    
        tarefaAntiga.setDescricao(novaTarefa.getDescricao());
        tarefaAntiga.setDataLimite(novaTarefa.getDataLimite());
        tarefaAntiga.setStatus(novaTarefa.getStatus());
        
        tarefaRepository.save(tarefaAntiga);
        return tarefaAntiga;
    }

    public void deletarTarefa(Long idProjeto, Long idTarefa){
        Projeto projeto = projetoRepository.findById(idProjeto)
            .orElseThrow(() -> new RuntimeException("Projeto não encontrado!"));

        Tarefa novaTarefa = projeto.getTarefas().stream()
            .filter(trf -> trf.getId().equals(idTarefa))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Tarefa não encontrada!"));

        projeto.getTarefas().remove(novaTarefa);
        projetoRepository.save(projeto);
        tarefaRepository.delete(novaTarefa);
    }

    public Tarefa buscarTarefa(Long idProjeto, Long idTarefa){
        Projeto projeto = projetoRepository.findById(idProjeto)
            .orElseThrow(() -> new RuntimeException("Projeto não encontrado!"));

        Tarefa novaTarefa = projeto.getTarefas().stream()
            .filter(trf -> trf.getId().equals(idTarefa))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Tarefa não encontrada!"));

        return novaTarefa;   
    }
    public Tarefa atualizarStatus(Long idProjeto, Long idTarefa, StatusTarefa novoStatus) {
        Projeto projeto = projetoRepository.findById(idProjeto)
            .orElseThrow(() -> new RuntimeException("Projeto não encontrado!"));
        
        Tarefa tarefa = projeto.getTarefas().stream()
            .filter(t -> t.getId().equals(idTarefa))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Tarefa não encontrada!"));
        
        tarefa.setStatus(novoStatus);
        return tarefaRepository.save(tarefa);
    }

}
