package br.com.softworks.pesquisei.repository;

import br.com.softworks.pesquisei.model.Pergunta;
import br.com.softworks.pesquisei.model.Resultado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerguntaRepository extends JpaRepository<Pergunta, Long> {
}
