package br.org.catolicasc.catrh.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.org.catolicasc.catrh.bean.Automovel;
import br.org.catolicasc.catrh.bean.AutomovelResumido;

@Repository
public interface AutomovelRepository 
		extends CrudRepository<Automovel, Long> {
	
	@Query(value = "select id, modelo, marca from Automovel ", 
				nativeQuery = true)
	List<AutomovelResumido> findAutomovelResumido();
	

}
