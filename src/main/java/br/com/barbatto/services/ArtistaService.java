package br.com.barbatto.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.barbatto.entities.Artista;
import br.com.barbatto.repository.ArtistaRepository;

/**
 * Service com consultas customizadas da tabela artista
 * @author Karen Barbato
 * @since 06/01/2020
 */
@Service
public class ArtistaService {

	@Autowired
	private ArtistaRepository repository;
	
	public Page<Artista> search( String nome, int page, int size) {
        PageRequest pageRequest = PageRequest.of(
                page,
                size,
                Sort.Direction.ASC,
                "nome");

        return repository.search(nome.toUpperCase(),pageRequest);
    }

    public Page<Artista> findAll() {
        int page = 0;
        int size = 10;
        PageRequest pageRequest = PageRequest.of(
                page,
                size,
                Sort.Direction.ASC,
                "name");
        return new PageImpl<>(  repository.findAll(), pageRequest, size);
    }
}
