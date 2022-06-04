package br.org.catolicasc.hangman_java.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Game {
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

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Word getWord() {
    return word;
  }

  public void setWord(Word word) {
    this.word = word;
  }

  public int getDificulty() {
    return dificulty;
  }

  public void setDificulty(int dificulty) {
    this.dificulty = dificulty;
  }

  public int getPlayerLife() {
    return playerLife;
  }

  public void setPlayerLife(int playerLife) {
    this.playerLife = playerLife;
  }

}
