package view;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

import controller.PlayerActionFeatures;
import model.ReadOnlyReversiModel;

/**
 * Represents the graphics view of a game of Reversi, implementing the JFrame.
 */
public class ReversiGraphicsView extends JFrame implements GUIView {

  private final ReadOnlyReversiModel model;
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
    this.model = model;

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
  public void addPlayerActionFeatures(PlayerActionFeatures playerActionFeatures) {
    GUIView frame = this;
    this.addKeyListener(new KeyListener() {
      @Override
      public void keyTyped(KeyEvent e) {
        //must be overridden to implement interface
      }

      @Override
      public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        //playing a move
        if (code == 10) {
          int[] coords = reversiHexagonalPanel.getSelectedHexagon();
          //mutiply every coordinate by -1 since our coordinate system is inverted

          if (coords == null) {
            frame.showErrorMessage("No move selected!");
          } else {
            System.out.println("Play the following move to the cell: " + " Q:" + coords[0]
                    + " R:" + coords[1] + " S:" + coords[2]);
            playerActionFeatures.playMove(coords[0], coords[1], coords[2]);
          }
        }
        //passing as a move
        else if (code == 80) {
          System.out.println("Passing the move!");
          playerActionFeatures.passMove();
        }
        reversiHexagonalPanel.overwriteSelectedHexagon();
      }
      @Override
      public void keyReleased(KeyEvent e) {
        //must be overridden to implement interface
      }
    });

  }

  @Override
  public void showErrorMessage(String message) {
    // Create a modal dialog
    JDialog errorDialog = new JDialog(this, "Error", true);
    errorDialog.setLayout(new FlowLayout()); // Set FlowLayout for proper component ordering
    errorDialog.setSize(300, 100); // You can adjust the size as needed

    // Add a message label
    JLabel errorMessageLabel = new JLabel(message);
    errorDialog.add(errorMessageLabel);

    // Add a close button
    JButton closeButton = new JButton("Close");
    closeButton.addActionListener(e -> errorDialog.dispose());
    errorDialog.add(closeButton);

    // Center the dialog on the parent frame
    errorDialog.setLocationRelativeTo(this);

    // Display the dialog
    errorDialog.setVisible(true);
  }

  @Override
  public void update() {
    this.repaint();
    System.out.println(new ReversiTextualView(this.model).render());
    System.out.println("being updated");
  }

}
