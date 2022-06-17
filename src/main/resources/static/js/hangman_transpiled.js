"use strict";

function Hangman() {
  const [game, setGame] = React.useState({});
  const [word, setWord] = React.useState("");
  const [wordArray, setWordArray] = React.useState([]);
  const [anonWordArray, setAnonWordArray] = React.useState([]);
  const [inputLetter, setInputLetter] = React.useState("");
  const [inputLettersHistory, setInputLettersHistory] = React.useState([]);
  const [lifes, setLifes] = React.useState(99);
  const [remaningAttempts, setRemaningAttempts] = React.useState(5);
  React.useEffect(() => {
    getGameInProgress();
  }, []);
  React.useEffect(() => {
    if (!anonWordArray.length) return;
    const isGameWon = anonWordArray.every((letter) => letter != " _ ");

    if (isGameWon) {
      setTimeout(() => {
        alert("Parabéns!!");
        correctWord();
      }, 500);
    }
  }, [anonWordArray]);
  React.useEffect(() => {
    if (remaningAttempts <= 0) {
      alert("Você Perdeu!!"); // FIXME:

      return;
    }
  }, [remaningAttempts]);

  function getGameInProgress() {
    return fetch("game/game_in_progress")
      .then((res) => res.json())
      .then((game) => {
        if (game != {}) {
          updateGame(game);
        }
      })
      .catch((err) => {
        alert("Erro: ", err);
        getGameInProgress();
      });
  }

  function correctWord() {
    return fetch("game/correct_word")
      .then((res) => {
        return new Promise((resolve, reject) => {
          res
            .json()
            .then((res) => resolve(res))
            .catch(() => {
              resolve({});
            });
        });
      })
      .then((game) => {
        if (game.id) {
          updateGame(game);
        } else {
          alert("Você Ganhou!!!!");
          getGameInProgress();
        }
      })
      .catch((err) => {
        alert("Erro: ", err);
        correctWord();
      });
  }

  function updateGame(game) {
    const _word = game.word.word;
    const _life = game.playerLife;
    setWord(_word);
    setWordArray(wordToArray(_word));
    setAnonWordArray(wordToAnonArray(_word));
    setInputLetter("");
    setInputLettersHistory([]);
    setLifes(_life);
    setRemaningAttempts(5);
    setGame(game);
  }

  function wordToArray(word) {
    return word.split("");
  }

  function wordToAnonArray(word) {
    const anonWordArray = word.split("");
    return anonWordArray.map(() => " _ ");
  }

  function handleInput(letter) {
    const isLetter = /([a-zA-Z])\b/g.test(letter);

    if (!isLetter) {
      return alert("Input Inválido! Somente letras.");
    }

    letter = letter.toUpperCase();

    if (inputLettersHistory.includes(letter)) {
      return alert("Input Inválido! Letra já inserida.");
    }

    const _anonWordArray = anonWordArray;
    let foundLetter = false;
    wordArray.forEach((wordLetter, index) => {
      if (wordLetter == letter) {
        _anonWordArray[index] = letter;
        foundLetter = true;
      }
    });

    if (!foundLetter) {
      const _remaningAttempts = remaningAttempts - 1;

      setRemaningAttempts(_remaningAttempts);
    }

    setInputLetter("");
    setInputLettersHistory([...inputLettersHistory, letter]);
    setAnonWordArray([..._anonWordArray]);
  }

  return /*#__PURE__*/ React.createElement(
    "div",
    {
      className: "game",
    },
    /*#__PURE__*/ React.createElement(
      "div",
      {
        className: "header",
      },
      /*#__PURE__*/ React.createElement(
        "h2",
        null,
        "Tentativas Restantes: ",
        remaningAttempts
      ),
      /*#__PURE__*/ React.createElement(
        "h3",
        null,
        "Palavras inseridas:",
        " ",
        inputLettersHistory.map((letter, index) =>
          /*#__PURE__*/ React.createElement(
            "span",
            {
              key: `${letter}:${index}`,
            },
            letter.toUpperCase()
          )
        )
      )
    ),
    /*#__PURE__*/ React.createElement(
      "div",
      {
        className: "display",
      },
      /*#__PURE__*/ React.createElement(
        "h1",
        null,
        anonWordArray.map((letter, index) =>
          /*#__PURE__*/ React.createElement(
            "span",
            {
              style: {
                marginLeft: 6,
              },
              key: letter + `${index}`,
            },
            " ",
            `${letter}`,
            " "
          )
        )
      )
    ),
    /*#__PURE__*/ React.createElement(
      "form",
      {
        className: "form",
        onSubmit: (e) => {
          e.preventDefault();
          handleInput(inputLetter);
        },
      },
      /*#__PURE__*/ React.createElement("input", {
        className: "form-input",
        maxLength: 1,
        value: inputLetter,
        onChange: (e) => setInputLetter(e.target.value),
      })
    )
  );
}

const domContainer = document.querySelector("#app");
ReactDOM.render(/*#__PURE__*/ React.createElement(Hangman, null), domContainer);
