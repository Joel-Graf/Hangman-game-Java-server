package br.org.catolicasc.hangman_java.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.org.catolicasc.hangman_java.bean.Word;

@Repository
public interface WordRepository extends CrudRepository<Word, Long> {

  @Query("select w from Word w where w.dificulty = :dificulty")
  List<Word> getWordsByDificulty(@Param("dificulty") int dificulty);

}
