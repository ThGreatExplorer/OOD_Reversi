package cs3500.reversi.provider.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import cs3500.reversi.provider.board.HexReversiBoard;
import cs3500.reversi.provider.controller.PlayerFeatures;
import cs3500.reversi.provider.model.ReversiROM;
import cs3500.reversi.provider.utils.TokenStatus;
import cs3500.reversi.provider.utils.HexCoords;

/**
 * Represents our graphical view for a game of Reversi using Java Swing.
 *
 * <p>If you give this view a ReadOnlyModel, and assign a PlayerFeatures interface as a listener
 * this view will display properly.
 *
 * <p>Click on tiles to select them.
 * Reclick on the selected tile to deselect.
 * Click on a new tile to deselect the current one and select the new one.
 * Click enter to place a token on that selected tile.
 * Enter P to pass.
 */
public class ReversiGraphicsView extends JFrame implements RevGUI {

  private JPanel panel;
  private PlayerFeatures feat;
  private ReversiROM model;

  /**
   * Default constructor for our graphical view.
   *
   * @param width      width of the screen
   * @param height     height of the screen
   * @param model      the ROM that you want this view to read the board from.
   */
  public ReversiGraphicsView(int width, int height, ReversiROM model) {
    super();
    this.model = model;
    setTitle("Reversi");
    setSize(width, height);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    panel = new MyHexPanel();
    this.setFocusable(true);
    JScrollPane scrolls = new JScrollPane(panel);
    this.add(scrolls);
    setVisible(true);
  }

  @Override
  public void render() {
    panel.updateUI();
  }

  /**
   * Sets the given PlayerFeatures interface as a listener for high-level events executed by
   * this view. Will call methods on this features interface when propted.
   * @param feat the interface you want to be notified when moves are made.
   */
  @Override
  public void setCommandListener(PlayerFeatures feat) {
    this.feat = feat;
  }

  /**
   * Displays the given message to the player on the board.
   * @param message the message you wish to display.
   */
  @Override
  public void showMessage(String message) {
    JOptionPane.showMessageDialog(panel, message);
  }

  /**
   * Sets this panel as the focus of key and mouse commands. Should be called when the
   * listening controller's turn begins.
   */
  @Override
  public void setFocus() {
    panel.requestFocus();
  }

  /**
   * Removes this panel as the focus of key and mouse commands. Should be called when the
   * listening controller's turn ends.
   */
  @Override
  public void removeFocus() {
    panel.transferFocus();
  }

  /**
   * Gets how much this Panel is offset from the top left corner.
   * @return the point at the center of the panel.
   */
  private Point getOffsetPosn() {
    return this.getLocation();
  }


  private class MyHexPanel extends JPanel {
    private HexCoords hcc;
    private Point pc;

    MyHexPanel() {
      super();
      setBackground(Color.GRAY);
      addMouseListener(new RevMouseListener());
      addKeyListener(new RevKeyListener());
      addFocusListener(new RevFocusListener());
    }

    private class RevFocusListener extends FocusAdapter {
      /**
       * Invoked when a component gains the keyboard focus.
       *
       * @param e the focus event
       */
      @Override
      public void focusGained(FocusEvent e) {
        super.focusGained(e);
      }

      /**
       * Invoked when a component loses the keyboard focus.
       *
       * @param e the focus event
       */
      @Override
      public void focusLost(FocusEvent e) {
        super.focusLost(e);
      }
    }

    private class RevKeyListener extends KeyAdapter {

      @Override
      public void keyPressed(KeyEvent e) {
        if (inFocus()) {
          int keyCode = e.getKeyCode();
          if (keyCode == KeyEvent.VK_P) {
            resetClicked();
            feat.pass();
          } else if (keyCode == KeyEvent.VK_ENTER) {
            if (hcc == null) {
              showMessage("Must select Hex to play");
            } else {
              HexCoords coords = hcc;
              resetClicked();
              feat.placeToken(coords);
            }
          }
        }
      }
    }

    private class RevMouseListener extends MouseAdapter {

      @Override
      public void mouseClicked(MouseEvent e) {
        if (inFocus()) {
          Point clicked = e.getLocationOnScreen();
          //Offsets the clicked location to be registered at the panel level.
          clicked.x = clicked.x - getOffsetPosn().x;
          clicked.y = clicked.y - getOffsetPosn().y;
          //Moves clicked location to be in reference to the center of the panel, not top left.
          clicked.x -= getWidth() / 2;
          clicked.y -= getHeight() / 2;
          pc = clicked;
          render();
        }
      }
    }

    private boolean inFocus() {
      return this.hasFocus();
    }

    private void resetClicked() {
      hcc = null;
    }


    /**
     * Paints the board as give by the ReadOnlyModel based on an axial coordinate system.
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    public void paintComponent(Graphics g) {
      super.paintComponent(g);
      Graphics2D g2d = (Graphics2D) g;
      //Set origin to the center of the screen.
      g2d.translate(this.getWidth() / 2, this.getHeight() / 2);
      g2d.scale(1, 1);
      HexReversiBoard board = model.getBoard();
      int sideLength = model.getSideLength();
      int boardSize = panel.getHeight();
      int hexSize = Math.round(boardSize / (3 * sideLength));
      int apothem = Math.toIntExact(Math.round(Math.sqrt(3)) * hexSize);
      drawBoard(g2d, board);
    }

    private void drawBoard(Graphics2D g2d, HexReversiBoard board) {
      for (HexCoords hc : board.getValidCoords()) {
        Point point = xyOffset(hc);
        Polygon p = makeHex(point);
        g2d.setColor(Color.BLACK);
        g2d.drawPolygon(p);
        //If you clicked on this hexagon.
        if (pc != null && p.contains(pc)) {
          //if you click the same tile as before it deselects.
          if (hcc != null && hcc.q == hc.q && hcc.r == hc.r) {
            pc = null;
            resetClicked();
          }
          //If you click on a new one, it deselects the old one and selects the new one.
          else {
            pc = null;
            hcc = hc;
            g2d.setColor(Color.CYAN);
            g2d.fillPolygon(p);
          }
        }
        //Places token at center of hex.
        g2d.setColor(getColor(board.getPieceAt(hc)));
        g2d.fillOval(point.x - getMiniApothem() / 2, point.y - getMiniApothem() /
                2, getMiniApothem(), getMiniApothem());
      }
    }

    public Polygon makeHex(Point p) {
      int apothem = getMiniApothem();
      int sideL = getMiniHexSL();
      int cX = p.x;
      int cY = p.y;
      int[] xS = {cX, cX + apothem, cX + apothem, cX, cX - apothem, cX - apothem};
      int[] yS = {cY + 2 * sideL, cY + sideL, cY - sideL, cY - 2 * sideL, cY - sideL, cY + sideL};
      return new Polygon(xS, yS, 6);
    }

    /*
     * Translates Hexcoords into their Point on the screen.
     */
    public Point xyOffset(HexCoords hc) {
      Point ans = new Point(0, 0);
      ans.x += hc.q * 2 * getMiniApothem();
      ans.x += hc.r * getMiniApothem();
      ans.y += hc.r * 3 * getMiniHexSL();

      return ans;
    }

    private int getBoardHeight() {
      return panel.getHeight();
    }

    private int getMiniHexSL() {
      return Math.round(getBoardHeight() / (6 * model.getSideLength()));
    }

    private int getMiniApothem() {
      return Math.toIntExact(panel.getWidth() /
              (2 * (2 * model.getSideLength() - 1)));
    }


    private Color getColor(TokenStatus color) {
      switch (color) {
        case BLACK:
          return Color.BLACK;
        case WHITE:
          return Color.WHITE;
        case EMPTY:
          return new Color(0, 0, 0, 0);
        default:
          throw new IllegalArgumentException("Bad Color");
      }
    }
  }
}
