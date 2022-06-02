package br.org.catolicasc.hangman_java.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GameController {
  @GetMapping("/game")
  public String hello(Model model) {
    //TODO: Pegar usuário, verificar se existe jogo em progresso ou não
    model.addAttribute("nome", "Blasius");
    return "game";
    //TODO: Se o perdeu o jogo da return "defeat" senão return "victory"
  }
}
