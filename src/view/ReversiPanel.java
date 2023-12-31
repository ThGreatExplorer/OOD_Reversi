package view;

import java.awt.event.MouseEvent;

/**
 * Implements the JPanel representing a panel in the GUI for a game of hexagonal reversi by creating
 * a hexagonal panel representing the board. Any operations that update the GUIView, specially
 * changing the board state <b>MUST ALSO BE CALLED HERE</b>.
 */
public interface ReversiPanel {


  /**
   * Communicates what hexagon is selected to GUIView.
   *
   * @return array of coordinates
   */
  int[] getSelected();

  /**
   * After a hexagon is selected, deselects it.
   */
  void overwriteSelected();

  /**
   * Communicates the mouse click to the Panel which then internally handles the logic of that
   * click as selecting, deselecting, or doing nothing to the tiles.
   *
   * @param e the mouseEvent
   * @param width the width
   * @param height the height
   */
  void mouseClicked(MouseEvent e, int width, int height);
}
