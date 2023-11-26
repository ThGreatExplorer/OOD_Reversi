package model;

import controller.ModelFeatures;

/**
 * The interface represents a model that functions as both a rules keeper and the board state
 * for a game of Reversi, facilitating communications from the board state to the rules keeper.
 */
public interface ReversiModel extends ReadOnlyReversiModel {

  //TODO
  void addModelFeatures(ModelFeatures modelFeatures);

  void notifyMoveMade();

  //TODO
  void notifyPlayerTurn();

  //TODO
  /**
   * Starts a game of Reversi. Sets up all the players as listeners before the game begins.
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
   * @param q The q coordinate of the disc to place.
   * @param r The r coordinate of the disc to place.
   * @param s The s coordinate of the disc to place.
   * @throws IllegalArgumentException move is not valid
   */
  void move(Color color, int q, int r, int s) throws IllegalArgumentException;


}
