/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.kit.informatik;

import edu.kit.informatik.GameComponents.Game;
import edu.kit.informatik.BoardAPI.Cell2DValueStrategy;
import edu.kit.informatik.BoardAPI.CellValueStrategies.OrdinaryBoardStrategy;
import edu.kit.informatik.BoardAPI.CellValueStrategies.TorusBoardStrategy;
import edu.kit.informatik.GameComponents.TokenBoard.Player;
import edu.kit.informatik.GameComponents.TokenBoard.TokenBoard;
import edu.kit.informatik.GameComponents.TokenBoard.TokenCell;
import edu.kit.informatik.Utils.BasicErrorHandler;
import edu.kit.informatik.Utils.ErrorHandler;
import java.util.Arrays;

/**
 *
 * @author Stanislav
 * @version 0.0.1
 */
public final class MainClass {

    private MainClass() {

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ErrorHandler errorHandler = new BasicErrorHandler();

        if (args.length != 1) {
            errorHandler.printErrorMessage("Error, USAGE: [standard|torus] - board type ");
            System.exit(1);
        }
        String type = args[0];

        Cell2DValueStrategy<TokenCell> boardValuesStrategy = null;
        if (type.equals("standard")) {
            boardValuesStrategy = new OrdinaryBoardStrategy<>();
        } else if (type.equals("torus")) {
            boardValuesStrategy = new TorusBoardStrategy<>();
        } else {
            errorHandler.printErrorMessage("wrong type of board");
            System.exit(1);
        }
        TokenBoard board = new TokenBoard(6, 6, boardValuesStrategy);
        Game game = new Game(Arrays.asList(new Player(1), new Player(2)), board, 4);
        CommandLine.beginInputSequence(errorHandler, game);
    }

}
