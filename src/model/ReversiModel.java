package model;

import controller.ModelObserverFeatures;
import cs3500.reversi.provider.controller.ModelFeatures;

/**
 * The interface represents a model that functions as both a rules keeper and the board state
 * for a game of Reversi, facilitating communications from the board state to the rules keeper.
 */
public interface ReversiModel extends ReadOnlyReversiModel {

  /**
   * Subscribes the given ModelObserverFeatures to the model. This allows the controller, which has
   * the ModelObserverFeatures as a delegate, to listen to the model whenever a move is made in
   * the model.
   *
   * @param modelFeatures the ModelObserverFeatures to be added
   */
  void addModelFeatures(ModelObserverFeatures modelFeatures);

  /**
   * Adds the provider's listeners to our model.
   *
   * @param providerFeatures the provider's listener
   */
  void addMoveFeatures(ModelFeatures providerFeatures);

  /**
   * Notifies all the ModelObserverFeatures (controllers) subscribed to this model that a move has
   * been made in the model. This allows the controller to update the view with the new board state.
   */
  void notifyMoveMade();

  /**
   * Starts a game of Reversi. Notifies all the listeners that a game has started. Calls
   * notifyMoveMade()
   */
  void startGame();

  /**
   * Player passes their turn. Switches to the next Player in the ENUM Color
   * by ordinal number.
   */
  void pass();

  /**
   * Performs a move if valid, where a valid move is defined as for a given Player A,
   * if the disc being played is adjacent (in at least one direction) to a straight line of
   * the opponent player B’s discs, at the far end of which is another player A disc.
   * <p></p>
   * Flips all the discs of Player B between the two ends of A.
   * <p></p>
   * Switches the Player.
   * Calls [ValidMove](isValidMove) method
   *
   * @param color the color of the player being moved for.
   * @param q     The q coordinate of the disc to place.
   * @param r     The r coordinate of the disc to place.
   * @param s     The s coordinate of the disc to place.
   * @throws IllegalArgumentException move is not valid
   */
  void move(Color color, int q, int r, int s) throws IllegalArgumentException;


}
