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

import br.com.barbatto.concurso.entities.Album;
import br.com.barbatto.concurso.repository.AlbumRepository;

/**
 * Service com consultas customizadas da tabela album
 * @author Karen Barbato
 * @since 06/01/2021
 */
@Service
public class AlbumService 
{

	@Autowired
	private AlbumRepository repository;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public Page<Album> search( String nome, int page, int size) {
        PageRequest pageRequest = PageRequest.of(
                page,
                size,
                Sort.Direction.ASC,
                "nome");

        return repository.search(nome,pageRequest);
    }

    public Page<Album> findAll(Direction sort) {
        int page = 0;
        int size = 10;
        PageRequest pageRequest = PageRequest.of(
                page,
                size,
                sort,
                "nome");
        return new PageImpl<>(  repository.findAll(), pageRequest, size);
    }
    
    public Album atualizar(Album a, Integer id)
    {
    	try 
    	{
    		Album albumEncontrado = entityManager.getReference(Album.class, id);
			BeanUtils.copyProperties(a, albumEncontrado);
			albumEncontrado = repository.save(albumEncontrado);
			return albumEncontrado;
		} 
    	catch (Exception e) 
    	{
			e.printStackTrace();
		}
    	return null;
    }
}
