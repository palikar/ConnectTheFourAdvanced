/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.kit.informatik.BoardAPI.CellValueStrategies;

import edu.kit.informatik.Exceptions.CellDoesNotExistsException;
import edu.kit.informatik.BoardAPI.Cell2DValueStrategy;
import edu.kit.informatik.BoardAPI.Cell2D;

/**
 *
 * @author Stanislav
 * @version 0.0.42
 * @param <X> The type of cell that will be set or get
 */
public class OrdinaryBoardStrategy<X extends Cell2D> implements Cell2DValueStrategy<X> {

    /**
     * Normal board;
     * If the given coordinates are outside of the board, exception will be thrown
     */
    public OrdinaryBoardStrategy() {
    }

    @Override
    public X getCellValue(int x, int y, X[][] board) throws CellDoesNotExistsException {
        if (x < 0 || x >= board[0].length
                || y < 0 || y >= board.length) {
            throw new CellDoesNotExistsException("There is no cell with those coridnates in the board");
        }
        return board[y][x];
    }

    @Override
    public void setCellValue(int x, int y, X value, X[][] board) throws CellDoesNotExistsException {
        if (x < 0 || x > board[0].length
                || y <= 0 || y >= board.length) {
            throw new CellDoesNotExistsException("There is no cell with those coridnates in the board");
        }
        board[y][x] = value;
    }

}
