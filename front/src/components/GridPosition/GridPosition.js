import React from "react";
import "./GridPosition.css";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faBomb } from '@fortawesome/free-solid-svg-icons';


const GridPosition = ({ gameId, coordinate, onItemClick, alreadyClicked, totalNeighbourBombs, hasBomb, gameFinished }) => {
  const handleClick = () => {
    onItemClick(gameId, coordinate);
  };

  const renderContent = () => { 
    if (gameFinished) {
      if (hasBomb) {
        // Show bomb icon when gameFinished is true and hasBomb is true
        return <FontAwesomeIcon icon={faBomb} />;

      } else if (totalNeighbourBombs > 0) {
        // Show totalNeighbourBombs when gameFinished is true and totalNeighbourBombs > 0
        return totalNeighbourBombs;
      }
    } else {
      if (alreadyClicked && totalNeighbourBombs > 0) {
        // Show totalNeighbourBombs when gameFinished is false, alreadyClicked is true, and totalNeighbourBombs > 0
        return totalNeighbourBombs;
      }
    }

    // Return null if no content to show
    return null;
  };

  const gridClassName = alreadyClicked ? "grid-position already-clicked" : "grid-position";


  return (
    <div className={gridClassName} onClick={handleClick}>
      {renderContent()}      
    </div>
  );
};

export default GridPosition;