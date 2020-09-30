package br.com.softworks.pesquisei.repository;

import br.com.softworks.pesquisei.model.Bairro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BairroRepository extends JpaRepository<Bairro, Long> {
    List<Bairro> findByCidadeId(Long idCidade);


    @Query("FROM Bairro b " +
            "WHERE LOWER(b.nome) like %:bairro% ")
    Page<Bairro> buscarPaginado(
            @Param("bairro") String searchTerm,
            Pageable pageable);
}
