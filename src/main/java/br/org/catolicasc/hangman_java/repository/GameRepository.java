package br.org.catolicasc.hangman_java.repository;

import org.springframework.stereotype.Repository;

import br.org.catolicasc.hangman_java.bean.Game;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

@Repository
public interface GameRepository extends CrudRepository<Game, Long> {

  @Query("select g from Game g where g.user.id = :userId")
  Game getUserByUserId(@Param("userId") int userId);

}
