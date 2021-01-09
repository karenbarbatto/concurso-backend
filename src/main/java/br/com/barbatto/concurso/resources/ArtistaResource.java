package br.com.barbatto.concurso.resources;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
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

import br.com.barbatto.concurso.entities.Artista;
import br.com.barbatto.concurso.exception.RecordNotFoundException;
import br.com.barbatto.concurso.services.ArtistaService;

/**
 * Controller que irá ter os endpoins para acessar o artista
 * @author Karen Barbato
 * @since 06/01/2021
 */
@RestController
@RequestMapping("/artista")
public class ArtistaResource {

	@Autowired
    ArtistaService service;

    @GetMapping("/search")
    public ResponseEntity<List<Artista>> search(
            @RequestParam("nome") String nome,
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
    	Page<Artista> resultado = service.search(nome, page, size);
        if(resultado.isEmpty())
	   	 {
	   		 throw new RecordNotFoundException("Artista ["+ nome +"] não encontrado");
	   	 }
   	     return new ResponseEntity<List<Artista>>(resultado.getContent(), HttpStatus.OK);
    }

    @GetMapping
    public Page<Artista> getAll() {
        return service.findAll(Sort.Direction.ASC);
    }
    
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping(value = "/add")
    public ResponseEntity<Artista> add (@Valid @RequestBody Artista novo)
    {
        return new ResponseEntity<Artista>(novo, HttpStatus.OK);
    }
    
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(value = "/atualizar")
    public ResponseEntity<Artista> atualizar (@Valid @RequestBody Artista artista)
    {
    	Artista artistaAlterado = service.atualizar(artista, artista.getCodigo());
        return new ResponseEntity<Artista>(artistaAlterado, HttpStatus.OK);
    }
}
