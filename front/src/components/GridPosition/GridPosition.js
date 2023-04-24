import React from "react";

import "./GridPosition.css";


const GridPosition = ({ gameId, coordinate, onItemClick, alreadyClicked, totalNeighbourBombs, hasBomb }) => {
  const handleClick = () => {
    onItemClick(gameId, coordinate);
  };

  return (
    <div className="grid-position" onClick={handleClick}>
      { alreadyClicked ? totalNeighbourBombs : null }
      { hasBomb ? "bomb" : null }
      
    </div>
  );
};

export default GridPosition;