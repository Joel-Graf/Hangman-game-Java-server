package br.org.catolicasc.catrh;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import br.org.catolicasc.catrh.bean.Cargo;
import br.org.catolicasc.catrh.bean.Funcionario;
import br.org.catolicasc.catrh.repository.CargoRepository;
import br.org.catolicasc.catrh.repository.FuncionarioRepository;

@SpringBootTest
public class FuncionarioTest {

	@Autowired
	private CargoRepository cargoRepository;
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@Test
	public void funcionarioAddTest() {
		List<Cargo> cargos = cargoRepository.findByDescricao("Gerente de Projeto");
		Funcionario f1 = new Funcionario();
		f1.setNome("João da Silva");
		f1.setCargo(cargos.get(0));
		f1.setSalario(25000D);
		
		Funcionario f2 = new Funcionario();
		f2.setNome("Maria Filisbina");
		f2.setCargo(cargos.get(0));
		f2.setSalario(30000D);
		this.funcionarioRepository.save(f1);
		this.funcionarioRepository.save(f2);
		Assert.isTrue(f2.getId() != null, "Valor vazio");
	}
	
}
