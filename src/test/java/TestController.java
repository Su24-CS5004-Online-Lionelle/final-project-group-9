import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Controller.Controller;
import Model.Player;
import Model.SortFilter.ColumnData;

import java.awt.event.ActionEvent;
import java.util.HashSet;
import java.util.Set;


class TestController {
  private Controller controller;
  private ViewStub viewStub;
  private ModelStub modelStub;
  private ModelStub modelStub2;
  private Set<Player> testSet;

  @BeforeEach
  void setUp() throws Exception {
    
    viewStub = new ViewStub();
    modelStub = new ModelStub();
    modelStub2 = new ModelStub();
    controller = new Controller(viewStub, modelStub);
  }

  /**
   * Tests the search action in the controller class.
   */
  @Test
  void testSearchMethod() {
    viewStub.setInput("Kyrie", ColumnData.FIRST_NAME, true);
    Player player1 = new Player("Kyrie", "Irving", "G", "6-2", 2011, 
                                1, 1, "Dallas Mavericks", "West", 25.638, 5.017, 
                                5.155, 0.483, 1.276, "34:56", 0.497, 0.905, 0.411);
    modelStub.filterSortNBARoster("Kyrie", ColumnData.FIRST_NAME, true).add(player1);

    controller.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "search"));

    String expectedOutput = "1. Kyrie Irving\n";
    assertEquals(expectedOutput, viewStub.getDisplayContent().toString());
  }

  /**
   * Tests the add action in the controller class.
   */
  @Test
  void testAddMethod() {
    Set<Player> players = new HashSet<>();
    Player player1 = new Player("Kyrie", "Irving", "G", "6-2", 2011, 
                                1, 1, "Dallas Mavericks", "West", 25.638, 5.017, 
                                5.155, 0.483, 1.276, "34:56", 0.497, 0.905, 0.411);
    players.add(player1);
    modelStub.buildRoster(players, "Kyrie");

    viewStub.setInput("Kyrie", ColumnData.FIRST_NAME, true);

    controller.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "add"));

    assertTrue(modelStub.getRoster().contains(player1));
  }

  /**
   * Tests the remove action in the controller class.
   */
  @Test
  void testRemoveMethod() {
    Player player1 = new Player("Kyrie", "Irving", "G", "6-2", 2011, 
                                1, 1, "Dallas Mavericks", "West", 25.638, 5.017, 
                                5.155, 0.483, 1.276, "34:56", 0.497, 0.905, 0.411);
    modelStub.getRoster().add(player1);

    viewStub.setInput("Kyrie", ColumnData.FIRST_NAME, true);

    controller.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "remove"));

    assertFalse(modelStub.getRoster().contains(player1));
  }

  /**
   * Tests the show roster action in the controller class.
   */
  @Test
  void testShowRosterMethod() {
    Player player1 = new Player("Kyrie", "Irving", "G", "6-2", 2011, 
                                1, 1, "Dallas Mavericks", "West", 25.638, 5.017, 
                                5.155, 0.483, 1.276, "34:56", 0.497, 0.905, 0.411);
    modelStub.getRoster().add(player1);
    
    controller.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "showRoster"));
    String expectedOutput = "1. Kyrie Irving\n";
    assertEquals(expectedOutput, viewStub.getDisplayContent().toString());
  }

  /**
   * Tests the clear action in the controller class.
   */
  @Test
  void testClearMethod() {
    viewStub.display("Something");

    controller.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "clear"));

    String expectedOutput = "";
    assertEquals(expectedOutput, viewStub.getDisplayContent().toString());
  }

  /**
   * Tests the help action in the controller class.
   */
  @Test
  void testHelpMethod() {
    controller.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "help"));

    String expectedOutput = viewStub.getHelp();
    assertEquals(expectedOutput, viewStub.getDisplayContent().toString());
  }

  /**
   * Tests the getter method for the model in the controller class.
   */
  @Test
  void testGetModel() {
    assertEquals(modelStub, controller.getModel());;
  }

  /**
   * Tests the setter method for the model in the controller class.
   */
  @Test
  void testSetModel() {
    controller.setModel(modelStub2);

    assertEquals(modelStub2, controller.getModel());
  }

  /**
   * Tests the getter and setter methods for the filtered sorted set in the controller class.
   */
  @Test
  void testSetAndGetFilteredSortedSet() {
    Player player1 = new Player("Kyrie", "Irving", "G", "6-2", 2011, 
            1, 1, "Dallas Mavericks", "West", 25.638, 5.017, 
            5.155, 0.483, 1.276, "34:56", 0.497, 0.905, 0.411);
    
    Player player2 = new Player("LeBron", "James", "F", "6-9", 2003, 
            1, 1, "Los Angeles Lakers", "West", 25.603, 7.37, 
            8.247, 0.548, 1.288, "35:21", 0.535, 0.757, 0.407);

    testSet = new HashSet<>();
    testSet.add(player1);
    testSet.add(player2);

    controller.setFilterSortedSet(testSet);
    
    Set<Player> retrievedSet = controller.getFilterSortedSet();
    assertEquals(testSet, retrievedSet);
  }
}