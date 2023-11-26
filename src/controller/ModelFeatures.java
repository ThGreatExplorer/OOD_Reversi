package controller;

import model.Color;

/**
 * Represents the features that the controller should be listening for that are coming from the
 * model.
 */
public interface ModelFeatures {

  void update();

  Color getcurrentPlayerColor();

  void makeMove();

}
