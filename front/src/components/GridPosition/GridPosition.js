import React from "react";
import "./GridPosition.css";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faBomb, faFlag } from '@fortawesome/free-solid-svg-icons';


const GridPosition = ({ gameId, coordinate, onItemClick, alreadyClicked, totalNeighbourBombs, hasBomb, hasFlag, gameFinished, onContextMenu }) => {
  const handleClick = () => {
    onItemClick(gameId, coordinate);
  };
  const handleContextMenu = (e) => {
    e.preventDefault();
    onContextMenu(gameId, coordinate);
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
    }

    if (alreadyClicked && totalNeighbourBombs > 0) {
      // Show totalNeighbourBombs when gameFinished is false, alreadyClicked is true, and totalNeighbourBombs > 0
      return totalNeighbourBombs;
    }

    if (hasFlag) {
      return <FontAwesomeIcon icon={faFlag} />;
    }

    // Return null if no content to show
    return null;
  };

  const gridClassName = alreadyClicked ? "grid-position already-clicked" : "grid-position";


  return (
    <div className={gridClassName} onClick={handleClick} onContextMenu={handleContextMenu}>
      {renderContent()}      
    </div>
  );
};

export default GridPosition;