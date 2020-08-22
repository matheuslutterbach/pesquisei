package br.com.softworks.pesquisei.repository;

import br.com.softworks.pesquisei.model.Bairro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BairroRepository extends JpaRepository<Bairro, Long> {}
