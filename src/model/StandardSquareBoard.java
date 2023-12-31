package model;


/**
 * Represents a standard Square board that tracks the game state of the square tiles. The squares
 * are organized as entries to a 2D arraylist with the 0,0 index is the top left, and the squares
 * fill in sequentially. The square's row and col indices must equal their entry in the 2d
 * arraylist.
 */
public class StandardSquareBoard extends APlayingBoard<Square> {

  /**
   * Constructs a square board for a given size:
   *  rows ---->
   *  0-index               boardSize
   *  |---------------------|
   *   |                    |
   *   |                     |
   *   |                     |
   *   |                     |
   *   |---------------------|
   *  boardSize
   *
   * @param boardSize the size which is length of the arraylist, i.e. width and height
   */
  public StandardSquareBoard(int boardSize) {
    super(boardSize);

    if (this.boardSize % 2 != 0) {
      throw new IllegalArgumentException("Must be positive even-sided board size");
    }

    //generates the list of square tiles in this game
    for (int row = 0; row < boardSize; row++) {
      for (int col = 0; col < boardSize; col++) {
        this.tiles.add(new Square(row, col));
      }
    }

    //sets up the four occupied tiles in the center at the start of the game
    Square bottomRight = new Square(boardSize/2, boardSize/2);
    Square topLeft = new Square(boardSize/2 - 1, boardSize/2 - 1);
    Square bottomLeft = new Square(boardSize/2 - 1, boardSize/2);
    Square topRight = new Square(boardSize/2, boardSize/2 - 1);

    this.occupiedTiles.put(bottomRight, Color.BLACK);
    this.occupiedTiles.put(topLeft, Color.BLACK);
    this.occupiedTiles.put(bottomLeft, Color.WHITE);
    this.occupiedTiles.put(topRight, Color.WHITE);
  }

  /**
   * Constructs a copy of this playing board.
   *
   * @param squareBoard the incoming board to copy.
   */
  public StandardSquareBoard(APlayingBoard<Square> squareBoard) {
    super(squareBoard);
  }

  @Override
  void occupyTile(int q, int r, int s, Color color) {
    //do Nothing
  }

  @Override
  void occupyTile(int row, int col, Color color) {
    Square square = new Square(row, col);

    if (!this.tiles.contains(square)) {
      throw new IndexOutOfBoundsException("Incoming hexagon is not on the board!");
    }

    //adds or updates a tile to have that color
    this.occupiedTiles.put(square, color);
  }
}
