package Model;


/**
 * Reversi game model for standard hexagonal board following the normal rules of the game.
 */
public class StandardHexagonalReversiModel implements ReversiModel {
  private final PlayingBoard board;
  private Color currentPlayer;


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

  //Perform move that first calls isValidMove in RulesKeeper

  @Override
  public void Pass() {
    this.switchPlayer();
  }


  /**
   * Switches the current Player to the next Player in the ENUM Players by ordinal number. Calls
   * the next method in Players.
   */
  private void switchPlayer() {
    this.currentPlayer = this.currentPlayer.getNextColor();
  }

  @Override
  public boolean isValidMove()
      throws IllegalArgumentException {
    //if no value moves ATALL in the first place should throw IllegalArgumentException
    //TODO validation logic
    return false;
  }


  @Override
  public int getScore(Color color) {
    int countScore = 0;

    //go through the map of all the occupied hexagons and count how many are of the same color
    for (Color discColor: board.getOccupiedTiles().values()){
      if (discColor.equals(color)){
        countScore++;
      }
    }

    return countScore;
  }
}
