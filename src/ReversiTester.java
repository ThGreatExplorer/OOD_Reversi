import controller.Controller;
import model.Color;
import model.Hexagon;
import model.ReversiModel;
import model.Square;
import model.StandardHexagonalReversiModel;
import model.StandardSquareReversiModel;
import player.AIPlayer;
import player.CaptureMostPiecesStrategy;
import player.CompleteStrategyFromFallible;
import player.HumanPlayer;
import player.InfalliblePlayerStrategies;
import player.Player;
import view.GUIView;
import view.ReversiGraphicsView;
import view.ReversiHexagonalPanel;
import view.SquareReversiPanel;

/**
 * This class is used to test the Reversi game using a preset configuration of our implementation
 * as color white and human and their implementation as color black and AI with the highest score
 * strategy.
 */
public class ReversiTester {

  /**
   * currentIndex guides which command in the arguments is making the next customization of the
   * players.
   */
  private static int currentIndex;

  /**
   * Creates a model and view to run the game with the selected players.
   *
   * @param args String commands, with spaces in between, to pick the size, tile type, player and,
   *             if computer player, strategy.
   *             <p></p>
   *             There must be two players picked.
   *             <p></p>
   *             The first word is the size. The second is the board type either "square" or
   *             "hexagon".
   *             <p>
   *             There must be at least 2 words, if no extra words are given, we presume it is
   *             human and human player.
   *             </p>
   *             Third word is the first player, either "human" or "computer".
   *             If picked "computer" as player 1, then second word must be the strategy.
   *             Supported strategies for player 1: "CaptureMostPieces"
   *             The next word will be the second player, and if the computer player is picked,
   *             there must be a next word for the strategy.
   *             Supported strategies for player 2: "CaptureMostPieces",
   *             "Strategy1": GetHighestScore, "Strategy2": AvoidNextToCorner,
   *             "Strategy3": GetCorner, "Strategy4": MinMax
   */
  public static void main(String[] args) {
    currentIndex = 0;

    if (args.length < 2) {
      throw new IllegalArgumentException("need at least 2 inputs!");
    }
    int size = Integer.parseInt(args[0]);

    switch (args[1]) {
      case "square":
        ReversiModel<Square> modelA = new StandardSquareReversiModel(size);
        GUIView<Square> viewA1 = new ReversiGraphicsView<>(modelA, new SquareReversiPanel(modelA));
        GUIView<Square> viewA2 = new ReversiGraphicsView<>(modelA, new SquareReversiPanel(modelA));

        currentIndex = 2;

        Player A1 = getPlayer(Color.WHITE, modelA, args);
        Player A2 = getPlayer(Color.BLACK, modelA, args);

        Controller controllerA1 = new Controller(modelA, viewA1, A1);
        Controller controllerA2 = new Controller(modelA, viewA2, A2);

        viewA1.setVisible();
        viewA2.setVisible();
        modelA.startGame();
        break;
      case "hexagon":
        ReversiModel<Hexagon> modelB = new StandardHexagonalReversiModel(size);
        GUIView<Hexagon> viewB1 =
            new ReversiGraphicsView<>(modelB, new ReversiHexagonalPanel(modelB));
        GUIView<Hexagon> viewB2 =
            new ReversiGraphicsView<>(modelB, new ReversiHexagonalPanel(modelB));

        currentIndex = 2;

        Player B1 = getPlayer(Color.WHITE, modelB, args);
        Player B2 = getPlayer(Color.BLACK, modelB, args);

        Controller controllerB1 = new Controller(modelB, viewB1, B1);
        Controller controllerB2 = new Controller(modelB, viewB2, B2);

        viewB1.setVisible();
        viewB2.setVisible();
        modelB.startGame();
        break;
      default:
        throw new IllegalArgumentException("Unsupported tile type!");
    }
  }

  private static String getCommand(String[] args) {
    String next = args[currentIndex];
    currentIndex++;
    return next;
  }

  private static Player getPlayer(Color color, ReversiModel<?> model, String[] args) {
    String player = getCommand(args);
    switch (player) {
      case "human":
        return new HumanPlayer(color);
      case "computer":
        return new AIPlayer(model, color, getStrategy(args, color));
      default:
        throw new IllegalArgumentException("Must pick 'computer' or 'human' as next player");
    }
  }

  private static InfalliblePlayerStrategies getStrategy(String[] args, Color color) {
    String strategy = getCommand(args);
    if (strategy.equals("CaptureMostPieces")) {
      return new CompleteStrategyFromFallible(new CaptureMostPiecesStrategy());
    }

    throw new IllegalArgumentException("Must pick supported strategy");
  }
}
