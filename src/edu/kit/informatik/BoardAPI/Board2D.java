/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.kit.informatik.BoardAPI;

import edu.kit.informatik.Exceptions.CellDoesNotExistsException;
import edu.kit.informatik.Exceptions.ColumnDoesNotExist;
import edu.kit.informatik.Exceptions.RowDoesNotExist;

/**
 *
 * @author Stanislav
 * @version 0.0.42
 * @param <X> The type of the cells that will be on the board
 */
public abstract class Board2D<X extends Cell2D> {

    /**
     * 2d array that represents the board
     */
    private X[][] board;
    private final Cell2DValueStrategy<X> cellValueStrategy;
    private final int rows;
    private final int cols;

    /**
     * Abstract representation of 2 dimensional board
     *
     * @param rows The number of rows
     * @param cols The number of columns
     * @param cellValueStrategy The way cells will be 'palced' on the board
     */
    public Board2D(int rows, int cols, Cell2DValueStrategy<X> cellValueStrategy) {
        this.rows = rows;
        this.cols = cols;
        this.initBoard();
        this.cellValueStrategy = cellValueStrategy;
    }

    /**
     * This method will be called when the board is being constructed
     */
    public abstract void initBoard();

    /**
     * Gets the cell with the given coordinates
     *
     * @param x The x coordinate of the cell
     * @param y The y coordinate of the cell
     * @return The cell with the given coordinates
     * @throws CellDoesNotExistsException if there is no cell with the given
     * coordinates
     */
    public X getCellAt(int x, int y) throws CellDoesNotExistsException {
        return cellValueStrategy.getCellValue(x, y, board);
    }

    /**
     * Sets a value for a cell with given coordinates
     *
     * @param x The x coordinate of the cell
     * @param y The y coordinate of the cell
     * @param value The value to be set
     * @throws CellDoesNotExistsException if there is no cell with the given
     * coordinates
     */
    public void setCellAt(int x, int y, X value) throws CellDoesNotExistsException {
        cellValueStrategy.setCellValue(x, y, value, board);
    }

    /**
     *
     * @return The number of rows of the board
     */
    public int getRows() {
        return rows;
    }

    /**
     *
     * @return The number of columns of the board
     */
    public int getCols() {
        return cols;
    }

    /**
     * COnverts the cell values of a single row to strings and separates them
     * with space
     *
     * @param row The row to be converted to string
     * @return The string representation of the given row index
     * @throws RowDoesNotExist If there is no row with the given index
     */
    public String getRow(int row) throws RowDoesNotExist {
        if (row < 0 || row > board.length) {
            throw new RowDoesNotExist("There is no row with this index");

        }
        String buffer = "";
        for (int i = 0; i < board[0].length; i++) {
            buffer += this.board[row][i];
            buffer += i == board.length - 1 ? "" : " ";
        }
        return buffer;
    }

    /**
     * Converts the cell values of a single column to strings and separates them
     * with space
     *
     * @param col The col to be converted to string
     * @return The string representation of the given row index
     * @throws ColumnDoesNotExist If there is no column with the given index
     */
    public String getColumn(int col) throws ColumnDoesNotExist {
        if (col < 0 || col > board[0].length) {
            throw new ColumnDoesNotExist("There is no column with this index");
        }
        String buffer = "";
        for (int i = 0; i < board.length; i++) {
            buffer += this.board[i][col];
            buffer += i == board.length - 1 ? "" : " ";
        }
        return buffer;
    }

    @Override
    public String toString() {
        String buffer = "";
        for (int i = 0; i < board.length; i++) {
            String line = "";
            for (int j = 0; j < board[0].length; j++) {
                line += this.board[i][j].toString();
                line += j == board[0].length - 1 ? "" : " ";
            }
            line += i == board.length - 1 ? "" : "\n";
            buffer += line;

        }
        return buffer;
    }

    /**
     * Basic getter for the board
     *
     * @return The 2d array that represents the board
     */
    public X[][] getBoard() {
        return board;
    }

    /**
     * Basic setter that only subclasses can access
     *
     * @param board The 2d array that represents the board
     */
    protected void setBoard(X[][] board) {
        this.board = board;
    }

}
