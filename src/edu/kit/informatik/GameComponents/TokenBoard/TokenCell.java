/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.kit.informatik.GameComponents.TokenBoard;

import edu.kit.informatik.BoardAPI.Cell2D;

/**
 *
 * @author Stanislav
 * @version 0.0.42
 */
public class TokenCell extends Cell2D {

    private Token token;

    /**
     * A cell that contains a token and belongs to a player
     *
     * @param x The x coordinate of the cell
     * @param y The y coordinate of the cell
     */
    public TokenCell(int x, int y) {
        super(x, y);
    }

    /**
     * Basic getter for the token at this cell
     *
     * @return The token of the cell if there is one
     */
    public Token getToken() {
        return token;
    }

    /**
     * Basic getter for the token at this cell
     *
     *
     * @param token The token to be placed here
     */
    public void setToken(Token token) {
        this.token = token;
    }

    /**
     *
     * @return True if the cell is taken, false otherwise
     */
    public boolean isTaken() {
        return token != null;
    }

    @Override
    public String toString() {
        if (token != null) {
            return String.valueOf(token.getId());
        } else {
            return "#";
        }
    }

}
