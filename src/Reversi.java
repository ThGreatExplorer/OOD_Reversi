import controller.Controller;
import model.Color;
import model.ReversiModel;
import model.StandardHexagonalReversiModel;
import player.AIPlayer;
import player.CaptureMostPiecesStrategy;
import player.CompleteStrategyFromFallible;
import player.HumanPlayer;
import player.InfalliblePlayerStrategies;
import player.Player;
import view.GUIView;
import view.ReversiGraphicsView;

/**
 * The main class that executes a game of Reversi.
 */
public final class Reversi {
  /**
   * currentIndex guides which command in the arguments is making the next customization of the
   * players.
   */
  private static int currentIndex;

  /**
   * Creates a model and view to run the game with the selected players.
   *
   * @param args String commands, with spaces in between, to pick the player and, if computer
   *             player, strategy.
   *             There must be two players picked.
   *             First word is the first player, either "human" or "computer".
   *             If picked "computer" as player 1, then second word must be the strategy.
   *             Supported strategies: "CaptureMostPieces"
   *             The next word will be the second player, and if the computer player is picked,
   *             there must be a next word for the strategy.
   */

  public static void main(String[] args) {
    currentIndex = 0;

    ReversiModel model = new StandardHexagonalReversiModel(8);
    GUIView view1 = new ReversiGraphicsView(model);
    GUIView view2 = new ReversiGraphicsView(model);

    Player player1;
    Player player2;

    //If no input, set default as human-human
    if (args.length == 0) {
      player1 = new HumanPlayer(Color.WHITE);
      player2 = new HumanPlayer(Color.BLACK);
    } else {
      //Player human1 = new HumanPlayer(Color.WHITE, model);
      player1 = getPlayer(Color.WHITE, model, args);
      player2 = getPlayer(Color.BLACK, model, args);
    }

    Controller controller1 = new Controller(model, view1, player1);
    Controller controller2 = new Controller(model, view2, player2);
    view1.setVisible();
    view2.setVisible();
    model.startGame();

  }

  private static String getCommand(String[] args) {
    String next = args[currentIndex];
    currentIndex++;
    return next;
  }

  private static Player getPlayer(Color color, ReversiModel model, String[] args) {
    String player = getCommand(args);
    switch (player) {
      case "human":
        return new HumanPlayer(color);
      case "computer":
        return new AIPlayer(model, color, getStrategy(args));
      default:
        throw new IllegalArgumentException("Must pick 'computer' or 'human' as next player");
    }
  }

  private static InfalliblePlayerStrategies getStrategy(String[] args) {
    String strategy = getCommand(args);
    if (strategy.equals("CaptureMostPieces")) {
      return new CompleteStrategyFromFallible(new CaptureMostPiecesStrategy());
    }
    throw new IllegalArgumentException("Must pick supported strategy");
  }


}