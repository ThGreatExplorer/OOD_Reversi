package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JPanel;

import model.Color;
import model.Hexagon;
import model.APlayingBoard;
import model.ReadOnlyReversiModel;


/**
 * Represents a Hexagonal panel in a game of Reversi where each cell in the grid is a hexagon that
 * is drawn using Path2dHexagon. Converts the coordinate system to a standard x,y coordinate where
 * the origin is the center of the hexagon, the hexagon at coordinates 0,0,0. Then applies
 * appropriate transforms to generate new hexagons relative to the origin on the xy coordinate
 * system from the q,r,s coordinate system.
 */
public class ReversiHexagonalPanel extends AReversiPanel<Path2DHexagon, Hexagon> {


  public ReversiHexagonalPanel(ReadOnlyReversiModel<Hexagon> model) {
    super(model);
  }

  @Override
  public int[] getSelected() {
    if (this.selected == null) {
      return null;
    }
    return new int[]{selected.q, selected.r, selected.s};
  }

  @Override
  protected Path2DHexagon createObj(Hexagon tile, int size) {
    return new Path2DHexagon(size, tile.getQ(), tile.getR(), tile.getS());
  }

  @Override
  protected Hexagon createTile(int[] coordinates) {
    if (coordinates.length != 3) {
      throw new IllegalArgumentException("Coordinates must be q,r,s, not: "
              + Arrays.toString(coordinates));
    }
    return new Hexagon(coordinates[0], coordinates[1], coordinates[2]);
  }

  @Override
  protected Hexagon createTile(Path2DHexagon obj) {
    return new Hexagon(obj.q, obj.r, obj.s);
  }

  @Override
  protected double[] calculateXY(Path2DHexagon path2dObj, double hexSize) {
    double x =  hexSize * (Math.sqrt(3) * path2dObj.q + Math.sqrt(3) / 2 * path2dObj.r);
    double y = hexSize * (3.0 / 2 * path2dObj.r);
    return new double[]{x,y};
  }

  @Override
  protected double[] calculateXY(int[] coordinates, double hexSize) {
    if (coordinates.length != 3) {
      throw new IllegalArgumentException("Coordinates must be q,r,s, not: "
              + Arrays.toString(coordinates));
    }
    double x = hexSize * (Math.sqrt(3) * coordinates[0] + Math.sqrt(3) / 2 * coordinates[1]);
    double y = hexSize * (3.0 / 2 * coordinates[1]);
    return new double[]{x,y};
  }

  @Override
  protected double[] mouseToXY(MouseEvent e, int width, int height) {
    return new double[]{e.getX() - ((double) width / 2), e.getY() - ((double) height / 2)};
  }

  @Override
  protected void paintComponent(Graphics g) {

    super.paintComponent(g);

    //clear the hexagons from the previous board state
    drawnObjs.clear();

    //get updated board state
    APlayingBoard<Hexagon> boardState = model.getCurrentBoardState();

    // Recreate boardTiles based on updated board state
    List<Hexagon> tiles = boardState.getBoard();
    for (Hexagon tile : tiles) {
      //default size of 5
      this.drawnObjs.add(this.createObj(tile, 5));
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

    for (Path2DHexagon obj : drawnObjs) {

      //coordinates of that hexagon's center
      double x = this.calculateXY(obj, hexSize)[0];
      double y = this.calculateXY(obj, hexSize)[1];

      obj.setSize(hexSize);

      //overwrite the color of the hexagon if selected
      if (obj.equals(selected)) {
        obj.setColor(selected.getColor());
      }

      AffineTransform hexTransform = new AffineTransform();
      //send the hexagon to the x,y coordinate of its center
      hexTransform.translate(x, y);
      obj.transform(hexTransform);
      //draw the hexagon
      obj.draw(g2d);

      Hexagon tmp = this.createTile(obj);

      // if the tile is occupied, draw a circle in the center of the tile
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

}