package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import model.Color;
import model.Hexagon;
import model.PlayingBoard;
import model.ReadOnlyReversiModel;


/**
 * Represents a Hexagonal panel in a game of Reversi where each cell in the grid is a hexagon that
 * is drawn using Path2dHexagon. Converts the coordinate system to a standard x,y coordinate where
 * the origin is the center of the hexagon, the hexagon at coordinates 0,0,0. Then applies
 * appropriate transforms to generate new hexagons relative to the origin on the xy coordinate
 * system from the q,r,s coordinate system.
 */
public class ReversiHexagonalPanel extends JPanel implements ReversiPanel {

  final ReadOnlyReversiModel model;
  private final List<Path2DHexagon> drawnHexagons; //tracks the state of the Path2DHexagons being
  private Path2DHexagon selectedHexagon; //initialized as null, stores the most recently selected
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
   */
  public ReversiHexagonalPanel(ReadOnlyReversiModel model) {
    if (model == null) {
      throw new IllegalArgumentException("Model is null!");
    }
    this.model = model;
    this.drawnHexagons = new ArrayList<>();
    this.selectedHexagon = null;
  }


  @Override
  public int[] getSelectedHexagon() {
    if (this.selectedHexagon == null) {
      return null;
    }
    return new int[]{selectedHexagon.q, selectedHexagon.r, selectedHexagon.s};
  }

  @Override
  public void overwriteSelectedHexagon() {
    this.selectedHexagon = null;
    this.repaint();
  }


  @Override
  protected void paintComponent(Graphics g) {

    super.paintComponent(g);

    //clear the hexagons from the previous board state
    drawnHexagons.clear();

    //get updated board state
    PlayingBoard boardState = model.getCurrentBoardState();

    // Recreate hexagons based on updated board state
    List<Hexagon> hexagons = boardState.getBoard();
    for (Hexagon hex : hexagons) {
      int q = hex.getQ();
      int r = hex.getR();
      int s = hex.getS();
      //initialize with some default size
      Path2DHexagon hexPath = new Path2DHexagon(5, q, r, s);
      this.drawnHexagons.add(hexPath);
    }

    // Calculate center of the panel
    int centerX = getWidth() / 2;
    int centerY = getHeight() / 2;

    //rewrite the size of the hexagon before drawing it to have the hexagon dynamically resize
    //when the screen is resized
    double hexSize = Math.min(this.getWidth() / (4.0 * boardState.getSize()),
            this.getHeight() / (4.0 * boardState.getSize()));

    Graphics2D g2d = (Graphics2D) g;
    AffineTransform originalTransform = g2d.getTransform();

    // Apply global transform
    AffineTransform globalTransform = new AffineTransform();
    globalTransform.translate(centerX, centerY);
    //globalTransform.scale(1, -1); // Flip the y-axis
    g2d.transform(globalTransform);

    for (Path2DHexagon dHexagon : drawnHexagons) {
      int q = dHexagon.q;
      int r = dHexagon.r;
      int s = dHexagon.s;

      //coordinates of that hexagon's center
      double x = hexSize * (Math.sqrt(3) * q + Math.sqrt(3) / 2 * r);
      double y = hexSize * (3.0 / 2 * r);

      dHexagon.setSize(hexSize);

      //overwrite the color of the hexagon if selected
      if (dHexagon.equals(selectedHexagon)) {
        dHexagon.setColor(selectedHexagon.getColor());
      }

      AffineTransform hexTransform = new AffineTransform();
      //send the hexagon to the x,y coordinate of its center
      hexTransform.translate(x, y);
      dHexagon.transform(hexTransform);
      //draw the hexagon
      dHexagon.drawHexagon(g2d);

      Hexagon tmp = new Hexagon(q, r, s);

      // if the tile is occupied, draw a circle in the center of the hexagon
      if (boardState.isOccupiedTile(tmp)) {
        Color color = boardState.whoOccupiesThisTile(tmp);
        Ellipse2D.Double circle =
                new Ellipse2D.Double(x - hexSize / 4, y - hexSize / 4,
                        hexSize / 2, hexSize / 2);
        g2d.setColor(color.convertToColor());
        g2d.fill(circle);
        g2d.draw(circle);
      }
    }

    // Restore the original transform
    g2d.setTransform(originalTransform);
    g2d.dispose();
  }


  @Override
  public void mouseClicked(double xCoord, double yCoord) {
    boolean hexagonFound = false;

    for (Path2DHexagon dHexagon : drawnHexagons) {
      if (dHexagon.contains(new Point2D.Double(xCoord, yCoord))) {
        //System.out.println("Clicked Q:" + dHexagon.q + " R:" + dHexagon.r + " S:" + dHexagon.s);
        if (this.selectedHexagon != null) {
          if (this.selectedHexagon == dHexagon) {
            this.selectedHexagon.selectHexagon();
            this.selectedHexagon = null;
            break;
          } else {
            this.selectedHexagon.selectHexagon(); // deselects the current hexagon
            this.selectedHexagon = dHexagon;
            dHexagon.selectHexagon();
            hexagonFound = true;
            break;
          }
        } else {
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
}