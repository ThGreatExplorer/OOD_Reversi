package view;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.geom.Path2D;
import java.util.Objects;

import model.Hexagon;

/**
 * Creates a pointy-top hexagon in the GUI based a given size, tracks the state of this object
 * throughout the changes in the GUI.
 */
public final class Path2DHexagon extends APath2D<Hexagon> {
  final int q;
  final int r;
  final int s;


  /**
   * Constructor that creates a path object that draws the hexagon given some size for the
   * Hexagon.
   *
   * @param size the size specified
   */
  public Path2DHexagon(double size, int q, int r, int s) {
    super(Color.LIGHT_GRAY, size);
    this.q = q;
    this.r = r;
    this.s = s;
    this.defineHexagon();
  }

  @Override
  public void setSize(double newSize) {
    this.size = newSize;
    this.defineHexagon(); // Redraw the hexagon with the new size
  }

  /**
   * Defines the shape of the hexagon.
   */
  private void defineHexagon() {
    this.reset();
    double angle = Math.PI / 3; // 60 degrees in radians

    // Start at the first vertex with a 30 degrees offset for a pointy-top
    this.moveTo(size * Math.cos(Math.PI / 6), size * Math.sin(Math.PI / 6));

    // Draw lines to the other vertices
    for (int i = 1; i <= 6; i++) {
      this.lineTo(size * Math.cos((Math.PI / 6) + (angle * i)),
          size * Math.sin((Math.PI / 6) + (angle * i)));
    }

    // Close the path to complete the hexagon
    this.closePath();
  }


  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Path2DHexagon)) {
      return false;
    }
    Path2DHexagon hex = (Path2DHexagon) o;
    return (this.q == hex.q) && (this.r == hex.r) && (this.s == hex.s);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.q, this.r, this.s);
  }

}
