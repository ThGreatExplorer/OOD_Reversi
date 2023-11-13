package model;

/**
 * Enumerates the two colors in the game, white or black.
 */
public enum Color {
  WHITE, BLACK;

  /**
   * Returns the next color from the ordered set of possible player colors.
   *
   * @return the "next" Color
   */
  public Color getNextColor() {

    // Get an array of all the enum values using the built-in 'values()' method
    Color[] values = values();

    // Find the index of the current enum value in the array
    int currentIndex = ordinal();

    // Compute the ordinal of the next value in the array.
    // If this is the last element, wrap around to the first element.
    int nextIndex = (currentIndex + 1) % values.length;

    // Return the enum value with the computed ordinal
    return values[nextIndex];
  }

  /**
   * Converts the enum's Color to a AWT Color for use in the GUI.
   *
   * @return the Color such as Color.WHITE
   */
  public java.awt.Color convertToColor() {
    switch (this) {
      case WHITE:
        return java.awt.Color.WHITE;
      case BLACK:
        return java.awt.Color.BLACK;
      default:
        throw new IllegalArgumentException("Unknown color");
    }
  }

}