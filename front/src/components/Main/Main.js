import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './Main.css';
import Grid from '../Grid/Grid';

import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faTrophy, faBomb } from '@fortawesome/free-solid-svg-icons';

function Main() {
  const [gameData, setGameData] = useState(null);

  const handleNewGame = async () => {
    try {
      const response = await axios.get('http://localhost:8088/minesweeper/new');
      setGameData(response.data);
    } catch (error) {
      console.error('Error on start new game:', error);
      window.alert("Error on start new game: " + error.message);
    }
  };

  const handleRestartGame = async (gameId) => {
    try {
      const response = await axios.get(`http://localhost:8088/minesweeper/restart/${gameId}`);
      setGameData(response.data);
    } catch (error) {
      console.error('Error on restart game:', error);
      window.alert("Error on restart game: " + error.message);
    }
  };

  const renderStatus = () => { 
    if (gameData && gameData.status === 'PLAYER_WON') {
      return <FontAwesomeIcon icon={faTrophy} style={{ fontSize: '48px', display: 'block', margin: '5 auto' }} />;
    }

    if (gameData && gameData.status === 'PLAYER_LOST') {
      return <FontAwesomeIcon icon={faBomb} style={{ fontSize: '48px', display: 'block', margin: '5 auto' }} />;
    }
    return null;
  }

  const renderGameInfo = () => {
    if (gameData) {
      return (
        <div>
          <p className="gameId">Game ID: {gameData.id}</p>
          <p className="status">Status: {gameData.status}</p>
          <p className="totalBombs">Total bombs: {gameData.totalBombs}</p>
          <p className="uncoveredCoordinates">Uncovered Coordinates: {gameData.uncoveredCoordinates}</p>
        </div>
      );
    }

    return null;
  }

  const handleItemClick = async (gameId, coordinate) => {
    try {
      const response = await axios.put(`http://localhost:8088/minesweeper/click/${gameId}?positionX=${coordinate.x}&positionY=${coordinate.y}`);
      setGameData(response.data);
    } catch (error) {
      console.error('Error on make a move:', error);
      window.alert("Invalid Movement: " + error.message);
    }
  };

  const handleChangeFlag = async (gameId, coordinate) => {
    try {
      const response = await axios.put(`http://localhost:8088/minesweeper/flag/${gameId}?positionX=${coordinate.x}&positionY=${coordinate.y}`);
      setGameData(response.data);
    } catch (error) {
      console.error('Error on flag/unflag a position:', error);
      window.alert("Error on flag/unflag a position: " + error.message);
    }
  }

  return (
    <div className="Main">

      <header className="header">
        <h1 className="title">Minesweeper App</h1>
        {renderGameInfo()}
      </header>

      {gameData && <button className="button" onClick={() => handleRestartGame(gameData.id)}>Restart Game</button>}
      <button className="button" onClick={handleNewGame}>New Game</button>

      {renderStatus()}

      {gameData && 
        <Grid gameData={gameData} 
          onItemClick={handleItemClick} 
          onContextMenu={handleChangeFlag}
        />}
        
        {gameData && <p className="flag-info">Add/Remove flags with secondary mouse clicking.</p>}
      </div>
  );
}

export default Main;