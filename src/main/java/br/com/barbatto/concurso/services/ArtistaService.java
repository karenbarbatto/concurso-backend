package br.com.barbatto.concurso.services;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.barbatto.concurso.entities.Artista;
import br.com.barbatto.concurso.repository.ArtistaRepository;

/**
 * Service com consultas customizadas da tabela artista
 * @author Karen Barbato
 * @since 06/01/2021
 */
@Service
public class ArtistaService {

	@Autowired
	private ArtistaRepository repository;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public Page<Artista> search( String nome, int page, int size) {
        PageRequest pageRequest = PageRequest.of(
                page,
                size,
                Sort.Direction.ASC,
                "nome");

        return repository.search(nome,pageRequest);

	}

    public Page<Artista> findAll() {
        int page = 0;
        int size = 10;
        PageRequest pageRequest = PageRequest.of(
                page,
                size,
                Sort.Direction.ASC,
                "nome");
        return new PageImpl<>(  repository.findAll(), pageRequest, size);
    }
    
    public Page<Artista> findAll(Direction sort) {
        int page = 0;
        int size = 10;
        PageRequest pageRequest = PageRequest.of(
                page,
                size,
                sort,
                "nome");
        return new PageImpl<>(  repository.findAll(), pageRequest, size);
    }
    
    public Artista atualizar(Artista a, Integer id)
    {
    	try 
    	{
    		Artista artistaEncontrado = entityManager.getReference(Artista.class, id);
			BeanUtils.copyProperties(a, artistaEncontrado);
			artistaEncontrado = repository.save(artistaEncontrado);
			return artistaEncontrado;
		} 
    	catch (Exception e) 
    	{
			e.printStackTrace();
		}
    	return null;
    }
}
