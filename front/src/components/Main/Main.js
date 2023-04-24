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
      console.error('Error on start new game:', error);
    }
  };

  const handleRestartGame = async (gameId) => {
    try {
      const response = await axios.get(`http://localhost:8080/minesweeper/restart/${gameId}`);
      setGameData(response.data);
    } catch (error) {
      console.error('Error on restart game:', error);
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
      console.error('Error on make a move:', error);
    }
  };

  return (
    <div className="Main">

      <header className="header">
        <h1 className="title">Minesweeper App</h1>
        <p className="subtitle">Powered by: Renan Rodrigues</p>
        {gameData && <p className="gameId">Game ID: {gameData.id}</p>}
      </header>

      {gameData && <button className="button" onClick={() => handleRestartGame(gameData.id)}>Restart Game</button>}
      <button className="button" onClick={handleNewGame}>New Game</button>


      {gameData && <Grid gameData={gameData} onItemClick={handleItemClick} />}
      </div>
  );
}

export default Main;