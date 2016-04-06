/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.kit.informatik.GameComponents;

import edu.kit.informatik.BoardAPI.BoardScanner;
import edu.kit.informatik.Exceptions.CellDoesNotExistsException;
import edu.kit.informatik.GameComponents.TokenBoard.TokenBoard;
import edu.kit.informatik.GameComponents.TokenBoard.TokenCell;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Stanislav
 * @version 0.0.42
 */
public class TokenScanner implements BoardScanner<Boolean, TokenBoard> {

    private int lineLength;

    /**
     * Checks of the board contains a sequence of neighboring cells with same
     * qualities and belonging to the same player
     *
     * @param lineLength The length of the sequence to be searched for
     */
    public TokenScanner(int lineLength) {
        if (lineLength < 0) {
            throw new IllegalArgumentException("The length of the line must be positive");
        }
        this.lineLength = lineLength;
    }

    @Override
    public Boolean scan(TokenBoard board) {

        try {
            for (int x = 0; x < board.getCols(); x++) {
                for (int y = 0; y < board.getRows(); y++) {
                    if (checkQualityMap(getTakenCellsQualities(board, x, y, 1, 0, lineLength))) {
                        return Boolean.TRUE;
                    }
                    if (checkQualityMap(getTakenCellsQualities(board, x, y, 0, 1, lineLength))) {
                        return Boolean.TRUE;
                    }
                    if (checkQualityMap(getTakenCellsQualities(board, x, y, 1, 1, lineLength))) {
                        return Boolean.TRUE;
                    }
                    if (checkQualityMap(getTakenCellsQualities(board, x, y, 1, -1, lineLength))) {
                        return Boolean.TRUE;
                    }

                }
            }
        } catch (CellDoesNotExistsException ex) {
            Logger.getLogger(TokenScanner.class.getName()).log(Level.SEVERE, null, ex);
        }

        return Boolean.FALSE;
    }

    private Map<String, Integer> getTakenCellsQualities(TokenBoard board,
            int xStart,
            int yStart,
            int xDir,
            int yDir, int maxSteps) throws CellDoesNotExistsException {
        if (!board.getCellAt(xStart, yStart).isTaken()) {
            return null;
        }

        Map<String, Integer> qualitiesMap = new HashMap<>();
        board.getCellAt(xStart, yStart).getToken().getAllQualities().forEach((String s) -> {
            qualitiesMap.put(s, 1);
        });

        int xCurrent = xStart + xDir;
        int yCurrent = yStart + yDir;
        for (int i = 0; i < maxSteps - 1; i++) {
            TokenCell nextCell;
            try {
                nextCell = board.getCellAt(xCurrent, yCurrent);
            } catch (CellDoesNotExistsException e) {
                return null;
            }
            if (!nextCell.isTaken()) {
                return null;
            }

            nextCell.getToken().getAllQualities().forEach((String quality) -> {
                if (qualitiesMap.containsKey(quality)) {
                    qualitiesMap.put(quality, qualitiesMap.get(quality) + 1);
                }
            });

            xCurrent += xDir;
            yCurrent += yDir;
        }
        return qualitiesMap;
    }

    private boolean checkQualityMap(Map<String, Integer> qualityMap) {
        if (qualityMap == null) {
            return false;
        } else {
            return qualityMap.values().stream().anyMatch((Integer count) -> count == lineLength);
        }
    }

}
