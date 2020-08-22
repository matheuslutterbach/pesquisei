package br.com.softworks.pesquisei.service;

import br.com.softworks.pesquisei.model.Cidade;
import br.com.softworks.pesquisei.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CidadeService {

    @Autowired private CidadeRepository cidadeRepository;

    public List<Cidade> buscarCidades() {
        return cidadeRepository.findAll();
    }

    public Cidade cadastrar(Cidade cidade) {
        return cidadeRepository.save(cidade);
    }
}
