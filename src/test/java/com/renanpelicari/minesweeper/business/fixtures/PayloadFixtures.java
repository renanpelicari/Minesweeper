package com.renanpelicari.minesweeper.business.fixtures;

public class PayloadFixtures {

    public static final String STARTED_GAME_RESPONSE = "{\"id\":\"DEFAULT_GAME_ID\",\"status\":\"STARTED\",\"totalBombs\":1," +
            "\"uncoveredCoordinates\":3,\"boardPositionMap\":{\"0\":{\"coordinate\":{\"x\":0,\"y\":0},\"hasBomb\":false," +
            "\"hasFlag\":false,\"alreadyClicked\":true,\"totalNeighbourBombs\":0,\"neighbourCoordinates\":[{\"x\":1,\"y\":1}," +
            "{\"x\":1,\"y\":0},{\"x\":0,\"y\":1}]},\"32\":{\"coordinate\":{\"x\":1,\"y\":1},\"hasBomb\":true," +
            "\"hasFlag\":false,\"alreadyClicked\":false,\"totalNeighbourBombs\":0," +
            "\"neighbourCoordinates\":[{\"x\":2,\"y\":2},{\"x\":2,\"y\":1},{\"x\":1,\"y\":2},{\"x\":0,\"y\":2}," +
            "{\"x\":0,\"y\":1},{\"x\":0,\"y\":0},{\"x\":1,\"y\":0},{\"x\":2,\"y\":0}]},\"64\":" +
            "{\"coordinate\":{\"x\":2,\"y\":2},\"hasBomb\":true,\"hasFlag\":true,\"alreadyClicked\":false,\"" +
            "totalNeighbourBombs\":0,\"neighbourCoordinates\":[{\"x\":2,\"y\":1},{\"x\":1,\"y\":2},{\"x\":1,\"y\":1}]}," +
            "\"1\":{\"coordinate\":{\"x\":0,\"y\":1},\"hasBomb\":false,\"hasFlag\":false,\"alreadyClicked\":false," +
            "\"totalNeighbourBombs\":0,\"neighbourCoordinates\":[{\"x\":1,\"y\":2},{\"x\":1,\"y\":1},{\"x\":0,\"y\":2}," +
            "{\"x\":1,\"y\":0},{\"x\":0,\"y\":0}]},\"33\":{\"coordinate\":{\"x\":1,\"y\":2},\"hasBomb\":false," +
            "\"hasFlag\":false,\"alreadyClicked\":false,\"totalNeighbourBombs\":0,\"neighbourCoordinates\":" +
            "[{\"x\":2,\"y\":2},{\"x\":2,\"y\":1},{\"x\":1,\"y\":1},{\"x\":0,\"y\":2},{\"x\":0,\"y\":1}]},\"2\":" +
            "{\"coordinate\":{\"x\":0,\"y\":2},\"hasBomb\":false,\"hasFlag\":false,\"alreadyClicked\":false," +
            "\"totalNeighbourBombs\":0,\"neighbourCoordinates\":[{\"x\":1,\"y\":2},{\"x\":1,\"y\":1},{\"x\":0,\"y\":1}]}," +
            "\"62\":{\"coordinate\":{\"x\":2,\"y\":0},\"hasBomb\":false,\"hasFlag\":false,\"alreadyClicked\":true," +
            "\"totalNeighbourBombs\":0,\"neighbourCoordinates\":[{\"x\":2,\"y\":1},{\"x\":1,\"y\":1},{\"x\":1,\"y\":0}]}," +
            "\"31\":{\"coordinate\":{\"x\":1,\"y\":0},\"hasBomb\":false,\"hasFlag\":false,\"alreadyClicked\":true," +
            "\"totalNeighbourBombs\":0,\"neighbourCoordinates\":[{\"x\":2,\"y\":1},{\"x\":2,\"y\":0},{\"x\":1,\"y\":1}," +
            "{\"x\":0,\"y\":1},{\"x\":0,\"y\":0}]},\"63\":{\"coordinate\":{\"x\":2,\"y\":1},\"hasBomb\":false," +
            "\"hasFlag\":false,\"alreadyClicked\":false,\"totalNeighbourBombs\":0," +
            "\"neighbourCoordinates\":[{\"x\":2,\"y\":2},{\"x\":2,\"y\":0},{\"x\":1,\"y\":2},{\"x\":1,\"y\":1},{\"x\":1,\"y\":0}]}}}\n";
}
