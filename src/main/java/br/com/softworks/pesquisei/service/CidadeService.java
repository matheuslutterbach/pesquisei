package br.com.softworks.pesquisei.service;

import br.com.softworks.pesquisei.exception.model.ServiceException;
import br.com.softworks.pesquisei.model.Cidade;
import br.com.softworks.pesquisei.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CidadeService {

    @Autowired private CidadeRepository cidadeRepository;

    public List<Cidade> buscar() {
        return cidadeRepository.findAll();
    }

    public Cidade cadastrar(Cidade cidade) {
        return cidadeRepository.save(cidade);
    }

    public Cidade buscarPorId(Long id) {
        Optional<Cidade> cidade = cidadeRepository.findById(id);

        return cidade.orElseThrow(() -> new ServiceException("Cidade", id));
    }
}
