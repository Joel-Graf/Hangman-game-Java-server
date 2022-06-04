package br.org.catolicasc.hangman_java.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.org.catolicasc.hangman_java.bean.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

  @Query("select u from User u where u.login = :login")
  List<User> getUserByLogin(@Param("login") String login);

}
