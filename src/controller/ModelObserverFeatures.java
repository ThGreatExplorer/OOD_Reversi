package controller;

import model.Color;

/**
 * Represents an observer that is subscribed to updates from the model. Functions as a delegate in
 * the controller to the model, and is responsible for updating the view when the model changes.
 */
public interface ModelObserverFeatures {


  /**
   * Tells the view to update once the model state has changed.
   */
  void update();


}
