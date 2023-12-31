package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class StandardSquareReversiModel extends AReversiModel<Square> {

  public StandardSquareReversiModel(int boardSize) {
    super(boardSize);
  }

  public StandardSquareReversiModel(APlayingBoard<Square> board) {
    super(board);
  }

  public StandardSquareReversiModel(ReadOnlyReversiModel<Square> model) {
    super(model);
  }

  @Override
  protected APlayingBoard<Square> initPLayingBoard(int boardSize) {
    return new StandardSquareBoard(boardSize);
  }

  @Override
  protected APlayingBoard<Square> initPLayingBoard(APlayingBoard<Square> board) {
    return new StandardSquareBoard(board);
  }

  @Override
  public boolean canMakeAnyMove(Color color) {
    return !this.getValidMoveScores(color).isEmpty();
  }

  @Override
  public boolean isValidMove(Color color, int q, int r, int s) {
    throw new IllegalArgumentException("Should not be called by a Square Model");
  }

  @Override
  public boolean isValidMove(Color color, int row, int col) {
    if (this.currentPlayer != color) {
      throw new IllegalArgumentException("Not your turn! Can't move! Current Player: "
              + this.currentPlayer + " Player Attempting Move:" + color);
    }
    if (!this.canMakeAnyMove(this.currentPlayer)) {
      throw new IllegalArgumentException("Can't make any moves, must pass!");
    }
    int size = board.getSize();
    if (Math.abs(col) > size || Math.abs(row) > size) {
      throw new IllegalArgumentException("Not within bounds of the Board!");
    }

    Square tmp = new Square(row, col);
    if (board.isOccupiedTile(tmp)) {
      throw new IllegalArgumentException("Tile is already occupied "
              + board.whoOccupiesThisTile(tmp));
    }

    for (int[] currentDirection : Square.CUBE_DIRECTION_VECTORS) {
      if (countDirectionValidSequence(row, col, currentDirection, color) != 0) {
        return true;
      }
    }
    return false;
  }

  @Override
  public Map<Square, Integer> getValidMoveScores(Color color) {
    Map<Square, Integer> validMovesScore = new HashMap<>();

    //get a list of the Squares that are of the given color.
    List<Square> sameColor = board.getOccupiedTiles().entrySet().stream()
            .filter(entry -> entry.getValue() == color).map(Map.Entry::getKey)
            .collect(Collectors.toList());
    //get a list of all the Squares that are not filled
    List<Square> notFilled = board.getBoard();
    notFilled.removeAll(
            new ArrayList<>(board.getOccupiedTiles().keySet()));

    //iterate through each Square and see if a move can be made
    for (Square start : sameColor) {
      for (Square end : notFilled) {
        int allFlipsLength = 0;
        for (int[] currentDirection : Square.CUBE_DIRECTION_VECTORS) {
          int sequenceLength = countDirectionValidSequence(end.getRow(), end.getCol(),
                  currentDirection, color);
          allFlipsLength += sequenceLength;
        }
        if (allFlipsLength != 0) {
          validMovesScore.put(end, allFlipsLength);
        }
      }
    }
    return validMovesScore;
  }

  @Override
  public Color getColorAt(int q, int r, int s) throws IllegalArgumentException {
    throw new IllegalArgumentException("Should not be called by a Square Model");
  }

  @Override
  public Color getColorAt(int row, int col) throws IllegalArgumentException {
    if (row > board.getSize() || col > board.getSize()) {
      throw new IllegalArgumentException("Invalid Coordinates " + row + " " + col);
    }
    Square square = new Square(row, col);
    if (this.board.isOccupiedTile(square)) {
      return this.board.whoOccupiesThisTile(square);
    }
    else {
      return null;
    }
  }

  @Override
  public void move(Color color, int q, int r, int s) throws IllegalArgumentException {
    throw new IllegalArgumentException("Should not be called by a Square Model");
  }

  @Override
  public void move(Color color, int row, int col) throws IllegalArgumentException {
    //check if the game is already over
    if (this.isGameOver) {
      throw new IllegalArgumentException("Game is already over!");
    }
    //check if the move is valid
    if (!this.isValidMove(color, row, col)) {
      throw new IllegalArgumentException("Invalid logical move!");
    }

    for (int[] direction : Square.CUBE_DIRECTION_VECTORS) {
      int sequenceLength = this.countDirectionValidSequence(row, col, direction, color);
      if (sequenceLength != 0) {
        this.switchTilesGivenPositionAndDirection(row, col, sequenceLength, direction);
      }
    }

    //place the color down at the given tile
    this.board.occupyTile(row, col, this.currentPlayer);
    //switch the player to the next player
    this.switchPlayer();
    //set pass to false
    this.flagPass = false;
    //checks if the next player can make a move, if not, forces that player to pass
    if (!this.canMakeAnyMove(this.currentPlayer)) {
      this.pass();
    }
    this.notifyMoveMade();
  }

  /**
   * Returns the int representing the valid sequence in a given direction. 0 if no valid sequence.
   * Checks if any of the cube direction has a valid sequence of Colors where there must be at
   * least one Color of the opposite color between the Tile of this Color that is being placed
   * and another Tile of this color.
   *
   * @param row                the incoming square's row coordinate
   * @param col                the incoming square's col coordinate
   * @param currentDirection the direction vector that is being tested for
   * @param color the color
   * @return the int representing the valid sequence in a given direction. 0 if no valid sequence.
   * @throws IllegalArgumentException if an invalid direction (i.e. not in cube vectors is given)
   */
  private int countDirectionValidSequence(int row, int col, int[] currentDirection, Color color)
          throws IllegalArgumentException {
    if (!Arrays.asList(Square.CUBE_DIRECTION_VECTORS).contains(currentDirection)) {
      throw new IllegalArgumentException("Invalid Direction given");
    }
    int counter = 0;
    while (Math.abs(row) <= board.getSize() && Math.abs(col) <= board.getSize()) {
      row += currentDirection[0];
      col += currentDirection[1];

      Square tmp = new Square(row, col);
      //check if the tile is occupied in the board
      if (board.isOccupiedTile(tmp)) {
        Color occupyingColor = board.whoOccupiesThisTile(tmp);

        //check if the tile is the same or different color
        if (occupyingColor == color) {
          return counter;
        } else {
          counter++;
        }
      } else {
        return 0;
      }
    }
    return 0;
  }


  /**
   * Switches all the tiles along a valid sequence's length given an incoming Square's coordinates
   * and a direction vector to this Color.
   *
   * @param row             the row coordinate of incoming square.
   * @param col             the col coordinate of incoming square.
   * @param sequenceLength  the length of the sequence to switch.
   * @param directionVector the direction form the incoming coordinates to switch tiles.
   */
  private void switchTilesGivenPositionAndDirection(int row, int col, int sequenceLength,
                                                    int[] directionVector) {
    while (sequenceLength > 0) {
      row += directionVector[0];
      col += directionVector[1];
      Square square = new Square(row, col);
      if (!this.board.isOccupiedTile(square)) {
        throw new IllegalArgumentException("Tile is not occupied");
      }
      if (this.board.whoOccupiesThisTile(square) == this.currentPlayer) {
        throw new IllegalArgumentException("Not able to switch a tile of the same color");
      }
      //switch the tile's Color
      board.occupyTile(row, col, this.currentPlayer);
      sequenceLength--;
    }
  }


}
