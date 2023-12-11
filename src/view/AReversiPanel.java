package view;

import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import model.BoardTile;
import model.ReadOnlyReversiModel;

public abstract class AReversiPanel<U extends APath2D<T>, T extends BoardTile> extends JPanel
        implements ReversiPanel {

  protected final ReadOnlyReversiModel<T> model;
  protected final List<U> drawnObjs; //tracks the state of the Path2DHexagons being
  protected U selected; //initialized as null, stores the most recently selected
  //hexagon

  public AReversiPanel(ReadOnlyReversiModel<T> model) {
    if (model == null) {
      throw new IllegalArgumentException("Model is null!");
    }
    this.model = model;
    this.drawnObjs = new ArrayList<>();
    this.selected = null;
  }

  @Override
  public abstract int[] getSelected();

  protected abstract U createObj(T tile, int size);

  protected abstract T createTile(int[] coordinates);

  protected abstract T createTile(U obj);

  protected abstract double[] calculateXY(U path2dObj, double hexSize);

  protected abstract double[] calculateXY(int[] coordinates, double hexSize);

  protected abstract double[] mouseToXY(MouseEvent e, int width, int height);

  @Override
  public void overwriteSelected() {
    this.selected = null;
    this.repaint();
  }

  @Override
  public void mouseClicked(MouseEvent e, int width, int height) {
    double xCoord = this.mouseToXY(e, width, height)[0];
    double yCoord =  this.mouseToXY(e, width, height)[1];

    boolean found = false;

    for (U obj : drawnObjs) {
      if (obj.contains(new Point2D.Double(xCoord, yCoord))) {
        //System.out.println("Clicked Q:" + dHexagon.q + " R:" + dHexagon.r + " S:" + dHexagon.s);
        if (this.selected != null) {
          if (this.selected == obj) {
            this.selected.select();
            this.selected = null;
            break;
          } else {
            this.selected.select(); // deselects the current hexagon
            this.selected = obj;
            obj.select();
            found = true;
            break;
          }
        } else {
          this.selected = obj;
          obj.select();
          found = true;
          break;
        }
      }
    }

    // If clicked outside, deselect the current hexagon
    if (!found && this.selected != null) {
      this.selected.select();
      this.selected = null;
    }

    repaint();
  }
}
