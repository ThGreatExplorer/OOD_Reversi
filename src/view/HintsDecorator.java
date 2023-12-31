package view;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import javax.swing.*;
import model.BoardTile;
import model.ReadOnlyReversiModel;

public class HintsDecorator<U extends APath2D<T>, T extends BoardTile> extends JPanel
        implements ReversiPanel{
  private final AReversiPanel<U, T> decoratedPanel;
  private boolean hints = false;
  private model.Color color;
  private final ReadOnlyReversiModel<T> model;

  public HintsDecorator(AReversiPanel<U, T> decoratedPanel) {
    this.decoratedPanel = decoratedPanel;
    setLayout(new BorderLayout()); // Use BorderLayout or another as needed
    add(this.decoratedPanel, BorderLayout.CENTER); // Add the panel to the center
    this.setOpaque(false);
    this.model = this.decoratedPanel.model;
  }


  @Override
  public void paint(Graphics g) {
    super.paint(g); // This will paint child components first (including ReversiHexagonalPanel)

    if (this.hints) {
      Graphics2D g2d = (Graphics2D) g;
      if (this.decoratedPanel instanceof ReversiHexagonalPanel) {
        // Get the panel size
        double hexSize = Math.min(this.getWidth() / (4.0 * model.getCurrentBoardState().getSize()),
                this.getHeight() / (4.0 * model.getCurrentBoardState().getSize()));

        // Calculate center of the panel
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;// Set the color to black

        AffineTransform originalTransform = g2d.getTransform();

        // Apply global transform
        AffineTransform globalTransform = new AffineTransform();
        globalTransform.translate(centerX, centerY);
        //globalTransform.scale(1, -1); // Flip the y-axis
        g2d.transform(globalTransform);

        int[] selectedCoords = decoratedPanel.getSelected();

        if (selectedCoords != null) {
          //coordinates of that hexagon's center
          double x = this.decoratedPanel.calculateXY(selectedCoords, hexSize)[0];
          double y = this.decoratedPanel.calculateXY(selectedCoords, hexSize)[1];

          g.setFont(new Font("SansSerif", Font.BOLD, (int) hexSize / 2));
          g.setColor(Color.BLACK);
          // Draw the filled rectangle
          g.drawString(String.valueOf(this.getScore()), (int) x,
                  (int) y);

          // Restore the original transform
          g2d.setTransform(originalTransform);
          g2d.dispose();
        }
      }
      else {
        double hexSize = Math.min(this.getWidth() / (model.getCurrentBoardState().getSize()),
                this.getHeight() / (model.getCurrentBoardState().getSize()));

        int[] selectedCoords = decoratedPanel.getSelected();
        if (selectedCoords != null) {
          //coordinates of that hexagon's center
          double x = this.decoratedPanel.calculateXY(selectedCoords, hexSize)[0];
          double y = this.decoratedPanel.calculateXY(selectedCoords, hexSize)[1];

          g.setFont(new Font("SansSerif", Font.BOLD, (int) hexSize / 2));
          g.setColor(Color.BLACK);
          // Draw the filled rectangle
          g.drawString(String.valueOf(this.getScore()), (int) x + (int) hexSize / 3,
                  (int) y + (int) hexSize / 2);
          g2d.dispose();
        }
      }
    }
  }

  public void toggleHints(model.Color color) {
    this.hints = !this.hints;
    this.color = color;
    this.repaint();
  }

  @Override
  public int[] getSelected() {
    return decoratedPanel.getSelected();
  }

  @Override
  public void overwriteSelected() {
    decoratedPanel.overwriteSelected();
  }

  @Override
  public void mouseClicked(MouseEvent e, int width, int height) {
    decoratedPanel.mouseClicked(e, width, height);
    this.repaint();
  }

  private int getScore() {
    int[] selectedCoords = decoratedPanel.getSelected();

    return model.getValidMoveScores(color).getOrDefault(
            this.decoratedPanel.createTile(selectedCoords), 0);
  }
}


