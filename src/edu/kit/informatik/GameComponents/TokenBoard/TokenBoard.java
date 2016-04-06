/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.kit.informatik.GameComponents.TokenBoard;

import edu.kit.informatik.BoardAPI.Board2D;
import edu.kit.informatik.BoardAPI.Cell2DValueStrategy;

/**
 *
 * @author Stanislav
 * @version 0.0.42
 */
public class TokenBoard extends Board2D<TokenCell> {

    /**
     * Basic board where each cell can contain a single token and belongs to a
     * player
     *
     * @param rows The number of rows of the board
     * @param cols The number of columns of the board
     * @param cellValueStrategy The way cells will be 'palced' on the board
     */
    public TokenBoard(int rows, int cols, Cell2DValueStrategy<TokenCell> cellValueStrategy) {
        super(rows, cols, cellValueStrategy);
    }

    @Override
    public void initBoard() {
        setBoard(new TokenCell[getRows()][getCols()]);
        for (int i = 0; i < getBoard().length; i++) {
            for (int j = 0; j < getBoard()[0].length; j++) {
                getBoard()[i][j] = new TokenCell(j, i);
            }
        }
    }

}
