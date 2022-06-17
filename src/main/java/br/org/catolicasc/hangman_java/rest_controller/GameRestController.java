package br.org.catolicasc.hangman_java.rest_controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.catolicasc.hangman_java.bean.Game;
import br.org.catolicasc.hangman_java.bean.User;
import br.org.catolicasc.hangman_java.bean.Word;
import br.org.catolicasc.hangman_java.repository.GameRepository;

@RestController
public class GameRestController {

  @Autowired
  private GameRepository gameRepository;

  @Autowired
  private WordRestController wordRestController;

  @CrossOrigin(origins = "http://localhost:8180")
  @GetMapping("/game/game_in_progress")
  Game getGameInProgress() {

    // TODO: Receber e validar user, dar return se n for valido
    User testUser = new User();
    testUser.setId(1L);

    Game existingGame = gameRepository.getGameByUserId(testUser.getId());
    if (existingGame == null) {
      Game newGame = new Game();
      newGame.setDificulty(1);
      newGame.setPlayerLife(5);
      newGame.setUser(testUser);
      newGame.setWord(wordRestController.getWord(1));

      existingGame = gameRepository.save(newGame);
    }

    return existingGame;
  }

  @CrossOrigin(origins = "http://localhost:8180")
  @GetMapping("/game/correct_word")
  Game correctWord() {

    // TODO: Receber e validar user, dar return se n for valido
    User testUser = new User();
    testUser.setId(1L);

    Game game = gameRepository.getGameByUserId(testUser.getId());
    if (game == null) {
      return null;
    }

    int nextDificulty = game.getDificulty() + 1;
    Word nextWord = wordRestController.getWord(nextDificulty);
    if (nextWord == null) {
      gameRepository.delete(game);
      return null;
    }

    game.setDificulty(nextDificulty);
    game.setWord(nextWord);
    return gameRepository.save(game);
  }

  @CrossOrigin(origins = "http://localhost:8180")
  @GetMapping("/game/incorrect_word")
  Game incorrectWord() {

    // TODO: Receber e validar user, dar return se n for valido
    User testUser = new User();
    testUser.setId(1L);

    Game game = gameRepository.getGameByUserId(testUser.getId());
    if (game == null) {
      return null;
    }

    int newLife = game.getPlayerLife() - 1;
    if (newLife > 0) {
      game.setPlayerLife(newLife);
      return gameRepository.save(game);
    }

    gameRepository.delete(game);
    return null;
  }

}