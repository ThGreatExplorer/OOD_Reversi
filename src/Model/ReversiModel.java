package Model;


public interface ReversiModel {

  Color getCurrentPlayer();

  PlayingBoard getCurrentBoardState();

  boolean canMakeMove();

  int getScore(Color color);

  /**
   * Player makes a move to pass out of their turn. Switches to the next Player in the ENUM Players
   * by ordinal number.
   */
  void Pass();

  /**
   *
   */
  void move(int q, int r, int s);

  boolean isGameOver();



}
