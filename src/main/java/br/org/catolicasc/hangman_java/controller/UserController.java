package br.org.catolicasc.hangman_java.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import br.org.catolicasc.hangman_java.bean.User;
import br.org.catolicasc.hangman_java.dto.RequestUserCreate;
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

    List<User> existingUsers = userRepository.getUserByLogin(request.getLogin());
    if (existingUsers.size() > 0) {
      model.addAttribute("error", "Duplicated User"); // FIXME: Passar erro de um jeito melhor
      return "user-create";
    }

    User user = request.toUser();
    this.userRepository.save(user);
    return "redirect:/game"; // TODO: Passar usuário logado

  }
}
