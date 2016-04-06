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
public class ColumnDoesNotExist extends Exception {

    /**
     * Basic constructor
     *
     * @param msg The message of this exception
     */
    public ColumnDoesNotExist(String msg) {
        super(msg);
    }
}
