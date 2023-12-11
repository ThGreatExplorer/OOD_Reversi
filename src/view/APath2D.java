package view;

import java.awt.*;
import java.awt.geom.Path2D;

import model.BoardTile;

public abstract class APath2D<T extends BoardTile> extends Path2D.Double {

  protected double size;
  protected Color color;

  public APath2D(Color color, double size) {
    this.color = color;
    this.size = size;
  }

  /**
   * Returns the color of this hexagon.
   */
  public Color getColor() {
    return this.color;
  }

  public void setColor(Color color) {
    this.color = color;
  }

  /**
   * Selects this Path2D shape. If the shape is already selected, then deselect it. If the
   * shape has not been selected, then select it.
   */
  public void select() {
    if (this.color != Color.LIGHT_GRAY) {
      this.color = Color.LIGHT_GRAY;
    } else {
      this.color = Color.CYAN;
    }
  }

  /**
   * Draws the shape onto a graphics 2d object with the filling being grey and the border being
   * black. Those colors are hardcoded.
   *
   * @param g2d the graphics 2d object being passed in.
   */
  public void draw(Graphics2D g2d) {
    // Fill the hexagon with the specified color
    g2d.setColor(this.color); // Set the fill color
    g2d.fill(this); // Fill the hexagon

    // Draw the hexagon outline in black
    g2d.setColor(Color.BLACK);
    g2d.draw(this); // Draw the outline
  }

  /**
   * Updates this Path2D object with a new size and redraws it appropriately.
   *
   * @param newSize the new size
   */
  public abstract void setSize(double newSize);


}
