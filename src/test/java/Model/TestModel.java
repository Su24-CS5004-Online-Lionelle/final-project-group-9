package Model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestModel {

  private Model expectedModel;
  private Model actualModel;
  private Model expectedLoadedRosterModel;
  private Model actualLoadedRosterModel;
  private Player testPlayer1;
  private Player testPlayer2;
  private Player testPlayer3;
  private Player testPlayer4;
  private Player testPlayer5;
  private Player testPlayer6;

  @BeforeEach
  void setUp() {
    // default constructor models
    Model expectedModel = new Model();
    Model actualModel = new Model();

    // models with rosters loaded in
    Model expectedLoadedRosterModel = new Model();
    Model actualLoadedRosterModel = new Model("data/testexport2.json");

    // test player objects.
    Player testPlayer1 = new Player("Seth", "Curry", "G", "6-1", 0, 0, 0,
            "Charlotte Hornets", "East", 5.136, 1.545, 1.0, 0.136, 0.5,
            "14:01", 0.392, 0.903, 0.352);

    Player testPlayer2 = new Player("Luka", "Doncic", "F-G", "6-7", 2018,
            1, 3, "Dallas Mavericks", "West", 33.857, 9.243, 9.786,
            0.543, 1.4, "37:26", 0.487, 0.786, 0.382);

    Player testPlayer3 = new Player("Shai", "Gilgeous-Alexander", "G", "6-6",
            2018, 1, 11, "Oklahoma City Thunder", "West",
            30.053, 5.52, 6.213, 0.893, 1.987, "34:08", 0.535, 0.874, 0.353);

    Player testPlayer4 = new Player("Giannis", "Antetokounmpo", "F", "6-11", 2013,
            1, 15, "Milwaukee Bucks", "East",
            30.438, 11.493, 6.521, 1.082, 1.164, "35:12", 0.611, 0.657, 0.274);

    Player testPlayer5 = new Player("Jalen", "Brunson", "G", "6-2", 2018,
            2, 33, "New York Knicks", "East",
            28.727, 3.597, 6.753, 0.169, 0.922, "35:25", 0.479, 0.847, 0.401);
    Player testPlayer6 = new Player("LeBron", "James", "F", "6-9", 2003,
            1, 1, "Los Angeles Lakers", "West",
            25.603, 7.37, 8.247, 0.548, 1.288, "35:21", 0.535, 0.757, 0.407);
  }

  @Test
  void getRoster() {
    // test that roster will start empty unless user loads a roster file.
    assertEquals(expectedModel.getRoster(), actualModel.getRoster());

    // test that model will load roster from a different file properly
    expectedLoadedRosterModel.getRoster().add(testPlayer1);
    expectedLoadedRosterModel.getRoster().add(testPlayer2);
    expectedLoadedRosterModel.getRoster().add(testPlayer3);
    expectedLoadedRosterModel.getRoster().add(testPlayer4);
    expectedLoadedRosterModel.getRoster().add(testPlayer5);
    expectedLoadedRosterModel.getRoster().add(testPlayer6);
    assertEquals(expectedLoadedRosterModel.getRoster(), actualLoadedRosterModel.getRoster());
  }

  @Test
  void getAllPlayers() {
    // should be the same regardless any situation. NBAROSTER is the master database.
    assertEquals(expectedModel.getAllPlayers(), actualModel.getAllPlayers());
    assertEquals(expectedModel.getAllPlayers(), actualLoadedRosterModel.getAllPlayers());
    assertEquals(expectedLoadedRosterModel.getAllPlayers(), actualLoadedRosterModel.getAllPlayers());
    assertEquals(expectedLoadedRosterModel.getAllPlayers(), actualModel.getAllPlayers());
  }

  @Test
  void getFilePath() {
  }

  @Test
  void setFilePath() {
  }

  @Test
  void testToString() {
  }

  @Test
  void testCreateNBARoster() {

  }

  @Test
  void beanToPlayer() {
  }

  @Test
  void testFilterSortNBARoster() {

  }

  @Test
  void testSetRoster() {

  }

  @Test
  void testBuildRoster() {

  }

  @Test
  void testRemoveFromRoster() {

  }

  @Test
  void createPlayer() {
  }




}