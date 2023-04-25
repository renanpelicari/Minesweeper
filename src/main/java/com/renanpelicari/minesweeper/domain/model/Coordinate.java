package com.renanpelicari.minesweeper.domain.model;

/**
 * The record of model Coordinate, which contains only necessary to build the object and use, following the
 * immutability principles.
 * @param x the x position of the coordinate
 * @param y the y position of the coordinate
 */
public record Coordinate(int x, int y) {
}