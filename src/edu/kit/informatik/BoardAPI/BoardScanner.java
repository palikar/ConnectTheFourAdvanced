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
 * @param <X> Value to be returned after the scan
 * @param <Y> Board that will be scanned
 */
public interface BoardScanner<X, Y extends Board2D<? extends Cell2D>> {

    /**
     * Scans the board and returns some kind of value
     *
     * @param board The board to be scanned
     * @return The result of the scan
     */
    X scan(Y board);

}
