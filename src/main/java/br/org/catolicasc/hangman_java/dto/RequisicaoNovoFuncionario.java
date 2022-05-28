package br.org.catolicasc.hangman_java.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;

import br.org.catolicasc.hangman_java.bean.Funcionario;
import br.org.catolicasc.hangman_java.repository.CargoRepository;

public class RequisicaoNovoFuncionario {

	@NotBlank
	private String funcionarioNome;
	@NotBlank
	private String funcionarioCpf;
	private Double funcionarioSalario;
	private LocalDate funcionarioDataContratacao;
	private Long funcionarioCargo;

	public String getFuncionarioNome() {
		return funcionarioNome;
	}

	public void setFuncionarioNome(String funcionarioNome) {
		this.funcionarioNome = funcionarioNome;
	}

	public String getFuncionarioCpf() {
		return funcionarioCpf;
	}

	public void setFuncionarioCpf(String funcionarioCpf) {
		this.funcionarioCpf = funcionarioCpf;
	}

	public Double getFuncionarioSalario() {
		return funcionarioSalario;
	}

	public void setFuncionarioSalario(Double funcionarioSalario) {
		this.funcionarioSalario = funcionarioSalario;
	}

	public LocalDate getFuncionarioDataContratacao() {
		return funcionarioDataContratacao;
	}

	public void setFuncionarioDataContratacao(LocalDate funcionarioDataContratacao) {
		this.funcionarioDataContratacao = funcionarioDataContratacao;
	}

	public Long getFuncionarioCargo() {
		return funcionarioCargo;
	}

	public void setFuncionarioCargo(Long funcionarioCargo) {
		this.funcionarioCargo = funcionarioCargo;
	}

	public Funcionario toFuncionario(CargoRepository cargoRepository) {
		Funcionario funcionario = new Funcionario();
		funcionario.setDataAdmissao(this.funcionarioDataContratacao);
		funcionario.setNome(this.funcionarioNome);
		funcionario.setCargo(cargoRepository.findById(this.funcionarioCargo).get());
		funcionario.setSalario(this.funcionarioSalario);
		return funcionario;
	}

}
