package view;

import java.awt.*;
import java.awt.geom.Path2D;

/**
 * Draws a pointy-top hexagon on the GUI based a given size or size 5 for default.
 */
class Path2DHexagon extends Path2D.Double {
  private final double size;
  private final Color color; //right now, always set to light gray

  /**
   * Convenience constructor for standardizing the size of each Hexagon in the GUI view for a game
   * of Reversi to be 5.
   */
  public Path2DHexagon() {
    this.size = 5.0;
    defineHexagon();
    this.color = Color.LIGHT_GRAY;
  }

  /**
   * Constructor that creates a path object that draws the hexagon given some size for the
   * Hexagon.
   *
   * @param size the size specified
   */
  public Path2DHexagon(double size) {
    this.size = size;
    defineHexagon();
    this.color = Color.LIGHT_GRAY;
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

}
