package view;

import java.awt.*;

import javax.swing.*;

import model.ReadOnlyReversiModel;

public class ReversiGraphicsView extends JFrame implements GUIView {

  private final ReadOnlyReversiModel model;
  private final ReversiHexagonalPanel reversiHexagonalPanel;


  public ReversiGraphicsView(ReadOnlyReversiModel model) {
    super();
    if (model == null) {
      throw new IllegalArgumentException("Model can't be null!");
    }
    this.model = model;
    // The inital frame
    this.setSize(900, 900);
    this.setTitle("Reversi!");

    // constructs the panel
    this.reversiHexagonalPanel = new ReversiHexagonalPanel(model, 40);
    this.reversiHexagonalPanel.setPreferredSize(new Dimension(900, 900));
    reversiHexagonalPanel.setBackground(Color.DARK_GRAY);
    this.add(reversiHexagonalPanel);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

  }

  @Override
  public void setVisible() {
    this.setVisible(true);
  }


}
