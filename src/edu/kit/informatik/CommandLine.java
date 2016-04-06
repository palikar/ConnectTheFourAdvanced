package edu.kit.informatik;

import edu.kit.informatik.GameComponents.Game;
import edu.kit.informatik.Exceptions.CellDoesNotExistsException;
import edu.kit.informatik.Exceptions.ColumnDoesNotExist;
import edu.kit.informatik.Exceptions.GameException;
import edu.kit.informatik.Exceptions.RowDoesNotExist;
import edu.kit.informatik.Utils.GameCommand;
import edu.kit.informatik.Utils.ErrorHandler;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Stanislav
 * @version 0.0.42
 */
public final class CommandLine {

    private static final Map<String, GameCommand> COMMANDS;
    private static final String ARGUMENT_SEPARATOR = ";";
    private static final String COMMAND_SEPARATOR = " ";
    private static final String QUIT_COMMAND = "quit";

    private CommandLine() {

    }

    static {
        COMMANDS = new HashMap<>();
        COMMANDS.put("select", new GameCommand(CommandLine::select, 1, null));
        COMMANDS.put("place", new GameCommand(CommandLine::place, 2, (Game game, ErrorHandler handler) -> {
            game.unselectToken();
        }));
        COMMANDS.put("bag", new GameCommand(CommandLine::bag, 0, null));
        COMMANDS.put("colprint", new GameCommand(CommandLine::colprint, 1, null));
        COMMANDS.put("rowprint", new GameCommand(CommandLine::rowprint, 1, null));
        COMMANDS.put("print", new GameCommand(CommandLine::print, 0, null));

    }

    /**
     * Starts the iterative sequence that takes input commands from the command
     * line
     *
     * @param errorHandler Error handler that can handle errors that happen due
     * to user input
     * @param game The game which will be played
     */
    public static void beginInputSequence(ErrorHandler errorHandler,
            Game game) {

        game.setWinAction(CommandLine::endGame);
        while (true) {
            String command = Terminal.readLine();
            if (command == null) {
                System.exit(1);
            }
            String data[] = command.split(COMMAND_SEPARATOR);

            try {
                if (data[0].equals(QUIT_COMMAND)) {
                    System.exit(0);
                } else if (COMMANDS.containsKey(data[0])) {
                    if (data.length == 1) {
                        COMMANDS.get(data[0]).execute(game, new String[0], errorHandler);
                    } else {
                        COMMANDS.get(data[0]).execute(game, data[1].split(ARGUMENT_SEPARATOR), errorHandler);
                    }
                } else {
                    errorHandler.printErrorMessage("There is no such command");
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                errorHandler.printErrorMessage("No arguments for the command found");
            } catch (IllegalArgumentException ex) {
                errorHandler.handelException(ex);
            }
        }

    }

    /**
     * This function is called when the game is over
     *
     * @param winner The winner of the game
     * @param move The move on which the winner has won
     */
    private static void endGame(Integer winner, Integer move) {
        if (winner == 0) {
            Terminal.printLine("draw");
        } else {
            Terminal.printLine("P" + winner + " wins");
            Terminal.printLine(move.toString());
        }

    }

    /**
     * Selects a token from the pile
     *
     * @param args The arguments of the command
     * @param game The game on which the command is executed
     * @param handler Error handler that can take care of errors during the
     * execution
     */
    private static void select(String args[], Game game, ErrorHandler handler) {
        try {
            int id = Integer.valueOf(args[0]);
            game.select(id);
            Terminal.printLine("OK");
        } catch (GameException ex) {
            handler.handelException(ex);
        } catch (NumberFormatException ex) {
            handler.printErrorMessage("Bad input");
        }

    }

    /**
     * Places the selected token on the board
     *
     * @param args The arguments of the command
     * @param game The game on which the command is executed
     * @param handler Error handler that can take care of errors during the
     * execution
     */
    private static void place(String args[], Game game, ErrorHandler handler) {
        try {
            int row = Integer.valueOf(args[0]);
            int col = Integer.valueOf(args[1]);
            game.place(col, row);
            if (game.isRunning()) {
                Terminal.printLine("OK");
            }
        } catch (GameException | CellDoesNotExistsException ex) {
            handler.handelException(ex);
        } catch (NumberFormatException ex) {
            handler.printErrorMessage("Bad input");
        }
    }

    /**
     * Shows all of the available tokens in the bag
     *
     * @param args The arguments of the command
     * @param game The game on which the command is executed
     * @param handler Error handler that can take care of errors during the
     * execution
     */
    private static void bag(String args[], Game game, ErrorHandler handler) {
        Terminal.printLine(game.getBagContent());
    }

    /**
     * Shows the content of a specific row of the board
     *
     * @param args The arguments of the command
     * @param game The game on which the command is executed
     * @param handler Error handler that can take care of errors during the
     * execution
     */
    private static void rowprint(String args[], Game game, ErrorHandler handler) {
        try {
            int row = Integer.valueOf(args[0]);
            Terminal.printLine(game.getBoardRow(row));
        } catch (RowDoesNotExist ex) {
            handler.handelException(ex);
        } catch (NumberFormatException ex) {
            handler.printErrorMessage("Bad input");
        }
    }

    /**
     * Shows the content of a specific column of the board
     *
     * @param args The arguments of the command
     * @param game The game on which the command is executed
     * @param handler Error handler that can take care of errors during the
     * execution
     */
    private static void colprint(String args[], Game game, ErrorHandler handler) {
        try {
            int col = Integer.valueOf(args[0]);
            Terminal.printLine(game.getBoardColumn(col));
        } catch (ColumnDoesNotExist ex) {
            handler.handelException(ex);
        } catch (NumberFormatException ex) {
            handler.printErrorMessage("Bad input");
        }
    }

    /**
     * Command only for debugging purposes. Displays the whole board
     *
     * @param args The arguments of the command
     * @param game The game on which the command is executed
     * @param handler Error handler that can take care of errors during the
     * execution
     */
    private static void print(String args[], Game game, ErrorHandler handler) {
        Terminal.printLine(game.getBoard());
    }

}
