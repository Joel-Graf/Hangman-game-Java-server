package br.org.catolicasc.hangman_java.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class GameProgress {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @OneToOne
  private User user;

  @ManyToOne
  private Word word;

  @Column(nullable = false)
  private int dificulty;
  
  @Column(nullable = false)
  private int playerLife;
}
