package br.com.softworks.pesquisei.service;


import br.com.softworks.pesquisei.model.Pergunta;
import br.com.softworks.pesquisei.repository.PerguntaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PerguntaService {

    @Autowired
    private PerguntaRepository repository;

    public List<Pergunta> buscar() {
        return repository.findAll();
    }
}