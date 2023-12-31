package view;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

import controller.PlayerActionFeatures;
import model.BoardTile;
import model.ReadOnlyReversiModel;

/**
 * Represents the graphics view of a game of Reversi, implementing the JFrame.
 */
public class ReversiGraphicsView<T extends BoardTile, U extends APath2D<T>,
        S extends AReversiPanel<U,T>> extends JFrame implements GUIView<T> {

  private final ReadOnlyReversiModel<T> model;
  private final HintsDecorator<U,T> reversiPanel;

  /**
   * Constructs the Graphics view of a Game of Reversi.
   *
   * @param model the model being passed in
   */
  public ReversiGraphicsView(ReadOnlyReversiModel<T> model, S reversiPanel) {
    super();
    if (model == null) {
      throw new IllegalArgumentException("Model can't be null!");
    }
    this.model = model;

    // The initial frame
    this.setSize(900, 900);
    this.setTitle("Reversi!");

    reversiPanel.setBackground(Color.DARK_GRAY);
    this.reversiPanel = new HintsDecorator<>(reversiPanel);
    this.reversiPanel.setPreferredSize(new Dimension(900,900));
    this.reversiPanel.revalidate();
    this.add(this.reversiPanel);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  @Override
  public void setVisible() {
    this.setVisible(true);
  }

  @Override
  public void addPlayerActionFeatures(PlayerActionFeatures<T> playerActionFeatures) {
    GUIView<T> frame = this;

    this.addMouseListener(new MouseListener() {
      @Override
      public void mouseClicked(MouseEvent e) {
        reversiPanel.mouseClicked(e, getWidth(), getHeight());
      }

      @Override
      public void mousePressed(MouseEvent e) {
        //must be overridden to implement interface
      }

      @Override
      public void mouseReleased(MouseEvent e) {
        //must be overridden to implement interface
      }

      @Override
      public void mouseEntered(MouseEvent e) {
        //must be overridden to implement interface
      }

      @Override
      public void mouseExited(MouseEvent e) {
        //must be overridden to implement interface
      }
    });

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
          //int[] coords = reversiHexagonalPanel.getSelectedHexagon();
          //mutiply every coordinate by -1 since our coordinate system is inverted
          int[] coords = reversiPanel.getSelected();

          if (coords == null) {
            frame.showErrorMessage("No move selected!");
          } else {
            //System.out.println("Play the following move to the cell: " + " Q:" + coords[0]
            //    + " R:" + coords[1] + " S:" + coords[2]);
            playerActionFeatures.playMove(coords);
          }
          reversiPanel.overwriteSelected();
        }
        //passing as a move
        else if (code == 80) {
          System.out.println("Passing the move!");
          playerActionFeatures.passMove();
          reversiPanel.overwriteSelected();
        }
        else if (code == 72) {
          reversiPanel.toggleHints(playerActionFeatures.getColor());
        }
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
  public void update(model.Color color) {
    if (model.getCurrentPlayer() == color) {
      this.setTitle("Reversi: " + color + " : Your Turn");
    } else {
      this.setTitle("Reversi: " + color + " : Not Your Turn");
    }
    this.repaint();
  }

  @Override
  public void gameOver(model.Color winner, model.Color playerColor) {
    if (!model.isGameOver()) {
      throw new IllegalArgumentException("Game isn't over!");
    }
    String message;
    if (winner == playerColor) {
      message = "Reversi: " + playerColor + " : You Win!\n Score: " + model.getScore(playerColor)
          + " - " + model.getScore(playerColor.getNextColor()) + "\n";
    } else if (winner == null) {
      message = "Reversi: Tie Game!\n Score: " + model.getScore(playerColor)
          + " - " + model.getScore(playerColor.getNextColor()) + "\n";
    } else {
      message = "Reversi: " + playerColor + " : You Lose!\n Score: " + model.getScore(playerColor)
          + " - " + model.getScore(playerColor.getNextColor()) + "\n";
    }

    // Create the overlay panel and use it as the glass pane to intercept user input and display
    // the end of the game message
    GameOverOverlay overlay = new GameOverOverlay(message);
    setGlassPane(overlay);
    revalidate();
    overlay.setVisible(true);

    // Update the title to show the game over message
    this.setTitle(message);

    // Disable key listeners to prevent further interaction
    KeyListener[] keyListeners = getKeyListeners();
    for (KeyListener listener : keyListeners) {
      removeKeyListener(listener);
    }

    //disable mouse listeners to prevent further interaction
    MouseListener[] mouseListeners = getMouseListeners();
    for(MouseListener listener : mouseListeners) {
      removeMouseListener(listener);
    }
  }

  /**
   * Represents the overlay that is displayed when the game is over. This class is used as the
   * glass pane of the JFrame to intercept user input and display the end of the game message.
   */
  private static class GameOverOverlay extends JPanel {

    private final String message;

    /**
     * Constructs a GameOverOverlay with the given message.
     *
     * @param message the message to display
     */
    protected GameOverOverlay(String message) {
      this.message = message;
      setOpaque(false); // Make panel transparent
      setLayout(new GridBagLayout()); // Center the message
    }

    @Override
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);

      // set a to 0 for transparency
      g.setColor(new Color(0, 0, 0, 0));
      g.fillRect(0, 0, getWidth(), getHeight());

      // Set the message color and font
      g.setColor(Color.YELLOW);
      g.setFont(new Font("Arial", Font.BOLD, getWidth() / 25));

      // Calculate the position to center the message
      FontMetrics fm = g.getFontMetrics();
      int x = (getWidth() - fm.stringWidth(message)) / 2;
      int y = ((getHeight() - fm.getHeight()) / 2) + fm.getAscent();

      // Draw the message
      g.drawString(message, x, y);
    }
  }

}
