package view;

import java.awt.*;
import java.awt.geom.Path2D;
import java.util.Objects;

import model.Square;

/**
 * Creates a square in the GUI based a given size, tracks the state of this object
 * throughout the changes in the GUI.
 */
public final class Path2DSquare extends APath2D<Square> {

  private double size;
  private Color color;
  final int row;
  final int col;

  public Path2DSquare(int row, int col, double size) {
    super(Color.LIGHT_GRAY, size);
    this.row = row;
    this.col = col;
    this.size = size;
    this.defineSquare();
  }

  @Override
  public void setSize(double newSize) {
    this.size = newSize;
    this.defineSquare(); // Redraw the hexagon with the new size
  }

  private void defineSquare() {
    this.reset();

    // Set the initial point to the bottom-left of the square
    this.moveTo(col * size, row * size);

    // Draw to the bottom-right of the square
    this.lineTo(col * size + size, row * size);

    // Draw to the top-right of the square
    this.lineTo(col * size + size, row * size + size);

    // Draw to the top-left of the square
    this.lineTo(col * size, row * size + size);

    // Draw back to the starting point (bottom-left)
    this.lineTo(col * size, row * size);

    // Close the path to complete the square
    this.closePath();
  }


  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Path2DSquare)) {
      return false;
    }
    Path2DSquare square = (Path2DSquare) o;
    return (this.row == square.row) && (this.col == square.col);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.row, this.col);
  }

}
