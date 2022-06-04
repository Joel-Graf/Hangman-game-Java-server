-- db_hangman_java

-- User
INSERT INTO user (name, login, password) VALUES
  ("Joel M. Graf", "jojo", "jojo"),
  ("Gustavo Blasius", "gustavo", "blasius");

-- Word
INSERT INTO word (dificulty, word) VALUES
  (1, "SAPO"),
  (1, "GATO"),
  (1, "PATO"),
  (1, "BALA"),
  (2, "SAPATO"),
  (2, "PRATO"),
  (2, "CHAPEU"),
  (3, "PSICOLOGIA"),
  (3, "MANTEIGA");