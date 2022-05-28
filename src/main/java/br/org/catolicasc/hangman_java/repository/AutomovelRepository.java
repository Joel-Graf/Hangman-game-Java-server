package br.org.catolicasc.hangman_java.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.org.catolicasc.hangman_java.bean.Automovel;
import br.org.catolicasc.hangman_java.bean.AutomovelResumido;

@Repository
public interface AutomovelRepository 
		extends CrudRepository<Automovel, Long> {
	
	@Query(value = "select id, modelo, marca from Automovel ", 
				nativeQuery = true)
	List<AutomovelResumido> findAutomovelResumido();
	

}
