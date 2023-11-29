package view;

/**
 * An interface for text-based views that represent the state of a board.
 */
public interface TextualView {

  /**
   * Returns a textual representation of the current state of the board.
   *
   * @return A string that conveys the current state of the board.
   * @throws IllegalStateException if board is not in a valid state, such as having more than 2
   *                               colors represented.
   */
  String render() throws IllegalStateException;
}
