/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.kit.informatik.BoardAPI;

import edu.kit.informatik.Exceptions.CellDoesNotExistsException;

/**
 *
 * @author Stanislav
 * @version 0.0.42
 * @param <X> The type of cell that will be set or get
 */
public interface Cell2DValueStrategy<X extends Cell2D> {

    /**
     * Retrieves a single cell from a board
     *
     * @param x The x coordinate of the cell
     * @param y The y coordinate of the cell
     * @param board 2D array that represents the board
     * @return The cell at the coordinates
     * @throws CellDoesNotExistsException If there is no cell with these
     * coordinates on the board
     */
    X getCellValue(int x, int y, X[][] board) throws CellDoesNotExistsException;

    /**
     * Sets a value for a cell at the board
     *
     * @param x The x coordinate of the cell
     * @param y The y coordinate of the cell
     * @param value The value to be set
     * @param board 2D array that represents the board
     * @throws CellDoesNotExistsException If there is no cell with these
     * coordinates on the board
     */
    void setCellValue(int x, int y, X value, X[][] board) throws CellDoesNotExistsException;
}
