package view;

import java.awt.event.MouseEvent;

/**
 * Implements the JPanel representing a panel in the GUI for a game of hexagonal reversi by creating
 * a hexagonal panel representing the board. Any operations that update the GUIView, specially
 * changing the board state <b>MUST ALSO BE CALLED HERE</b>.
 */
public interface ReversiPanel {

  /*
  For next part of assignment:
  Implement logic for the view to handle being updated by the controller. Should be called in
  parallel with any operations on GUIView, since if the JFrame is being updated so should the panel
  which is displaying the actual board... (for relevant moves)
   */
  /**
   * TODO Communicates what hexagon is selected to GUIView, really a placeholder method for now.
   *
   * @return array of coordinates
   */
  int[] getSelected();

  /**
   * TODO After a hexagon is selected, deselects it, really a placeholder method for now.
   */
  void overwriteSelected();

  void mouseClicked(MouseEvent e, int width, int height);
}
