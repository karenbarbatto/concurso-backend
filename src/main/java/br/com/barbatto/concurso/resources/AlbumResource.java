package br.com.barbatto.concurso.resources;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.barbatto.concurso.entities.Album;
import br.com.barbatto.concurso.exception.RecordNotFoundException;
import br.com.barbatto.concurso.services.AlbumService;

/**
 * Controller que irá ter os endpoins para acessar o album
 * @author Karen Barbato
 * @since 06/01/2021
 */
@RestController
@RequestMapping("/album")
public class AlbumResource {

	@Autowired
    AlbumService service;

    @GetMapping("/search")
    public ResponseEntity<List<Album>> search(
            @RequestParam("nome") String nome,
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
    	Page<Album> resultado = service.search(nome.toLowerCase(), page, size);
    	if(resultado.isEmpty())
	   	 {
	   		 throw new RecordNotFoundException("Album ["+ nome +"] não encontrado");
	   	 }
    	return new ResponseEntity<List<Album>>(resultado.getContent(), HttpStatus.OK);
    }
    
    @GetMapping("/all")
    public Page<Album> getAll() {
        return service.findAll(Sort.Direction.ASC);
    }

    @GetMapping("/allSort")
    public Page<Album> getAll( @RequestParam("ordenacao") Direction ordenacao) {
        return service.findAll(ordenacao);
    }
    
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping(value = "/add")
    public ResponseEntity<Album> add (@Valid @RequestBody Album novo)
    {
        
        return new ResponseEntity<Album>(novo, HttpStatus.OK);
    }
    
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(value = "/atualizar")
    public ResponseEntity<Album> atualizar (@Valid @RequestBody Album album)
    {
        Album albumAlterado = service.atualizar(album, album.getCodigo());
        return new ResponseEntity<Album>(albumAlterado, HttpStatus.OK);
    }
}
