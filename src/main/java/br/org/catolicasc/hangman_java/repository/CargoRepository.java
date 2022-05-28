package br.org.catolicasc.hangman_java.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.org.catolicasc.hangman_java.bean.Cargo;

@Repository
public interface CargoRepository extends PagingAndSortingRepository<Cargo, Long>,
 JpaSpecificationExecutor<Cargo>{

	List<Cargo> findByDescricao(String descricao);
	
	@Query("select c from Cargo c where c.id > :id")
	List<Cargo> findCargoIdMaior(@Param("id") Long id);
	
	@Query(value = "select * from Cargo c where c.id > :id", 
			nativeQuery = true)
	List<Cargo> findCargoIdMaiorNative(@Param("id") Long id);
}
