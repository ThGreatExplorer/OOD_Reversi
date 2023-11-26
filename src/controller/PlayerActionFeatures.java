package controller;

/**
 * Represents the features the controller should be listening from that are coming from the View.
 */
public interface PlayerActionFeatures {

  /**
   * Only
   */
  void makeMove();

  void playMove(int q, int r, int s);

  void passMove();

}
