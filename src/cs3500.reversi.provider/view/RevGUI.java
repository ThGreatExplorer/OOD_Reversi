package cs3500.reversi.provider.view;

/**
 * The extension of Reversi's view system specified for a GUI.
 */
public interface RevGUI extends RevView {

  /**
   * Sets this panel as the focus of key and mouse commands. Should be called when the
   * listening controller's turn begins.
   */
  void setFocus();

  /**
   * Removes this panel as the focus of key and mouse commands. Should be called when the
   * listening controller's turn ends.
   */
  void removeFocus();
}
