/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.kit.informatik.GameComponents;

import edu.kit.informatik.BoardAPI.BoardScanner;
import edu.kit.informatik.Exceptions.CellDoesNotExistsException;
import edu.kit.informatik.Exceptions.ColumnDoesNotExist;
import edu.kit.informatik.Exceptions.GameException;
import edu.kit.informatik.Exceptions.RowDoesNotExist;
import edu.kit.informatik.GameComponents.TokenBoard.Player;
import edu.kit.informatik.GameComponents.TokenBoard.Token;
import edu.kit.informatik.GameComponents.TokenBoard.TokenBoard;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

/**
 *
 * @author Stanislav
 * @version 0.0.42
 */
public class Game {

    private final List<Token> bag;
    private final List<Player> players;
    private int currentTick = 1;
    private final TokenBoard board;
    private final BoardScanner<Boolean, TokenBoard> scanner;
    private Token selectedToken;
    private boolean running = true;
    private BiConsumer<Integer, Integer> winAction;

    /**
     * Creates a game of advanced connect the fours
     *
     * @param players The players that will be playing
     * @param board The board on which the game will be played
     * @param lineLength The length of the sequence that one player must connect
     */
    public Game(List<Player> players, TokenBoard board, int lineLength) {
        this.bag = new ArrayList<>();
        this.players = players;
        this.board = board;
        this.scanner = new TokenScanner(lineLength);
        initBag();
    }

    /**
     * Selects a token from the current pile
     *
     * @param id The id of the token to be selected
     * @throws GameException If some kind of error occurs during the selection
     */
    public void select(int id) throws GameException {
        if (!running) {
            throw new GameException("The game is finished. This command is no more allowed");
        }
        if (selectedToken != null) {
            throw new GameException("Token is already selected. This command is not allowed rigth now");
        }
        
        selectedToken = selectToken(id);

    }

    private Token selectToken(int id) throws GameException {
        Token tok = null;
        for (int i = 0; i < bag.size(); i++) {
            if (bag.get(i).getId() == id) {
                tok = bag.get(i);
                break;
            }
        }
        if (tok == null) {
            throw new GameException("There is no token with this number in the bag");
        }
        return tok;
    }

    /**
     * Places the selected token to a given coordinates on the board
     *
     * @param row The row where the token should be placed
     * @param col The column where the token should be placed
     * @throws GameException If some kind of error occurs during the selection
     * @throws CellDoesNotExistsException If the cell with those coordinates
     * does not exist
     */
    public void place(int row, int col) throws GameException, CellDoesNotExistsException {
        try {
            if (!running) {
                throw new GameException("The game is finished. This command is no more allowed");
            }
            if (selectedToken == null) {
                throw new GameException("Token is not selected. This command is not allowed rigth now");
            }
            if (board.getCellAt(row, col).isTaken()) {
                selectedToken = null;
                throw new GameException("This cell is already taken");
            }
            board.getCellAt(row, col).setToken(selectedToken);
            bag.remove(selectedToken);
            selectedToken = null;
            checkWinConditions();
            nextTick();
        } catch (CellDoesNotExistsException ex) {
            selectedToken = null;
            throw ex;
        }
    }

    /**
     * If there is a currently selected token, it deselects it and puts it back
     * in the bag
     */
    public void unselectToken() {
        if (selectedToken != null) {
            selectedToken = null;
        }
    }

    /**
     *
     * @return A string with the remaining tokens ids;
     */
    public String getBagContent() {
        String line = "";
        for (int i = 0; i < bag.size(); i++) {
            if (bag.get(i).equals(selectedToken)) {
                continue;
            }
            line += String.valueOf(bag.get(i).getId());
            line += i == bag.size() - 1 ? "" : " ";
        }
        return line;
    }

    /**
     *
     * @param row The row to be returned
     * @return A string representing the chosen row
     * @throws RowDoesNotExist If this row does not exist on the board
     */
    public String getBoardRow(int row) throws RowDoesNotExist {
        return board.getRow(row);
    }

    /**
     *
     * @param col The column to be column
     * @return A string representing the chosen row
     * @throws ColumnDoesNotExist If this row does not exist on the board
     */
    public String getBoardColumn(int col) throws ColumnDoesNotExist {
        return board.getColumn(col);
    }

    /**
     * Just for debugging purposes
     *
     * @return String representation of the board
     */
    public String getBoard() {
        return board.toString();
    }

    /**
     *
     * @return true if the game is still not won, false otherwise
     */
    public boolean isRunning() {
        return running;
    }

    private Player getCurrentPlayer() {
        return players.get(currentTick % players.size());
    }

    private void initBag() {
        bag.add(new Token(Token.Size.small, Token.Color.black, Token.Shape.rectangle, Token.Form.hollow, 0));
        bag.add(new Token(Token.Size.small, Token.Color.black, Token.Shape.rectangle, Token.Form.dense, 1));
        bag.add(new Token(Token.Size.big, Token.Color.black, Token.Shape.rectangle, Token.Form.hollow, 2));
        bag.add(new Token(Token.Size.big, Token.Color.black, Token.Shape.rectangle, Token.Form.dense, 3));
        bag.add(new Token(Token.Size.small, Token.Color.black, Token.Shape.cylinder, Token.Form.hollow, 4));
        bag.add(new Token(Token.Size.small, Token.Color.black, Token.Shape.cylinder, Token.Form.dense, 5));
        bag.add(new Token(Token.Size.big, Token.Color.black, Token.Shape.cylinder, Token.Form.hollow, 6));
        bag.add(new Token(Token.Size.big, Token.Color.black, Token.Shape.cylinder, Token.Form.dense, 7));
        bag.add(new Token(Token.Size.small, Token.Color.white, Token.Shape.rectangle, Token.Form.hollow, 8));
        bag.add(new Token(Token.Size.small, Token.Color.white, Token.Shape.rectangle, Token.Form.dense, 9));
        bag.add(new Token(Token.Size.big, Token.Color.white, Token.Shape.rectangle, Token.Form.hollow, 10));
        bag.add(new Token(Token.Size.big, Token.Color.white, Token.Shape.rectangle, Token.Form.dense, 11));
        bag.add(new Token(Token.Size.small, Token.Color.white, Token.Shape.cylinder, Token.Form.hollow, 12));
        bag.add(new Token(Token.Size.small, Token.Color.white, Token.Shape.cylinder, Token.Form.dense, 13));
        bag.add(new Token(Token.Size.big, Token.Color.white, Token.Shape.cylinder, Token.Form.hollow, 14));
        bag.add(new Token(Token.Size.big, Token.Color.white, Token.Shape.cylinder, Token.Form.dense, 15));

    }

    private void checkWinConditions() {
        if (bag.isEmpty()) {
            running = false;
            if (winAction != null) {
                winAction.accept(0, currentTick - 1);
            }
        }
        if (scanner.scan(board)) {
            running = false;
            if (winAction != null) {
                winAction.accept(this.getCurrentPlayer().getNum(), currentTick - 1);
            }
        }
    }

    private void nextTick() {
        currentTick++;
    }

    /**
     *
     * @param winAction Action to be performed when the game is over
     */
    public void setWinAction(BiConsumer<Integer, Integer> winAction) {
        this.winAction = winAction;
    }

}
