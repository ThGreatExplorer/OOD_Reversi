package model;

/**
 * The interface represents a model that functions as both a rules keeper and the board state
 * for a game of Reversi, facilitating communications from the board state to the rules keeper.
 */
public interface ReversiModel extends ReadOnlyReversiModel {

  /**
   * Checks if there's <b>Any</b> possible moves that can be made by the given player.
   *
   * @param color the Color to check possible moves for
   * @return true if there are any moves that can be made
   */
  boolean canMakeAnyMove(Color color);

  /**
   * Checks if a move is valid. Checks if any of the six directions contains a valid move.
   *
   * @param q coordinate of incoming hexagon
   * @param r coordinate of incoming hexagon
   * @param s coordinate of incoming hexagon
   * @return true or false depending on if move is valid
   * @throws IllegalArgumentException if the move is invalid for whatever reason including the move
   *     is logically invalid, or if the move is out of bounds
   */
  boolean isValidMove(int q, int r, int s);

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
   *
   * @param q The q coordinate of the disc to place.
   * @param r The r coordinate of the disc to place.
   * @param s The s coordinate of the disc to place.
   * @throws IllegalArgumentException move is not valid
   */
  void move(int q, int r, int s) throws IllegalArgumentException;


}
