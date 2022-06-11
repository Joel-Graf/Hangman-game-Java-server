package br.org.catolicasc.hangman_java.rest_controller;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.org.catolicasc.hangman_java.bean.Word;
import br.org.catolicasc.hangman_java.repository.WordRepository;

@RestController
public class WordRestController {

  @Autowired
  private WordRepository wordRepository;

  @GetMapping("/word")
  public Word getWord(@RequestParam int dificulty) {
    List<Word> words = wordRepository.getWordsByDificulty(dificulty);
    if (words.isEmpty()) {
      return null;
    }

    int randomIndex = new Random().nextInt(words.size());
    Word randomWord = words.get(randomIndex);

    return randomWord;
  }

}
