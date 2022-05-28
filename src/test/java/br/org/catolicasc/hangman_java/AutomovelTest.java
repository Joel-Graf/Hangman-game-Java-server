package br.org.catolicasc.hangman_java;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import br.org.catolicasc.hangman_java.bean.AutomovelResumido;
import br.org.catolicasc.hangman_java.repository.AutomovelRepository;

@SpringBootTest
public class AutomovelTest {

	@Autowired
	private AutomovelRepository automovelRepository;
	
	@Test
	public void projecaoTest() {
		List<AutomovelResumido> autos = automovelRepository.findAutomovelResumido();
		Assert.notEmpty(autos, "Erroo!!!!");
	}

	
	
	
}
