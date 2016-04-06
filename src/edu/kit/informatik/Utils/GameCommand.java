package edu.kit.informatik.Utils;

import edu.kit.informatik.GameComponents.Game;
import java.util.function.BiConsumer;

/**
 *
 * @author Stanislav
 * @version 0.0.42
 */
public class GameCommand {

    private final TriConsumer<String[], Game, ErrorHandler> action;
    private final int argCount;
    private final BiConsumer<Game, ErrorHandler> onCommandFailed;

    /**
     * Creates a basic command that can perform on the game
     *
     * @param action The action that should be performed
     * @param argCount The count of the arguments that the operation takes
     * @param onCommandFailed Action to be taken if the parsing of the
     * parameters fails
     */
    public GameCommand(TriConsumer<String[], Game, ErrorHandler> action,
            int argCount, BiConsumer<Game, ErrorHandler> onCommandFailed) {
        this.action = action;
        this.argCount = argCount;
        this.onCommandFailed = onCommandFailed;
    }

    /**
     * When this function is called, the action on the game in performed
     *
     * @param game The game on which the command will performed
     * @param args The arguments of the actions
     * @param errorHandler Error handler that can take care of errors during the
     * execution of the action
     *
     */
    public void execute(Game game, String args[], ErrorHandler errorHandler) {
        if (args.length != argCount) {
            if (onCommandFailed != null) {
                onCommandFailed.accept(game, errorHandler);
            }
            throw new IllegalArgumentException("The command takes exactly " + argCount + " argument(s)");
        }
  
        action.accept(args, game, errorHandler);

    }

    /**
     * Interface that represents a function that takes three arguments
     *
     * @param <A> The type of the first argument
     * @param <B>The type of the second argument
     * @param <C> The type of the third argument
     */
    @FunctionalInterface
    public interface TriConsumer<A, B, C> {

        /**
         * The function that will be executed
         *
         * @param a First argument
         * @param b Second argument
         * @param c Third argument
         */
        void accept(A a, B b, C c);
    }

}
