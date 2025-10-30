package br.com.arthuratividade2.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.arthuratividade2.demo.model.Projeto;
import br.com.arthuratividade2.demo.repository.ProjetoRepository;

@Service
public class ProjetoService {
    private final ProjetoRepository projetoRepository;


    public ProjetoService(ProjetoRepository projetoRepository) {
        this.projetoRepository = projetoRepository;
    }

    public Projeto criarProjeto(Projeto projeto){
        return projetoRepository.save(projeto);
    }

    public List<Projeto> listarProjetos(){
        return projetoRepository.findAll();
    }

    public Projeto buscarPorId(Long id){
        return projetoRepository.findById(id)
            .orElseThrow(()-> new RuntimeException("Projeto não encontrado!"));
    }

    public Projeto atualizarProjeto(Long id, Projeto novoProjeto){
        Projeto projeto = projetoRepository.findById(id)
            .orElseThrow(()-> new RuntimeException("Projeto não encontrado!"));

        projeto.setDescricao(novoProjeto.getDescricao());
        projeto.setNome(novoProjeto.getNome());
        
        return projetoRepository.save(projeto);
    }

    public void deletarProjeto(Long id){
        Projeto projeto = projetoRepository.findById(id)
            .orElseThrow(()-> new RuntimeException("Projeto não encontrado!"));

        projetoRepository.delete(projeto);
    }



}
