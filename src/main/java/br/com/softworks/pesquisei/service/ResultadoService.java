package br.com.softworks.pesquisei.service;


import br.com.softworks.pesquisei.dto.RetornoResultadoDTO;
import br.com.softworks.pesquisei.model.Pergunta;
import br.com.softworks.pesquisei.model.Resultado;
import br.com.softworks.pesquisei.repository.ResultadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.LinkedList;
import java.util.List;

@Service
public class ResultadoService {

    @Autowired private ResultadoRepository repository;

    @Autowired private EntityManager entityManager;


    public List<Resultado> buscar() {
        return repository.findAll();
    }

    public List<Resultado> cadastrar(List<Resultado> resultados) {
        resultados.forEach(resultado -> {
            repository.save(resultado);
        });
        return resultados;
    }

    public List<RetornoResultadoDTO> buscarConsolidadoPergunta(Long idPergunta) {
        String sql = "SELECT " +
                "resultado.id id, " +
                "pergunta.descricao pergunta, " +
                "resposta.descricao resposta, " +
                "count(resposta.descricao) total " +
                "FROM tbl_resultado resultado\n" +
                "INNER JOIN tbl_pergunta pergunta ON resultado.id_pergunta = pergunta.id \n" +
                "INNER JOIN tbl_resposta resposta ON resultado.id_resposta = resposta.id\n" +
                "WHERE pergunta.id = :idPergunta " +
                "group by resposta.descricao;";

        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("idPergunta", idPergunta);

        List<Object[]> result = query.getResultList();


        List<RetornoResultadoDTO> resultadoDTOS = new LinkedList<>();
        for (Object[] a : result) {
            resultadoDTOS.add(RetornoResultadoDTO.builder()
                    .pergunta(a[1].toString())
                    .resposta(a[2].toString())
                    .total(Long.parseLong(a[3].toString()))
                    .build());
        }

        return resultadoDTOS;
    }

    public List<RetornoResultadoDTO> buscarConsolidadoPerguntaBairro(Long idPergunta, Long idBairro) {
        String sql = "SELECT " +
                "resultado.id id, " +
                "pergunta.descricao pergunta, " +
                "resposta.descricao resposta, " +
                "count(resposta.descricao) total " +
                "FROM tbl_resultado resultado\n" +
                "INNER JOIN tbl_pergunta pergunta ON resultado.id_pergunta = pergunta.id \n" +
                "INNER JOIN tbl_resposta resposta ON resultado.id_resposta = resposta.id\n" +
                "WHERE pergunta.id = :idPergunta AND resultado.id_bairro = :idBairro " +
                "group by resposta.descricao;";

        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("idPergunta", idPergunta);
        query.setParameter("idBairro", idBairro);

        List<Object[]> result = query.getResultList();


        List<RetornoResultadoDTO> resultadoDTOS = new LinkedList<>();
        for (Object[] a : result) {
            resultadoDTOS.add(RetornoResultadoDTO.builder()
                    .pergunta(a[1].toString())
                    .resposta(a[2].toString())
                    .total(Long.parseLong(a[3].toString()))
                    .build());
        }

        return resultadoDTOS;
    }

    public List<RetornoResultadoDTO> buscarResultados(Long idPergunta) {
        String sql = "SELECT " +
                "resultado.id id, " +
                "pergunta.descricao pergunta,  " +
                "resposta.descricao resposta,  " +
                "resultado.momento momento     " +
                "FROM tbl_resultado resultado\n " +
                "INNER JOIN tbl_pergunta pergunta ON resultado.id_pergunta = pergunta.id \n " +
                "INNER JOIN tbl_resposta resposta ON resultado.id_resposta = resposta.id\n " +
                "WHERE pergunta.id = :idPergunta";

        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("idPergunta", idPergunta);

        List<Object[]> resultList = query.getResultList();

        List<RetornoResultadoDTO> resultadoDTOS = new LinkedList<>();
        for (Object[] a : resultList) {
            resultadoDTOS.add(RetornoResultadoDTO.builder()
                    .pergunta(a[1].toString())
                    .resposta(a[2].toString())
                    .build());
        }

        return resultadoDTOS;
    }


    public List<RetornoResultadoDTO> exportarCsv(Long idPesquisa) {
        String sql = "SELECT pergunta.descricao pergunta,\n" +
                "                   resposta.descricao resposta,\n" +
                "                       bairro.nome bairro,\n" +
                "                       cidade.nome cidade\n" +
                "                 FROM tbl_resultado resultado\n" +
                "                LEFT JOIN tbl_pergunta pergunta ON pergunta.id = resultado.id_pergunta\n" +
                "                LEFT JOIN tbl_resposta resposta ON resposta.id = resultado.id_resposta\n" +
                "                LEFT JOIN tbl_bairro bairro ON bairro.id = resultado.id_bairro\n" +
                "                LEFT JOIN tbl_cidade cidade ON cidade.id = bairro.id_cidade where pergunta.id_pesquisa = :idPesquisa";


        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("idPesquisa", idPesquisa);

        List<Object[]> resultList = query.getResultList();

        List<RetornoResultadoDTO> resultadoDTOS = new LinkedList<>();
        for (Object[] a : resultList) {
            resultadoDTOS.add(RetornoResultadoDTO.builder()
                    .pergunta(a[0].toString())
                    .resposta(a[1].toString())
                    .bairro(a[2].toString())
                    .cidade(a[3].toString())
                    .build());
        }

        return resultadoDTOS;
    }

    public void deletarPorPergunta(Pergunta pergunta) {
        List<Resultado> resultados = repository.findAllByPerguntaId(pergunta.getId());

        repository.deleteAll(resultados);
    }
}