import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './Main.css';
import Grid from '../Grid/Grid';

function Main() {
  const [gameData, setGameData] = useState(null);

  const handleNewGame = async () => {
    try {
      const response = await axios.get('http://localhost:8080/minesweeper/new');
      setGameData(response.data);
    } catch (error) {
      console.error('Erro ao buscar dados do jogo:', error);
    }
  };

  const handleItemClick = async (gameId, coordinate) => {
    console.log("clicked on grid position")
    console.log(gameId)
    console.log(coordinate)
    try {
      const response = await axios.put(`http://localhost:8080/minesweeper/click/${gameId}?positionX=${coordinate.x}&positionY=${coordinate.y}`);
      setGameData(response.data);
    } catch (error) {
      console.error('Erro ao realizar a jogada:', error);
    }
  };

  return (
    <div className="Main">
      {/* Renderiza o componente Grid e passa o objeto gameData como prop */}
      {gameData && <Grid gameData={gameData} onItemClick={handleItemClick} />}
      
      {/* Renderiza o botão para iniciar um novo jogo */}
      <button onClick={handleNewGame}>Novo Jogo</button>
    </div>
  );
}

export default Main;