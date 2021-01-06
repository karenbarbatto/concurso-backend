package br.com.barbatto.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidade da tabela public.album
 * @author Karen Barbato
 * @since 06/01/2021
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Artista implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer codigo;
	
	@Column(name = "nome")
	private String nome;
	
	@OneToMany(mappedBy =  "artista")
	private List<Album> albuns;
}
