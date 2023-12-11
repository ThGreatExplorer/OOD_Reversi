package view;

import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.util.Arrays;
import java.util.List;

import model.APlayingBoard;
import model.Color;
import model.ReadOnlyReversiModel;
import model.Square;

public class SquareReversiPanel extends AReversiPanel<Path2DSquare, Square> {

  public SquareReversiPanel(ReadOnlyReversiModel<Square> model) {
    super(model);
  }

  @Override
  protected double[] mouseToXY(MouseEvent e, int width, int height) {
    return new double[]{e.getX(), e.getY()};
  }

  @Override
  public int[] getSelected() {
    if (this.selected == null) {
      return null;
    }
    return new int[]{this.selected.row, this.selected.col};
  }

  @Override
  protected Path2DSquare createObj(Square tile, int size) {
    return new Path2DSquare(tile.getRow(), tile.getCol(), size);
  }

  @Override
  protected Square createTile(int[] coordinates) {
    if (coordinates.length != 2) {
      throw new IllegalArgumentException("coordinates must be row, col, not: "
          + Arrays.toString(coordinates));
    }
    return new Square(coordinates[0], coordinates[1]);
  }

  @Override
  protected Square createTile(Path2DSquare obj) {
    return new Square(obj.row, obj.col);
  }

  @Override
  protected double[] calculateXY(Path2DSquare path2dObj, double hexSize) {
    double x = hexSize * path2dObj.col;
    double y = hexSize * path2dObj.row;
    return new double[]{x, y};
  }

  @Override
  protected double[] calculateXY(int[] coordinates, double hexSize) {
    if (coordinates.length != 2) {
      throw new IllegalArgumentException("coordinates must be row, col, not: "
          + Arrays.toString(coordinates));
    }
    double x = hexSize * coordinates[1];
    double y = hexSize * coordinates[0];
    return new double[]{x, y};
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    //clear the objects from the previous board state
    drawnObjs.clear();

    //get updated board state
    APlayingBoard<Square> boardState = model.getCurrentBoardState();

    // Recreate boardTiles based on updated board state
    List<Square> tiles = boardState.getBoard();
    for (Square tile : tiles) {
      // System.out.println(tile);
      //default size of 5
      this.drawnObjs.add(this.createObj(tile, 5));
    }

    //rewrite the size of the square before drawing it to have the square dynamically resize
    //when the screen is resized
    double hexSize = Math.min(this.getWidth() / (boardState.getSize()),
        this.getHeight() / (boardState.getSize()));

    Graphics2D g2d = (Graphics2D) g;

    for (Path2DSquare obj : drawnObjs) {

      //coordinates of that square's center
      double x = this.calculateXY(obj, hexSize)[0];
      double y = this.calculateXY(obj, hexSize)[1];

      obj.setSize(hexSize);

      //overwrite the color of the square if selected
      if (obj.equals(selected)) {
        obj.setColor(selected.getColor());
      }

      //draw the square
      obj.draw(g2d);

      Square tmp = this.createTile(obj);

      // if the tile is occupied, draw a circle in the center of the square
      if (boardState.isOccupiedTile(tmp)) {
        Color color = boardState.whoOccupiesThisTile(tmp);
        Ellipse2D.Double circle =
            new Ellipse2D.Double(x + hexSize / 4, y + hexSize / 4,
                hexSize / 2, hexSize / 2);
        g2d.setColor(color.convertToColor());
        g2d.fill(circle);
        g2d.draw(circle);
      }
    }
    g2d.dispose();

  }
}
