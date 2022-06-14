function Hangman() {
  const CURRENT_WORD = "MACARRONADA";
  const CURRENT_WORD_ARRAY = wordToArray(CURRENT_WORD);
  const [anonWordArray, setAnonWordArray] = React.useState(
    wordToAnonArray(CURRENT_WORD)
  );
  const [inputLetter, setInputLetter] = React.useState("");
  const [inputLettersHistory, setInputLettersHistory] = React.useState([]);
  const [remaningAttempts, setRemaningAttempts] = React.useState(5);
  React.useEffect(() => {
    const isGameWon = anonWordArray.every((letter) => letter != " _ ");

    if (isGameWon) {
      alert("Parabéns!!");
      lostGame(); // FIXME:
    }
  }, [anonWordArray]);
  React.useEffect(() => {
    if (remaningAttempts <= 0) {
      alert("Você Perdeu!!"); // FIXME:

      lostGame();
      return;
    }
  }, [remaningAttempts]);

  function wordToArray(word) {
    return word.split("");
  }

  function wordToAnonArray(word) {
    const anonWordArray = word.split("");
    return anonWordArray.map(() => " _ ");
  } // FIXME: Remover

  function lostGame() {
    setAnonWordArray([]);
    setInputLettersHistory([]);
    setAnonWordArray(wordToAnonArray(CURRENT_WORD));
    setRemaningAttempts(5);
    setInputLetter("");
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
    CURRENT_WORD_ARRAY.forEach((wordLetter, index) => {
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
