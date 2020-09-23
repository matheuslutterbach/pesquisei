package br.com.softworks.pesquisei.service;


import br.com.softworks.pesquisei.model.Resposta;
import br.com.softworks.pesquisei.repository.RespostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RespostaService {

    @Autowired
    private RespostaRepository repository;

    public List<Resposta> buscar() {
        return repository.findAll();
    }
}