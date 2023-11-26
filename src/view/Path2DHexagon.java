package view;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.geom.Path2D;
import java.util.Objects;

/**
 * Creates a pointy-top hexagon in the GUI based a given size, tracks the state of this object
 * throughout the changes in the GUI.
 */
class Path2DHexagon extends Path2D.Double {
  private double size;
  private Color color;
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
    this.size = size;
    this.color = Color.LIGHT_GRAY;
    this.q = q;
    this.r = r;
    this.s = s;
    this.defineHexagon();
  }

  /**
   * Returns the color of this hexagon.
   */
  public Color getColor() {
    return this.color;
  }

  public Color setColor(Color color) {
    return this.color = color;
  }

  /**
   * Updates this Path2DHexagon object with a new size and redraws it appropriately.
   *
   * @param newSize the new size
   */
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

  /**
   * Selects this Path2DHexagon. If the hexagon is already selected, then deselect it. If the
   * hexagon has not been selected, then select it.
   */
  public void selectHexagon() {
    if (this.color != Color.LIGHT_GRAY) {
      this.color = Color.LIGHT_GRAY;
    } else {
      this.color = Color.CYAN;
    }
  }


  /**
   * Draws the hexagon onto a graphics 2d object with the filling being grey and the border being
   * black. Those colors are hardcoded.
   *
   * @param g2d the graphics 2d object being passed in.
   */
  public void drawHexagon(Graphics2D g2d) {
    // Fill the hexagon with the specified color
    g2d.setColor(this.color); // Set the fill color
    g2d.fill(this); // Fill the hexagon

    // Draw the hexagon outline in black
    g2d.setColor(Color.BLACK);
    g2d.draw(this); // Draw the outline
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
