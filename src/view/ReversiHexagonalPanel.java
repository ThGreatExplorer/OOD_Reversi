package view;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.List;

import javax.swing.*;

import model.Hexagon;
import model.PlayingBoard;
import model.ReadOnlyReversiModel;


/**
 * Represents a Hexagonal panel in a game of Reversi where each cell in the grid is a hexagon that
 * is drawn using Path2dHexagon. Converts the coordinate system to a standard x,y coordinate where
 * the origin is the center of the hexagon, the hexagon at coordinates 0,0,0. Then applies
 * appropriate transforms to generate new hexagons relative to the origin on the xy coordinate
 * system from the q,r,s coordiante system.
 */
public class ReversiHexagonalPanel extends JPanel implements ReversiPanel {
  List<Hexagon> hexagons;
  int numRings;
  ReadOnlyReversiModel model;
  double hexSize;

  /**
   * Constructor for the ReversiHexagonalPanel that takes in a readonly model and the hexSize.
   *
   * @param model the readonly model
   * @param hexSize the size of each Hexagon
   */
  public ReversiHexagonalPanel(ReadOnlyReversiModel model, double hexSize) {
    if (model == null) {
      throw new IllegalArgumentException("Model is null!");
    }
    PlayingBoard board = model.getCurrentBoardState();
    this.model = model;
    this.hexagons = board.getBoard();
    this.numRings = board.getSize();
    this.hexSize = hexSize;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    // Calculate center of the panel
    int centerX = getWidth() / 2;
    int centerY = getHeight() / 2;

    Graphics2D g2d = (Graphics2D) g;
    AffineTransform originalTransform = g2d.getTransform();

    // Apply global transform
    AffineTransform globalTransform = new AffineTransform();
    globalTransform.translate(centerX, centerY);
    globalTransform.scale(1, -1); // Flip the y-axis
    g2d.transform(globalTransform);

    //draw the hexagons from the hexagonal board starting at the origin
    for (Hexagon hex : hexagons) {
      int q = hex.getQ();
      int r = hex.getR();

      //coordinates of that hexagon's center
      double x = hexSize * (Math.sqrt(3) * q + Math.sqrt(3) / 2 * r);
      double y = hexSize * (3.0 / 2 * r);

      Path2DHexagon hexPath = new Path2DHexagon(hexSize);
      AffineTransform hexTransform = new AffineTransform();
      hexTransform.translate(x, y);
      hexPath.transform(hexTransform);

      hexPath.drawHexagon(g2d);
    }

    g2d.setTransform(originalTransform); // Restore the original transform
    g2d.dispose();
  }
}
