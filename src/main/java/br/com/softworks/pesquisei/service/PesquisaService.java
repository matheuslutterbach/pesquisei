package br.com.softworks.pesquisei.service;

import br.com.softworks.pesquisei.exception.model.ServiceException;
import br.com.softworks.pesquisei.model.Pesquisa;
import br.com.softworks.pesquisei.repository.PesquisaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PesquisaService {

    @Autowired private PesquisaRepository pesquisaRepository;

    public List<Pesquisa> buscar() {
        return pesquisaRepository.findAll();
    }

    public Pesquisa cadastrar(Pesquisa pesquisa) {
        pesquisa.setDataCricao(new Date());

        return pesquisaRepository.save(pesquisa);
    }


    public Pesquisa buscarPorId(Long id) {
        Optional<Pesquisa> pesquisa = pesquisaRepository.findById(id);

        return pesquisa.orElseThrow(() -> new ServiceException("Pesquisa", id));
    }
}
