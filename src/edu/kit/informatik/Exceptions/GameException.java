/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.kit.informatik.Exceptions;

/**
 *
 * @author Stanislav
 * @version 0.0.42
 */
public class GameException extends Exception {

    /**
     * Basic constructor
     *
     * @param msg The message of this exception
     */
    public GameException(String msg) {
        super(msg);
    }
}
