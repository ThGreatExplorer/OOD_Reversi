package model;

import java.util.Objects;

/**
 * Represents a square tile.
 * <p>
 * </p>
 * Invariant: must have the same row and col coordinates as its location in 2d arraylist where the
 * square is created.
 */
public final class Square implements BoardTile {

  private final int row;
  private final int col;
  public static final int[][] CUBE_DIRECTION_VECTORS = {{0,1}, {0,-1}, {1,1}, {1,-1}, {1,0},
          {-1,1}, {-1,-1}, {-1,0}};

  public Square(int row, int col) {
    this.row = row;
    this.col = col;
  }

  public int getRow() {
    return this.row;
  }

  public int getCol() {
    return this.col;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Square)) {
      return false;
    }
    Square square = (Square) o;
    return row == square.getRow() && col == square.getCol();
  }

  @Override
  public int hashCode() {
    return Objects.hash(row, col);
  }

  @Override
  public String toString() {
    return "(" + this.row + ", " + this.col + ")";
  }
}
