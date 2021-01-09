package br.com.barbatto.concurso.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.barbatto.concurso.entities.Album;

/**
 * Repositorio da tabela Album
 * @author Karen Barbato
 * @since 06/01/2020
 */
@Repository
public interface AlbumRepository extends JpaRepository<Album, Integer>{

	@Query("FROM Album a WHERE LOWER(a.nome) like %:nome% ")
	Page<Album> search(@Param("nome") String nome, Pageable pageable);
	
}
