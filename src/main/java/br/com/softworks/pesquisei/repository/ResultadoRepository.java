package br.com.softworks.pesquisei.repository;

import br.com.softworks.pesquisei.model.Resultado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultadoRepository extends JpaRepository<Resultado, Long> {
    List<Resultado> findAllByPerguntaId(Long idPergunta);
}
