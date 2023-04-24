import React from "react";

import "./GridPosition.css";


const GridPosition = ({ gameId, coordinate, onItemClick }) => {
  const handleClick = () => {
    onItemClick(gameId, coordinate);
  };

  return (
    <div className="grid-position" onClick={handleClick}>
      {/* Conteúdo do componente GridPosition */}
    </div>
  );
};

export default GridPosition;