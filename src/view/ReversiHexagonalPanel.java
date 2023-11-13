package view;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import model.Color;
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
public class ReversiHexagonalPanel extends JPanel implements ReversiPanel, MouseListener {
  private final PlayingBoard boardState;
  private final List<Path2DHexagon> drawnHexagons;
  private final List<Hexagon> hexagons;
  private final ReadOnlyReversiModel model;
  private final double hexSize;
  private Path2DHexagon selectedHexagon; //intialized as null, stores the most recently selected
  //hexagon

  /*
  Class invariant:
  After the first paint, there should be a one to one correspondence between the list of the drawn
  Path2DHexagons and the list of the Hexagons where the 0th element of the Path2dHexagon has the
  same q,r,s coordinates as the 0th element of the Hexagons. This holds from index 0 to the end of
  Hexagons.
   */

  /**
   * Constructor for the ReversiHexagonalPanel that takes in a readonly model and the hexSize.
   * Creates the panel as a MouseListener.
   *
   * @param model the readonly model
   * @param hexSize the size of each Hexagon
   */
  public ReversiHexagonalPanel(ReadOnlyReversiModel model, double hexSize) {
    if (model == null) {
      throw new IllegalArgumentException("Model is null!");
    }
    this.model = model;
    this.boardState = model.getCurrentBoardState();
    this.hexagons = boardState.getBoard();
    this.hexSize = hexSize;
    this.drawnHexagons = new ArrayList<>();
    this.selectedHexagon = null;
    addMouseListener(this);
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

    int i = 0;
    //draw the hexagons from the hexagonal board starting at the origin
    for (Hexagon hex : hexagons) {
      int q = hex.getQ();
      int r = hex.getR();
      int s = hex.getS();

      //coordinates of that hexagon's center
      double x = hexSize * (Math.sqrt(3) * q + Math.sqrt(3) / 2 * r);
      double y = hexSize * (3.0 / 2 * r);

      Path2DHexagon hexPath = new Path2DHexagon(hexSize, q, r, s);
      
      if (drawnHexagons.contains(hexPath)) {
        drawnHexagons.get(i).drawHexagon(g2d);
      }
      else {
        AffineTransform hexTransform = new AffineTransform();
        hexTransform.translate(x, y);
        hexPath.transform(hexTransform);

        hexPath.drawHexagon(g2d);
        this.drawnHexagons.add(hexPath);
      }

      if (this.boardState.isOccupiedTile(hex)) {
        Color color = this.boardState.whoOccupiesThisTile(hex);
        Ellipse2D.Double circle =
                new Ellipse2D.Double(x - hexSize / 4, y - hexSize / 4,
                        hexSize / 2, hexSize / 2);
        g2d.setColor(color.convertToColor());
        g2d.fill(circle);
        g2d.draw(circle);
      }
      i++;
    }

    g2d.setTransform(originalTransform); // Restore the original transform
    g2d.dispose();
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    double xCoord = e.getX() - ((double) getWidth() / 2);
    double yCoord = -1 * (e.getY() - ((double) getHeight() / 2));
    System.out.println(drawnHexagons.size());

    boolean hexagonFound = false;

    for (Path2DHexagon dHexagon : drawnHexagons) {
      if (dHexagon.contains(new Point2D.Double(xCoord, yCoord))) {
        System.out.println("Clicked Q:" + dHexagon.q + " R:" + dHexagon.r + " S:" + dHexagon.s);
        if (this.selectedHexagon != null) {
          if (this.selectedHexagon == dHexagon) {
            this.selectedHexagon.selectHexagon();
            this.selectedHexagon = null;
            break;
          }
          else {
            this.selectedHexagon.selectHexagon(); // deselects the current hexagon
            this.selectedHexagon = dHexagon;
            dHexagon.selectHexagon();
            hexagonFound = true;
            break;
          }
        }
        else {
          this.selectedHexagon = dHexagon;
          dHexagon.selectHexagon();
          hexagonFound = true;
          break;
        }
      }
    }

    // If clicked outside, deselect the current hexagon
    if (!hexagonFound && this.selectedHexagon != null) {
      this.selectedHexagon.selectHexagon();
      this.selectedHexagon = null;
    }

    repaint();
  }

  @Override
  public void mousePressed(MouseEvent e) {}

  @Override
  public void mouseReleased(MouseEvent e) {}

  @Override
  public void mouseEntered(MouseEvent e) {}

  @Override
  public void mouseExited(MouseEvent e) {}
}
