package Model;


public interface ReversiModel {

  Color getCurrentPlayer();

  PlayingBoard getCurrentBoardState();

  boolean canMakeMove(Color color);

  /**
   * Player makes a move to pass out of their turn. Switches to the next Player in the ENUM Players
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
   * @throws IllegalArgumentException no legal moves or move is not valid
   */
  void move(int q, int r, int s) throws IllegalArgumentException;

  boolean isGameOver();


  int getScore(Color color);
}
