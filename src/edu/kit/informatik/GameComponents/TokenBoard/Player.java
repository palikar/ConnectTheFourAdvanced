/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.kit.informatik.GameComponents.TokenBoard;

/**
 *
 * @author Stanislav
 * @version 0.0.42
 */
public class Player {

    private int num;

    /**
     * Representation of a player of the game
     *
     * @param num The identification number of the player
     */
    public Player(int num) {
        this.num = num;
    }

    /**
     * Basic getter
     *
     * @return The number of the player
     */
    public int getNum() {
        return num;
    }

    /**
     * Basic getter
     *
     * @param num The number of the player
     */
    public void setNum(int num) {
        this.num = num;
    }

}
