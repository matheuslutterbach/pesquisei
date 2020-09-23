package br.com.softworks.pesquisei.repository;

import br.com.softworks.pesquisei.model.Resposta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RespostaRepository extends JpaRepository<Resposta, Long> {
}
