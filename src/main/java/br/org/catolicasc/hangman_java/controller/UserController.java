package br.org.catolicasc.hangman_java.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import br.org.catolicasc.hangman_java.bean.User;
import br.org.catolicasc.hangman_java.dto.RequestUserCreate;
import br.org.catolicasc.hangman_java.dto.RequestUserLogin;
import br.org.catolicasc.hangman_java.repository.UserRepository;

@Controller
public class UserController {

  @Autowired
  private UserRepository userRepository;

  @GetMapping("user/create")
  public String createForm(RequestUserCreate request) {
    return "user-create";
  }

  @PostMapping("user/create")
  public String create(@Valid RequestUserCreate request, BindingResult result, Model model) {

    if (result.hasErrors()) {
      return "user-create";
    }

    User existingUser = userRepository.getUserByLogin(request.getLogin());
    if (existingUser != null) {
      model.addAttribute("error", "Duplicated User");
      return "user-create";
    }

    User user = request.toUser();
    this.userRepository.save(user);
    return "redirect:/user/login";
  }

  @GetMapping("user/login")
  public String loginForm(RequestUserLogin request) {
    return "user-login";
  }

  @PostMapping("user/login")
  public String login(@Valid RequestUserLogin request, BindingResult result, Model model) {

    if (result.hasErrors()) {
      return "user-login";
    }

    User loggedUser = userRepository.getUserByLoginAndPassword(request.getLogin(), request.getPassword());

    if (loggedUser == null) {
      model.addAttribute("error", "Invalid User");
      return "user-login";
    }

    return "redirect:/game";
  }

}
