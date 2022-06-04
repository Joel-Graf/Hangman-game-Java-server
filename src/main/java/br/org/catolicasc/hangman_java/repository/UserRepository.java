package br.org.catolicasc.hangman_java.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.org.catolicasc.hangman_java.bean.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

  @Query("select u from User u where u.login = :login")
  User getUserByLogin(@Param("login") String login);

  @Query("select u from User u where u.login = :login and u.password = :password ")
  User getUserByLoginAndPassword(@Param("login") String login, @Param("password") String password);

}
