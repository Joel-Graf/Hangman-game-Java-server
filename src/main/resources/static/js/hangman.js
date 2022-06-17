function Hangman() {
  const [game, setGame] = useState({});
  const [word, setWord] = useState("");
  const [wordArray, setWordArray] = useState([]);
  const [anonWordArray, setAnonWordArray] = useState([]);
  const [inputLetter, setInputLetter] = useState("");
  const [inputLettersHistory, setInputLettersHistory] = useState([]);
  const [lifes, setLifes] = useState(99);
  const [remaningAttempts, setRemaningAttempts] = useState(5);

  useEffect(() => {
    getGameInProgress();
  }, []);

  useEffect(() => {
    if (!anonWordArray.length) return;

    const isGameWon = anonWordArray.every((letter) => letter != " _ ");
    if (isGameWon) {
      setTimeout(() => {
        alert("Parabéns!!");
        // FIXME:
      }, 500);
    }
  }, [anonWordArray]);

  useEffect(() => {
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
          const _word = game.word.word;
          const _life = game.playerLife;
          setWord(_word);
          setWordArray(wordToArray(_word));
          setAnonWordArray(wordToAnonArray(_word));
          setLifes(_life);
          setGame(game);
        }
      })
      .catch((err) => {
        alert("Erro: ", err);
        getGameInProgress();
      });
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
