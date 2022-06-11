package br.org.catolicasc.hangman_java.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GameController {

  @GetMapping("/game")
  public String game(Model model) {
    return "game";
  }

  @GetMapping("/game_defeat")
  public String defeat() {
    return "defeat";
  }

  @GetMapping("/game_victory")
  public String victory() {
    return "victory";
  }

}