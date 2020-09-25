package br.com.softworks.pesquisei.service;

import br.com.softworks.pesquisei.exception.model.ServiceException;
import br.com.softworks.pesquisei.model.Bairro;
import br.com.softworks.pesquisei.repository.BairroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BairroService {

    @Autowired
    private BairroRepository bairroRepository;

    public List<Bairro> buscar() {
        return bairroRepository.findAll();
    }


    public List<Bairro> buscarPorCidade(Long idCidade) {
        return bairroRepository.findByCidadeId(idCidade);
    }


    public Bairro cadastrar(Bairro bairro) {
        bairro.setAlteracao(new Date());
        return bairroRepository.save(bairro);
    }

    public Bairro buscarPorId(Long id) {
        Optional<Bairro> bairro = bairroRepository.findById(id);

        return bairro.orElseThrow(() -> new ServiceException("Bairro", id));
    }





}
