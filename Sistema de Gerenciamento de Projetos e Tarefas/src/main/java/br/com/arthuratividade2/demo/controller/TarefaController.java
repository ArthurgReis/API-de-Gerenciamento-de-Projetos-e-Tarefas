package br.com.arthuratividade2.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.arthuratividade2.demo.model.Projeto;
import br.com.arthuratividade2.demo.model.StatusTarefa;
import br.com.arthuratividade2.demo.model.Tarefa;
import br.com.arthuratividade2.demo.service.ProjetoService;
import br.com.arthuratividade2.demo.service.TarefaService;

@RestController
@RequestMapping("/trabalho/projeto/{idProjeto}/tarefa")
public class TarefaController {

    private final TarefaService tarefaService;
    private final ProjetoService projetoService;
    
    public TarefaController(TarefaService tarefaService, ProjetoService projetoService) {
        this.tarefaService = tarefaService;
        this.projetoService = projetoService;
    }

    @PostMapping
    public ResponseEntity<Tarefa> criarTarefa(@PathVariable Long idProjeto, @RequestBody Tarefa tarefa){
        Tarefa novaTarefa = tarefaService.criarTarefa(idProjeto, tarefa);
        return ResponseEntity.ok(novaTarefa);
    }

    @GetMapping
    public ResponseEntity<List<Tarefa>> listarTarefas(@PathVariable Long idProjeto){
        Projeto projeto = projetoService.buscarPorId(idProjeto);
        List<Tarefa> tarefas = tarefaService.listarTarefasPorProjeto(projeto);
        return ResponseEntity.ok(tarefas);
    }

    @GetMapping("/{idTarefa}")
    public ResponseEntity<Tarefa> buscarTarefa(@PathVariable Long idProjeto, @PathVariable Long idTarefa){
        Tarefa tarefa = tarefaService.buscarTarefa(idProjeto, idTarefa);
        return ResponseEntity.ok(tarefa);
    }

    @PutMapping("/atualizar/{idTarefa}")
    public ResponseEntity<Tarefa> atualizarTarefa(@PathVariable Long idProjeto, @PathVariable Long idTarefa, @RequestBody Tarefa tarefa){
        Tarefa novaTarefa = tarefaService.alterarTarefa(idProjeto, idTarefa, tarefa);
        return ResponseEntity.ok(novaTarefa);
    }

    @DeleteMapping("/deletar/{idTarefa}")
    public ResponseEntity<Void> deletarTarefa(@PathVariable Long idProjeto, @PathVariable Long idTarefa){
        tarefaService.deletarTarefa(idProjeto, idTarefa);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{idTarefa}/iniciar")
    public ResponseEntity<Tarefa> iniciarTarefa(
            @PathVariable Long idProjeto, 
            @PathVariable Long idTarefa) {
        
        Tarefa tarefa = tarefaService.atualizarStatus(idProjeto, idTarefa, StatusTarefa.EM_ANDAMENTO);
        return ResponseEntity.ok(tarefa);
    }
    
    @PatchMapping("/{idTarefa}/concluir")
    public ResponseEntity<Tarefa> concluirTarefa(
            @PathVariable Long idProjeto, 
            @PathVariable Long idTarefa) {
        
        Tarefa tarefa = tarefaService.atualizarStatus(idProjeto, idTarefa, StatusTarefa.CONCLUIDA);
        return ResponseEntity.ok(tarefa);
    }
    
    @PatchMapping("/{idTarefa}/reabrir")
    public ResponseEntity<Tarefa> reabrirTarefa(
            @PathVariable Long idProjeto, 
            @PathVariable Long idTarefa) {
        
        Tarefa tarefa = tarefaService.atualizarStatus(idProjeto, idTarefa, StatusTarefa.PENDENTE);
        return ResponseEntity.ok(tarefa);
    }

    @GetMapping("/descricao/{descricao}")
    public ResponseEntity<Tarefa> buscarTarefaPorDescricao(@PathVariable Long idProjeto, @PathVariable String descricao){
        Tarefa tarefa = tarefaService.buscarTarefaPorNome(idProjeto, descricao);

        return ResponseEntity.ok(tarefa);
    }
    
}

