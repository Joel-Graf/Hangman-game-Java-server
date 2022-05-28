package br.org.catolicasc.hangman_java.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.org.catolicasc.hangman_java.bean.Funcionario;
import br.org.catolicasc.hangman_java.repository.FuncionarioRepository;

@Controller
@RequestMapping("/home")
public class HomeController {

	@Autowired
	private FuncionarioRepository repository;
	
	@GetMapping
	public String hello(Model model) {
		List<Funcionario> funcionarios = 
					(List<Funcionario>) repository.findAll();
		model.addAttribute("funcionarios", funcionarios);
		return "home";
	}
}
