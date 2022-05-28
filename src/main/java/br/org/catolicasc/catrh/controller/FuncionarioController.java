package br.org.catolicasc.catrh.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.org.catolicasc.catrh.bean.Funcionario;
import br.org.catolicasc.catrh.dto.RequisicaoNovoFuncionario;
import br.org.catolicasc.catrh.repository.CargoRepository;
import br.org.catolicasc.catrh.repository.FuncionarioRepository;

@Controller
@RequestMapping("/funcionario")
public class FuncionarioController {

	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@Autowired
	private CargoRepository cargoRepository;
	
	@GetMapping("formulario")
	public String formulario(RequisicaoNovoFuncionario requisicao) {
		return "funcionario/formulario";
	}
	@PostMapping("novo")
	public String novo(@Valid RequisicaoNovoFuncionario requisicao, 
								BindingResult result) {
		if(result.hasErrors()) {
			return "funcionario/formulario";
		}
		Funcionario funcionario = requisicao.toFuncionario(this.cargoRepository);		
		this.funcionarioRepository.save(funcionario);
		return "redirect:/home";
	}
}
