package br.org.catolicasc.hangman_java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.org.catolicasc.hangman_java.bean.Game;
import br.org.catolicasc.hangman_java.bean.User;
import br.org.catolicasc.hangman_java.bean.Word;
import br.org.catolicasc.hangman_java.repository.GameRepository;

@Controller
public class GameController {

  @Autowired
  private GameRepository gameRepository;

  @Autowired
  private WordController wordController;

  @GetMapping("/game")
  public String game(Model model) {
    // FIXME: User chumbado, deve vir (caso logado) pela rota
    User testUser = new User();
    testUser.setId(1L);

    // TODO return se não tiver logado
    // if (testUser == null) {
    // return "redirect:/login";
    // }

    // FIXME: Está logado, procura jogo em progresso
    Game existingGame = gameRepository.getGameByUserId(testUser.getId());
    if (existingGame == null) {
      Game newGame = new Game();
      newGame.setDificulty(1);
      newGame.setPlayerLife(5);
      newGame.setUser(testUser);
      newGame.setWord(wordController.getWord(1));

      gameRepository.save(newGame);
      existingGame = newGame;
    }

    return "game"; // TODO: Passar existingGame
  }

  @GetMapping("/game/defeat")
  public String defeat() {
    return "defeat";
  }

  @GetMapping("/game/victory")
  public String victory() {
    return "victory";
  }

  // TODO: Testar, chamar ao
  @PostMapping("/game/correct_word")
  String correctWord(@RequestBody Game game, @RequestBody User user) {
    int nextDificulty = game.getDificulty() + 1;
    Word nextWord = wordController.getWord(nextDificulty);

    if (nextWord == null) {
      gameRepository.delete(game);
      return "redirect:/victory";
    }

    game.setDificulty(nextDificulty);
    game.setWord(nextWord);
    gameRepository.save(game);
    return "redirect:/game";
  }

  // TODO: Testar, chamar ao perder palavra
  @PostMapping("/game/incorrect_word")
  String incorrectWord(@RequestBody Game game, @RequestBody User user) {

    int newLife = game.getPlayerLife() - 1;
    if (newLife > 0) {
      game.setPlayerLife(newLife);
      return "redirect:/game";
    }

    return "redirect:/defeat";
  }

  // TODO: Testar, Chamar ao reiniciar jogo na tela de vitória e derrota!!
  @DeleteMapping("/game/delete/{id}")
  String delete(@PathVariable Long id) {
    gameRepository.deleteById(id);
    return "redirect:/game";
  }
}
