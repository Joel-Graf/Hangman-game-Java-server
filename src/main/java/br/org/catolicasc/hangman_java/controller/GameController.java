package br.org.catolicasc.hangman_java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.org.catolicasc.hangman_java.bean.Game;
import br.org.catolicasc.hangman_java.bean.User;
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
    testUser.setId(1);

    // TODO return se não tiver logado
    // if (testUser == null) {
    // return "redirect:/login";
    // }

    // FIXME: Está logado, procura jogo em progresso
    Game existingGame = gameRepository.getUserByUserId(testUser.getId());
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
}
