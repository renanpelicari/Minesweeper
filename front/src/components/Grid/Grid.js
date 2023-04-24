import React from "react";
import GridPosition from "../GridPosition/GridPosition";
import "./Grid.css";

function Grid({ gameData, onItemClick }) {
  const { boardPositionMap, id, status } = gameData;
  const gameFinished = status === 'PLAYER_WON' || status === 'PLAYER_LOST';

  const gridClassName = gameFinished ? "grid-container readonly" : "grid-container";

  // Obter o maior valor de x e y do backend
  const maxX = Math.max(...Object.keys(boardPositionMap).map(positionKey => boardPositionMap[positionKey].coordinate.x)) + 1;
  const maxY = Math.max(...Object.keys(boardPositionMap).map(positionKey => boardPositionMap[positionKey].coordinate.y)) + 1;

  return (
    <div className={gridClassName} style={{ gridTemplateColumns: `repeat(${maxY}, 0fr)`, gridTemplateRows: `repeat(${maxX}, 0fr)` }}>
      {Object.keys(boardPositionMap).map((positionKey) => {
        const { coordinate, hasBomb, hasFlag, alreadyClicked, totalNeighbourBombs, neighbourBombs } = boardPositionMap[positionKey];
        const { x, y } = coordinate;
        const key = `${id}-${x}-${y}`;

        return (
          <GridPosition
            key={key}
            coordinate={coordinate}
            gameFinished={gameFinished}
            gameId={id}
            hasBomb={hasBomb}
            hasFlag={hasFlag}
            alreadyClicked={alreadyClicked}
            totalNeighbourBombs={totalNeighbourBombs}
            neighbourBombs={neighbourBombs}
            onItemClick={gameFinished ? null : onItemClick}
            readOnly={gameFinished}
          />
        );
      })}
    </div>
  );
}
export default Grid;