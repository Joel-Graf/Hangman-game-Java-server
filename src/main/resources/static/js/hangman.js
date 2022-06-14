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
    wordToAnonArray();
    setRemaningAttempts(5);
    setInputLetter("");
  }

  function handleInput(letter) {
    const isLetter = /([A-Z])\b/g.test(letter);
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
    <div className="body">
      <div className="section">
        <h2>Tentativas Restantes: {remaningAttempts}</h2>
        <div className="display-word">
          <h1>
            {anonWordArray.map((letter, index) => (
              <span key={letter + `${index}`}> {letter} </span>
            ))}
          </h1>
        </div>

        <div className="input-all-letter-choiced">
          <h2 className="display-letter-choiced">
            {inputLettersHistory.map((letter, index) => (
              <span key={`${letter}:${index}`}>{letter.toUpperCase()}</span>
            ))}
          </h2>

          <div className="block-input">
            <form
              onSubmit={(e) => {
                e.preventDefault();
                handleInput(inputLetter);
              }}
            >
              <input
                className="input-hangman"
                maxLength={1}
                value={inputLetter}
                onChange={(e) => setInputLetter(e.target.value)}
              />
            </form>
          </div>
        </div>
      </div>
    </div>
  );
}

const domContainer = document.querySelector("#app");
ReactDOM.render(<Hangman />, domContainer);
