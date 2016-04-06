/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.kit.informatik.BoardAPI;

/**
 *
 * @author Stanislav
 * @version 0.0.42
 */
public class Cell2D {

    private int x;
    private int y;

    /**
     * Very basic representation of a cell
     *
     * @param x The x coordinate of the cell
     * @param y The y coordinate of the cell
     */
    public Cell2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Basic getter
     *
     * @return The x coordinate of the cell
     */
    public int getX() {
        return x;
    }

    /**
     * Basic setter
     *
     * @param x The x coordinate of the cell
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Basic getter
     *
     * @return The y coordinate of the cell
     */
    public int getY() {
        return y;
    }

    /**
     * Basic setter
     *
     * @param y The y coordinate of the cell
     */
    public void setY(int y) {
        this.y = y;
    }

}
