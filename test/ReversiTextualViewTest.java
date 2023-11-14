import org.junit.Assert;
import org.junit.Test;

import model.ReversiModel;
import model.StandardHexagonalReversiModel;
import view.ReversiTextualView;
import view.TextualView;

/**
 * These tests ensure the Textual View is correct, for both even and odd board sizes.
 */
public class ReversiTextualViewTest {
  @Test
  public void testNewGameSize4() {
    ReversiModel game1 = new StandardHexagonalReversiModel(4);
    TextualView view1 = new ReversiTextualView(game1);
    String fullBoard = view1.render();
    String[] splitByLine = fullBoard.split("\n");

    Assert.assertEquals(9, splitByLine.length);

    Assert.assertEquals("     _ _ _ _ _     ", splitByLine[0]);
    Assert.assertEquals("    _ _ _ _ _ _   ", splitByLine[1]);
    Assert.assertEquals("   _ _ _ _ _ _ _   ", splitByLine[2]);
    Assert.assertEquals("  _ _ _ X O _ _ _ ", splitByLine[3]);
    Assert.assertEquals(" _ _ _ O _ X _ _ _ ", splitByLine[4]);
    Assert.assertEquals("  _ _ _ X O _ _ _ ", splitByLine[5]);
    Assert.assertEquals("   _ _ _ _ _ _ _   ", splitByLine[6]);
    Assert.assertEquals("    _ _ _ _ _ _   ", splitByLine[7]);
    Assert.assertEquals("     _ _ _ _ _     ", splitByLine[8]);
  }

  @Test
  public void unfinishedGameSize4() {
    ReversiModel game1 = new StandardHexagonalReversiModel(4);
    TextualView view1 = new ReversiTextualView(game1);

    game1.move(game1.getCurrentPlayer(),-2, 1, 1);//white
    game1.move(game1.getCurrentPlayer(),-3, 2, 1);//black
    game1.move(game1.getCurrentPlayer(),-1, -1, 2);//white
    game1.move(game1.getCurrentPlayer(),-1, 2, -1);//black
    game1.move(game1.getCurrentPlayer(),-1, 3, -2);//white
    game1.move(game1.getCurrentPlayer(),1, -2, 1);//black
    game1.move(game1.getCurrentPlayer(),2, -1, -1);//white
    game1.move(game1.getCurrentPlayer(),-1, 4, -3);//black

    String fullBoard = view1.render();
    String[] splitByLine = fullBoard.split("\n");

    Assert.assertEquals(9, splitByLine.length);

    Assert.assertEquals("     _ _ _ _ _     ", splitByLine[0]);
    Assert.assertEquals("    _ _ _ _ _ _   ", splitByLine[1]);
    Assert.assertEquals("   _ _ _ X _ _ _   ", splitByLine[2]);
    Assert.assertEquals("  _ _ O O O O _ _ ", splitByLine[3]);
    Assert.assertEquals(" _ _ _ X _ O _ _ _ ", splitByLine[4]);
    Assert.assertEquals("  _ _ X X O _ _ _ ", splitByLine[5]);
    Assert.assertEquals("   _ X _ X _ _ _   ", splitByLine[6]);
    Assert.assertEquals("    _ _ _ X _ _   ", splitByLine[7]);
    Assert.assertEquals("     _ _ _ X _     ", splitByLine[8]);
  }

  @Test
  public void unfinishedGameSize3() {
    ReversiModel game1 = new StandardHexagonalReversiModel(3);
    TextualView view1 = new ReversiTextualView(game1);

    game1.move(game1.getCurrentPlayer(),-1, -1, 2);//white
    game1.move(game1.getCurrentPlayer(),-1, -2, 3);//black
    game1.move(game1.getCurrentPlayer(),-2, -1, 3);//white
    game1.move(game1.getCurrentPlayer(),1, -2, 1);//black
    game1.move(game1.getCurrentPlayer(),-1, 2, -1);//white
    game1.move(game1.getCurrentPlayer(),-1, 3, -2);//black
    game1.move(game1.getCurrentPlayer(),-2, 1, 1);//white
    game1.move(game1.getCurrentPlayer(),-3, 0, 3);//black
    game1.move(game1.getCurrentPlayer(),2, -3, 1);//white

    String fullBoard = view1.render();
    String[] splitByLine = fullBoard.split("\n");

    Assert.assertEquals(7, splitByLine.length);

    Assert.assertEquals("    _ _ O _   ", splitByLine[0]);
    Assert.assertEquals("   X _ O _ _   ", splitByLine[1]);
    Assert.assertEquals("  X X O X _ _ ", splitByLine[2]);
    Assert.assertEquals(" X _ O _ X _ _ ", splitByLine[3]);
    Assert.assertEquals("  _ O O O _ _ ", splitByLine[4]);
    Assert.assertEquals("   _ _ X _ _   ", splitByLine[5]);
    Assert.assertEquals("    _ _ X _   ", splitByLine[6]);
  }

  @Test
  public void testTextualViewCorrectSize5() {
    ReversiModel model = new StandardHexagonalReversiModel(5);
    TextualView view = new ReversiTextualView(model);
    Assert.assertEquals(view.render(),
        "      _ _ _ _ _ _     \n" +
            "     _ _ _ _ _ _ _     \n" +
            "    _ _ _ _ _ _ _ _   \n" +
            "   _ _ _ _ _ _ _ _ _   \n" +
            "  _ _ _ _ X O _ _ _ _ \n" +
            " _ _ _ _ O _ X _ _ _ _ \n" +
            "  _ _ _ _ X O _ _ _ _ \n" +
            "   _ _ _ _ _ _ _ _ _   \n" +
            "    _ _ _ _ _ _ _ _   \n" +
            "     _ _ _ _ _ _ _     \n" +
            "      _ _ _ _ _ _     ");
  }
}