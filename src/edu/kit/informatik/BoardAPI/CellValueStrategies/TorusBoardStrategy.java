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
public class TorusBoardStrategy<X extends Cell2D> implements Cell2DValueStrategy<X> {

    /**
     * Torus board; There is no 'outside' the board here
     */
    public TorusBoardStrategy() {
    }

    @Override
    public X getCellValue(int x, int y, X[][] board) throws CellDoesNotExistsException {
        int boardX = calucalteX(x, board[0].length);
        int boardY = calculateY(y, board.length);
        return board[boardY][boardX];
    }

    @Override
    public void setCellValue(int x, int y, X value, X[][] board) throws CellDoesNotExistsException {
        int boardX = calucalteX(x, board[0].length);
        int boardY = calculateY(y, board.length);
        board[boardY][boardX] = value;
    }

    private int calculateY(int y, int length) {
        int boardY = y;
        if (y >= length) {
            boardY = y % length;
        } else if (y < 0) {
            boardY = 5 - (Math.abs(y + 1) % length);
        }
        return boardY;
    }

    private int calucalteX(int x, int length) {
        int boardX = x;
        if (x >= length) {
            boardX = x % length;
        } else if (x < 0) {
            boardX = 5 - (Math.abs(x + 1) % length);
        }
        return boardX;
    }

}
