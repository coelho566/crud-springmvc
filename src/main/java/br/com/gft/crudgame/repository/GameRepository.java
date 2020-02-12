package br.com.gft.crudgame.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gft.crudgame.model.Game;

/**
 * Camada do repository da aplicação
 * 
 * @author Leandro
 * @version 1.0
 */
@Repository
public interface GameRepository extends JpaRepository<Game, Long > {
	
	public List<Game> findByNomeContaining(String nome);
	
	
}
