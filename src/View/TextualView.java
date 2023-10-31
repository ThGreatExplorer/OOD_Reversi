package View;

/**
 * An interface for text-based views that represent the state of a board.
 */
public interface TextualView {

  /**
   * Returns a textual representation of the current state of the board.
   *
   * @return A string that conveys the current state of the board.
   */
  String render();
}
