import React from "react";
import GridPosition from "../GridPosition/GridPosition";
import "./Grid.css";

function Grid({ gameData, onItemClick }) {
  const { boardPositionMap } = gameData;

  return (
    <div className="grid-container">
      {Object.keys(boardPositionMap).map((positionKey) => {
        const { coordinate, hasBomb, hasFlag, alreadyClicked, totalNeighbourBombs, neighbourBombs } = boardPositionMap[positionKey];
        const { x, y } = coordinate;
        const key = `${gameData.id}-${x}-${y}`;

        return (
          <GridPosition
            key={key}
            coordinate={coordinate}
            gameId={gameData.id}
            hasBomb={hasBomb}
            hasFlag={hasFlag}
            alreadyClicked={alreadyClicked}
            totalNeighbourBombs={totalNeighbourBombs}
            neighbourBombs={neighbourBombs}
            onItemClick={onItemClick}
          />
        );
      })}
    </div>
  );
}


export default Grid;