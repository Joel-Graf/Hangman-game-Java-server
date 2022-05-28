package br.org.catolicasc.hangman_java;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.Assert;

import br.org.catolicasc.hangman_java.bean.Cargo;
import br.org.catolicasc.hangman_java.repository.CargoRepository;

@SpringBootTest
public class CargoTest {

	@Autowired
	private CargoRepository cargoRepository;
	
	@Test
	public void derivedQueryTest() {
		List<Cargo> cargos = cargoRepository.findByDescricao("Gerente de Projeto");
		Assert.notEmpty(cargos, "Erroo!!!!");
	}
	
	@Test
	public void queryTest() {
		List<Cargo> cargos = cargoRepository.findCargoIdMaior(3L);
		Assert.notEmpty(cargos, "Erroo!!!!");
	}
	
	@Test
	public void queryNativeTest() {
		List<Cargo> cargos = cargoRepository.findCargoIdMaiorNative(3L);
		Assert.notEmpty(cargos, "Erroo!!!!");
	}
	
	@Test
	public void queryPaginadoTest() {
		Pageable p = PageRequest.of(0, 5, Sort.unsorted());
		Page<Cargo> cargos = cargoRepository.findAll(p);
		Assert.notEmpty(cargos.toList(), "Erroo!!!!");
		Assert.isTrue(!cargos.isEmpty(), "Erroo!!!!");
	}
	
	
	
}
