function Hangman() {
  const CURRENT_WORD = "MACARRONADA";
  const CURRENT_WORD_ARRAY = wordToArray(CURRENT_WORD);

  const [anonWordArray, setAnonWordArray] = useState(
    wordToAnonArray(CURRENT_WORD)
  );
  const [inputLetter, setInputLetter] = useState("");
  const [inputLettersHistory, setInputLettersHistory] = useState([]);
  const [remaningAttempts, setRemaningAttempts] = useState(5);

  useEffect(() => {
    const isGameWon = anonWordArray.every((letter) => letter != " _ ");
    if (isGameWon) {
      alert("Parabéns!!");
      lostGame(); // FIXME:
    }
  }, [anonWordArray]);

  useEffect(() => {
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
  }

  // FIXME: Remover
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

  return (
    <div className="game">
      <div className="header">
        <h2>Tentativas Restantes: {remaningAttempts}</h2>
        <h3>
          Palavras inseridas:{" "}
          {inputLettersHistory.map((letter, index) => (
            <span key={`${letter}:${index}`}>{letter.toUpperCase()}</span>
          ))}
        </h3>
      </div>

      <div className="display">
        <h1>
          {anonWordArray.map((letter, index) => (
            <span style={{ marginLeft: 6 }} key={letter + `${index}`}>
              {" "}
              {`${letter}`}{" "}
            </span>
          ))}
        </h1>
      </div>

      <form
        className="form"
        onSubmit={(e) => {
          e.preventDefault();
          handleInput(inputLetter);
        }}
      >
        <input
          className="form-input"
          maxLength={1}
          value={inputLetter}
          onChange={(e) => setInputLetter(e.target.value)}
        />
      </form>
    </div>
  );
}

const domContainer = document.querySelector("#app");
ReactDOM.render(<Hangman />, domContainer);
