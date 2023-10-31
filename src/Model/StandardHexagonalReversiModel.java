package Model;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

  /**
   * Constructor for testing a Standard Reversi game, starts with the player as white. Intializes
   * Board and Rules Keeper at a given Board state.
   *
   * @param board given board state to start the model at.
   */
  public StandardHexagonalReversiModel(PlayingBoard board) {
    this.board = new StandardHexagonalBoard(board);
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


  private void switchTilesGivenPositionAndDirection(int q, int r, int s, int sequenceLength,
                                                    int[] directionVector) {
    while (sequenceLength > 0) {
      q += directionVector[0];
      r += directionVector[1];
      s += directionVector[2];
      Hexagon toSwitch = new Hexagon(q, r, s);
      if (!this.board.isOccupiedTile(toSwitch)) {
        throw new IllegalArgumentException("Tile is not occupied");
      }
      if (this.board.whoOccupiesThisTile(toSwitch) == this.currentPlayer) {
        throw new IllegalArgumentException("Not able to switch a tile of the same color");
      }
      Color color = this.board.whoOccupiesThisTile(toSwitch);
      //switch the tile's Color
      board.occupyTile(q,r,s, this.currentPlayer);
      sequenceLength--;
    }
  }

  /**
   * Checks if a move is valid. Calls <code> determineValidDirectionsForMove </code> that checks if
   * any of the six directions contains a valid move.
   *
   * @param q coordinate of incoming hexagon
   * @param r coordinate of incoming hexagon
   * @param s coordinate of incoming hexagon
   * @return true or false depending on if move is valid
   * @throws IllegalArgumentException if the move is invalid for whatever reason including there
   *     are no available moves for this player at any position, the move is logically invalid, or
   *     if the move is out of bounds
   */
  private boolean isValidMove(int q, int r, int s)
          throws IllegalArgumentException {
    if (!this.canMakeMove(this.currentPlayer)) {
      throw new IllegalArgumentException("Can't make any moves, must pass!");
    }
    int size = board.getSize();
    if (Math.abs(q) > size || Math.abs(r) > size || Math.abs(s) > size) {
      throw new IllegalArgumentException("Not within bounds of the Board!");
    }

    Hexagon tmp = new Hexagon(q,r,s);
    if (board.isOccupiedTile(tmp)) {
      throw new IllegalArgumentException("Tile is already occupied " +
              board.whoOccupiesThisTile(tmp));
    }

    for (int[] currentDirection : Hexagon.CUBE_DIRECTION_VECTORS) {
      if (countDirectionValidSequence(q, r, s, currentDirection) != 0) {
        return true;
      }
    }
    return false;
  }

  private int[][] determineValidDirectionsForMove(int q, int r, int s) {
    int[][] validDirections = new int[Hexagon.CUBE_DIRECTION_VECTORS.length][3];
    for (int i = 0; i < Hexagon.CUBE_DIRECTION_VECTORS.length; i++) {
      if (countDirectionValidSequence(q, r, s, Hexagon.CUBE_DIRECTION_VECTORS[i]) != 0) {
        validDirections[i] = Hexagon.CUBE_DIRECTION_VECTORS[i];
      }
    }
    return validDirections;
  }

  /**
   * Returns the int representing the valid sequence in a given direction. 0 if no valid sequence.
   * Checks if any of the q,r,s direction has a valid sequence of Colors where there must be at
   * least one Color of the opposite color between the Tile of this Color that is being placed
   * and another Tile of this color.
   *
   * @param q the incoming hexagon's q coordinate
   * @param r the incoming hexagon's r coordinate
   * @param s the incoming hexagon's s coordinate
   * @param currentDirection the direction vector that is being tested for
   * @return the int representing the valid sequence in a given direction. 0 if no valid sequence.
   * @throws IllegalArgumentException if an invalid direction (i.e. not in cube vectors is given)
   */
  private int countDirectionValidSequence(int q, int r, int s, int[] currentDirection)
          throws IllegalArgumentException {
    if (!Arrays.asList(Hexagon.CUBE_DIRECTION_VECTORS).contains(currentDirection)) {
      throw new IllegalArgumentException("Invalid Direction given");
    }
    int counter = 0;
    while (Math.abs(q) <= board.getSize() && Math.abs(r) <= board.getSize()
            && Math.abs(s) <= board.getSize()) {
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

  /**
   * Does the same as above, but takes in the color this time.
   */
  private int countDirectionValidSequence(int q, int r, int s, int[] currentDirection, Color color)
          throws IllegalArgumentException {
    if (!Arrays.asList(Hexagon.CUBE_DIRECTION_VECTORS).contains(currentDirection)) {
      throw new IllegalArgumentException("Invalid Direction given");
    }
    int counter = 0;
    while (Math.abs(q) <= board.getSize() && Math.abs(r) <= board.getSize()
            && Math.abs(s) <= board.getSize()) {
      q += currentDirection[0];
      r += currentDirection[1];
      s += currentDirection[2];

      Hexagon tmp = new Hexagon(q,r,s);
      //check if the tile is occupied in the board
      if (board.isOccupiedTile(tmp)) {
        Color occupyingColor = board.whoOccupiesThisTile(tmp);

        //check if the tile is the same or different color
        if (occupyingColor == color) {
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



  //TODO STUB
  @Override
  public boolean canMakeMove(Color color) {
    //get a list of the Hexagons that are of this color.
    List<Hexagon> sameColor = board.getOccupiedTiles().entrySet().stream().
            filter(entry -> entry.getValue() == color).map(Map.Entry::getKey).
            collect(Collectors.toList());
    //get a list of all the Hexagons that are not filled
    List<Hexagon> notFilled = board.getBoard();
    notFilled.removeAll(
            new ArrayList<>(board.getOccupiedTiles().keySet()));

    //iterate through each Hexagon and see if a move can be made
    for (Hexagon start : sameColor) {
      for (Hexagon end : notFilled) {
        //not on the same line
        if (!start.sameLine(end)) {
          continue;
        }
        for (int[] currentDirection : Hexagon.CUBE_DIRECTION_VECTORS) {
          if (countDirectionValidSequence(end.getQ(), end.getR(), end.getS(),
                  currentDirection, color) != 0) {
            return true;
          }
        }
      }
    }
    return false;
  }

  @Override
  public void pass() {
    this.switchPlayer();
  }

  @Override
  public void move(int q, int r, int s) throws IllegalArgumentException {
    //check if the move is valid
    if (!this.isValidMove(q,r,s)) {
      throw new IllegalArgumentException("Invalid logical move!");
    }
    //get the direction vectors for which tiles should be flipped
    int[][] directions = this.determineValidDirectionsForMove(q,r,s);
    for (int[] direction: directions) {
      int[] allZeros = {0,0,0};
      if (!Arrays.equals(direction, allZeros)) {
        //then it is a valid direction
        //get the length and then switch the tile
        int sequenceLength = countDirectionValidSequence(q,r,s,direction);
        this.switchTilesGivenPositionAndDirection(q,r,s,sequenceLength, direction);
      }
    }
    //place the color down at the given tile
    this.board.occupyTile(q,r,s,this.currentPlayer);
    //switch the player
    this.switchPlayer();
  }

  @Override
  public boolean isGameOver() {
    //check if all the tiles are over
    List<Hexagon> occupiedTiles = new ArrayList<>(this.board.getOccupiedTiles().keySet());
    if (new HashSet<>(occupiedTiles).containsAll(this.board.getBoard())) {
      return true;
    }

    //check if both players must pass
    if (!this.canMakeMove(this.currentPlayer) &&
            !this.canMakeMove(this.getCurrentPlayer().getNextColor())) {
      return true;
    }

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

  /**
   * Switches the current Color to the next Player in the ENUM Color by ordinal number. Calls
   * the next method in Color.
   */
  private void switchPlayer() {
    this.currentPlayer = this.currentPlayer.getNextColor();
  }
}
