package cs3500.reversi.provider.view;

import cs3500.reversi.provider.controller.PlayerFeatures;

/**
 * A implementation of the view that does NOTHING.
 * This is to ensure when using MinMax, extra views do not pop up.
 */
public class FakeView implements RevGUI {

  @Override
  public void render() {
    //Does nothing on purpose.
  }

  @Override
  public void setCommandListener(PlayerFeatures feat) {
    //Does nothing on purpose.
  }

  @Override
  public void showMessage(String message) {
    //Does nothing on purpose.
  }

  @Override
  public void setFocus() {
    //Does nothing on purpose.
  }

  @Override
  public void removeFocus() {
    //Does nothing on purpose.
  }
}
