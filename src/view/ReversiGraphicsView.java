package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import model.ReadOnlyReversiModel;

/**
 * Represents the graphics view of a game of Reversi, implementing the JFrame.
 */
public class ReversiGraphicsView extends JFrame implements GUIView, KeyListener {

  //private final ReadOnlyReversiModel model; //the model being passed in
  private final ReversiHexagonalPanel reversiHexagonalPanel; //representing the actual Reversi Board

  /**
   * Constructs the Graphics view of a Game of Reversi.
   *
   * @param model the model being passed in
   */
  public ReversiGraphicsView(ReadOnlyReversiModel model) {
    super();
    if (model == null) {
      throw new IllegalArgumentException("Model can't be null!");
    }
    addKeyListener(this);

    // The initial frame
    this.setSize(900, 900);
    this.setTitle("Reversi!");

    // constructs the panel
    this.reversiHexagonalPanel = new ReversiHexagonalPanel(model);
    this.reversiHexagonalPanel.setPreferredSize(new Dimension(900, 900));
    reversiHexagonalPanel.setBackground(Color.DARK_GRAY);
    this.add(reversiHexagonalPanel);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

  }

  @Override
  public void setVisible() {
    this.setVisible(true);
  }

  @Override
  public void keyTyped(KeyEvent e) {
    //must be overridden to implement interface
  }

  @Override
  public void keyPressed(KeyEvent e) {
    int code = e.getKeyCode();
    if (code == 10) {
      int[] coords = reversiHexagonalPanel.getSelectedHexagon();
      if (coords == null) {
        System.out.println("No move selected!");
      } else {
        System.out.println("Play the following move to the cell: " + " Q:" + coords[0]
            + " R:" + coords[1] + " S:" + coords[2]);
      }
    } else if (code == 80) {
      System.out.println("Pass");
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
    //must be overridden to implement interface
  }
}
