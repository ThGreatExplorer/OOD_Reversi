package view;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.Map;

import javax.swing.*;

import model.Hexagon;
import model.ReadOnlyReversiModel;

public class HintsDecorator extends JPanel implements ReversiPanel{
  ReversiHexagonalPanel decoratedPanel;
  private boolean hints = false;
  private model.Color color;
  private ReadOnlyReversiModel model;

  public HintsDecorator(ReversiHexagonalPanel decoratedPanel) {
    this.decoratedPanel = decoratedPanel;
    setLayout(new BorderLayout()); // Use BorderLayout or another as needed
    add(this.decoratedPanel, BorderLayout.CENTER); // Add the panel to the center
    this.setOpaque(false);
    this.model = this.decoratedPanel.model;
  }


  @Override
  public void paint(Graphics g) {
    super.paint(g); // This will paint child components first (including ReversiHexagonalPanel)

    System.out.println("being repainted");

    if (this.hints) {
      // After child components are painted, draw the rectangle

      // Get the panel size
      double hexSize = Math.min(this.getWidth() / (4.0 * model.getCurrentBoardState().getSize()),
                  this.getHeight() / (4.0 * model.getCurrentBoardState().getSize()));

      // Calculate center of the panel
      int centerX = getWidth() / 2;
      int centerY = getHeight() / 2;// Set the color to black

      Graphics2D g2d = (Graphics2D) g;
      AffineTransform originalTransform = g2d.getTransform();

      // Apply global transform
      AffineTransform globalTransform = new AffineTransform();
      globalTransform.translate(centerX, centerY);
      //globalTransform.scale(1, -1); // Flip the y-axis
      g2d.transform(globalTransform);

      int[] selectedCoords = decoratedPanel.getSelectedHexagon();

      if (selectedCoords != null) {
        //coordinates of that hexagon's center
        double x = hexSize * (Math.sqrt(3) * selectedCoords[0] + Math.sqrt(3) / 2 * selectedCoords[1]);
        double y = hexSize * (3.0 / 2 * selectedCoords[1]);

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
  }

  public void toggleHints(model.Color color) {
    this.hints = !this.hints;
    this.color = color;
    this.repaint();
  }

  @Override
  public int[] getSelectedHexagon() {
    return decoratedPanel.getSelectedHexagon();
  }

  @Override
  public void overwriteSelectedHexagon() {
    decoratedPanel.overwriteSelectedHexagon();
  }

  @Override
  public void mouseClicked(double xCoord, double yCoord) {
    decoratedPanel.mouseClicked(xCoord, yCoord);
    this.repaint();
  }

  private int getScore() {
    int[] selectedCoords = decoratedPanel.getSelectedHexagon();

    int score =model.getValidMoveScores(color).getOrDefault(
            new Hexagon(selectedCoords[0], selectedCoords[1], selectedCoords[2]), 0);
    return score;
  }
}


