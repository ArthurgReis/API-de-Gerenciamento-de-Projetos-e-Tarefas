package br.com.arthuratividade2.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.arthuratividade2.demo.model.Projeto;
import br.com.arthuratividade2.demo.service.ProjetoService;

@RestController
@RequestMapping("/trabalho/projeto")
public class ProjetoController {

    private final ProjetoService projetoService;

    public ProjetoController(ProjetoService projetoService) {
        this.projetoService = projetoService;
    }
    
    @PostMapping("/cadastrar")
    public ResponseEntity<Projeto> criarProjeto(@RequestBody Projeto projeto){
        Projeto novoProjeto = projetoService.criarProjeto(projeto);

        return ResponseEntity.status(HttpStatus.CREATED).body(novoProjeto);
    }

    @GetMapping
    public ResponseEntity<List<Projeto>> listarTodos(){
        List<Projeto> projetos = projetoService.listarProjetos()
            .stream().toList();
        return ResponseEntity.ok(projetos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Projeto> buscarProjeto(@PathVariable Long id){
        Projeto projeto = projetoService.buscarPorId(id);
        return ResponseEntity.ok(projeto);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Projeto> atualizarProjeto(@PathVariable Long id, @RequestBody Projeto projeto){
        Projeto novoProjeto = projetoService.atualizarProjeto(id, projeto);
        return ResponseEntity.ok(novoProjeto);

    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarProjeto(@PathVariable Long id){
        projetoService.deletarProjeto(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/nome/{nomeDoProjeto}")
    public ResponseEntity<Projeto> buscarProjetoPorNome(@PathVariable String nomeDoProjeto){
        Projeto projeto = projetoService.buscarPorNome(nomeDoProjeto);
        return ResponseEntity.ok(projeto);
    }

}
