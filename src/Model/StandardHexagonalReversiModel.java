package Model;


import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Reversi game model for standard hexagonal board following the normal rules of the game.
 */
public class StandardHexagonalReversiModel implements ReversiModel {
  private final PlayingBoard board;
  private Color currentPlayer;

  private static final int[][] CUBE_DIRECTION_VECTORS = {{-1, 0, +1}, {0, -1, +1}, {+1, -1, 0},
          {+1, 0, -1}, {0, +1, -1}, {-1, +1, 0}}; //from clockwise direction starting from left


  /**
   * Default constructor for a Standard Reversi game, starts with the player as white. Intializes
   * Board and Rules Keeper.
   *
   * @param boardSize the distance from center of board.
   */
  public StandardHexagonalReversiModel(int boardSize) {
    this.board = new StandardHexagonalBoard(boardSize);
    this.currentPlayer = Color.WHITE;
  }

  //method to get current player
  @Override
  public Color getCurrentPlayer() {
    return this.currentPlayer;
  }

  //method to get the current Board()
  @Override
  public PlayingBoard getCurrentBoardState() {
    return new StandardHexagonalBoard(this.board);
  }

  //isGameOverMethod

  //check if there are any possible moves in the first place...


  //TODO
  @Override
  public boolean canMakeMove() {
    return false;
  }

  @Override
  public int getScore(Color c) {
    return 0;
  }

  //check if the given Player move is valid
  private boolean isValidMove(int q, int r, int s)
          throws IllegalArgumentException, IllegalStateException {
    //TODO
    if (!this.canMakeMove()) {
      throw new IllegalArgumentException("Can't make any moves, must pass!");
    }
    int size = board.getSize();
    if (Math.abs(q) > size || Math.abs(r) > size || Math.abs(s) > size) {
      throw new IllegalArgumentException("Not within bounds of the Board!");
    }

    Hexagon tmp = new Hexagon(q,r,s);
    if (board.isOccupiedTile(tmp)) {
      throw new IllegalArgumentException("Tile is already occupied" +
              board.whoOccupiesThisTile(tmp));
    }

    for (int[] currentDirection : CUBE_DIRECTION_VECTORS) {
      if (countDirectionValidSequence(q, r, s, currentDirection) != 0) {
        return true;
      }
    }
    return false;
  }

  /**
   * Returns the int representing the valid sequence in a given direction. 0 if no valid sequence.
   * Checks if any of the q,r,s direction has a valid sequence of Colors where there must be at
   * least one Color of the opposite color between the Tile of this Color that is being placed
   * and another Tile of this color.
   *
   * @return the int representing the valid sequence in a given direction. 0 if no valid sequence.
   * @throws IllegalArgumentException if an invalid direction (i.e. not in cube vectors is given)
   */
  private int countDirectionValidSequence(int q, int r, int s, int[] currentDirection)
          throws IllegalArgumentException {
    if (!Arrays.asList(CUBE_DIRECTION_VECTORS).contains(currentDirection)) {
      throw new IllegalArgumentException("Invalid Direction given");
    }
    int counter = 0;
    while (Math.abs(q) < board.getSize() && Math.abs(r) < board.getSize()
            && Math.abs(s) < board.getSize()) {
      q += currentDirection[0];
      r += currentDirection[1];
      s += currentDirection[2];

      Hexagon tmp = new Hexagon(q,r,s);
      //check if the tile is occupied in the board
      if (board.isOccupiedTile(tmp)) {
        Color occupyingColor = board.whoOccupiesThisTile(tmp);

        //check if the tile is the same or different color
        if (occupyingColor == this.currentPlayer) {
          return counter;
        }
        else {
          counter++;
        }
      }
      else {
        return 0;
      }
    }
    return 0;
  }


  //Perform move that first calls isValidMove in RulesKeeper

  @Override
  public void Pass() {
    this.switchPlayer();
  }

  @Override
  public void move(int q, int r, int s) {

  }

  @Override
  public boolean isGameOver() {
    return false;
  }


  /**
   * Switches the current Player to the next Player in the ENUM Players by ordinal number. Calls
   * the next method in Players.
   */
  private void switchPlayer() {
    this.currentPlayer = this.currentPlayer.getNextColor();
  }


}
