import static org.junit.jupiter.api.Assertions.*;
import Model.Model;
import Model.Player;
import Model.PlayerBean;
import Model.SortFilter.ColumnData;
import Model.SortFilter.PlayerSortStrategy;
import org.junit.jupiter.api.Test;

import java.util.*;

class TestModel {

  private Model expectedModel = new Model();

  private Model actualModel = new Model();

  private Model expectedLoadedRosterModel = new Model();

  private Model actualLoadedRosterModel = new Model("data/testexport2.json");

  private Player testPlayer1  = new Player("Seth", "Curry", "G", "6-1", 0, 0, 0,
          "Charlotte Hornets", "East", 5.136, 1.545, 1.0, 0.136, 0.5,
          "14:01", 0.392, 0.903, 0.352);

  private Player testPlayer2 = new Player("Luka", "Doncic", "F-G", "6-7", 2018,
          1, 3, "Dallas Mavericks", "West", 33.857, 9.243, 9.786,
          0.543, 1.4, "37:26", 0.487, 0.786, 0.382);

  private Player testPlayer3 = new Player("Shai", "Gilgeous-Alexander", "G", "6-6",
          2018, 1, 11, "Oklahoma City Thunder", "West",
          30.053, 5.52, 6.213, 0.893, 1.987, "34:08", 0.535, 0.874, 0.353);

  private Player testPlayer4 = new Player("Giannis", "Antetokounmpo", "F", "6-11", 2013,
          1, 15, "Milwaukee Bucks", "East",
          30.438, 11.493, 6.521, 1.082, 1.164, "35:12", 0.611, 0.657, 0.274);

  private Player testPlayer5 = new Player("Jalen", "Brunson", "G", "6-2", 2018,
          2, 33, "New York Knicks", "East",
          28.727, 3.597, 6.753, 0.169, 0.922, "35:25", 0.479, 0.847, 0.401);

  private Player testPlayer6 = new Player("LeBron", "James", "F", "6-9", 2003,
          1, 1, "Los Angeles Lakers", "West",
          25.603, 7.37, 8.247, 0.548, 1.288, "35:21", 0.535, 0.757, 0.407);

  private Player testPlayer7 = new Player("Grayson", "Allen", "G", "6-4",
          2018, 1, 21, "Pheonix Suns", "West",
          13.52, 3.92, 3.027, 0.587, 0.92, "33:27", 0.499, 0.878, 0.461);

  private String testPlayer1ToString = actualModel.toString(testPlayer1);

  private String testPlayer2ToString = actualModel.toString(testPlayer2);

  private String testPlayer3ToString = actualModel.toString(testPlayer3);

  private PlayerBean testPlayerBean1 = new PlayerBean("Seth", "Curry", "G", "6-1", 0, 0, 0,
          "Charlotte Hornets", "East", 5.136, 1.545, 1.0, 0.136, 0.5,
          "14:01", 0.392, 0.903, 0.352);

  private PlayerBean testPlayerBean2 = new PlayerBean("Luka", "Doncic", "F-G", "6-7", 2018,
          1, 3, "Dallas Mavericks", "West", 33.857, 9.243, 9.786,
          0.543, 1.4, "37:26", 0.487, 0.786, 0.382);

  private PlayerBean testPlayerBean3 = new PlayerBean("Shai", "Gilgeous-Alexander", "G", "6-6",
          2018, 1, 11, "Oklahoma City Thunder", "West",
          30.053, 5.52, 6.213, 0.893, 1.987, "34:08", 0.535, 0.874, 0.353);

  private PlayerBean testPlayerBean4 = new PlayerBean("Giannis", "Antetokounmpo", "F", "6-11", 2013,
          1, 15, "Milwaukee Bucks", "East",
          30.438, 11.493, 6.521, 1.082, 1.164, "35:12", 0.611, 0.657, 0.274);

  private PlayerBean testPlayerBean5 = new PlayerBean("Jalen", "Brunson", "G", "6-2", 2018,
          2, 33, "New York Knicks", "East",
          28.727, 3.597, 6.753, 0.169, 0.922, "35:25", 0.479, 0.847, 0.401);

  private PlayerBean testPlayerBean6 = new PlayerBean("LeBron", "James", "F", "6-9", 2003,
          1, 1, "Los Angeles Lakers", "West",
          25.603, 7.37, 8.247, 0.548, 1.288, "35:21", 0.535, 0.757, 0.407);



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
    assertEquals(expectedModel.getAllPlayers().size(), actualModel.getAllPlayers().size());
    assertEquals(expectedModel.getAllPlayers().size(), actualLoadedRosterModel.getAllPlayers().size());
    assertEquals(expectedLoadedRosterModel.getAllPlayers().size(), actualLoadedRosterModel.getAllPlayers().size());
    assertEquals(expectedLoadedRosterModel.getAllPlayers().size(), actualModel.getAllPlayers().size());
  }

  @Test
  void getFilePath() {
    // file path will always be the master database.
    assertEquals("data/database.json", actualModel.getFilePath());
  }

  @Test
  void setFilePath() {
    actualModel.setFilePath("testing setFilePath");
    assertEquals("testing setFilePath", actualModel.getFilePath());
  }

  @Test
  void testToString() {
    String actual1 = actualModel.toString(testPlayer1);
    String actual2 = actualModel.toString(testPlayer2);
    String actual3 = actualModel.toString(testPlayer3);

    assertEquals(testPlayer1ToString, actual1);
    assertEquals(testPlayer2ToString, actual2);
    assertEquals(testPlayer3ToString, actual3);
  }

  @Test
  void testCreateNBARoster() {
    // Like getAllPlayers, NBAROSTER (the database) should always be the same in every situation.
    assertEquals(expectedModel.createNBARoster().size(), actualModel.createNBARoster().size());
    assertEquals(expectedModel.createNBARoster().size(), actualLoadedRosterModel.createNBARoster().size());
    assertEquals(expectedLoadedRosterModel.getRoster().size(), actualLoadedRosterModel.getRoster().size());
    assertEquals(expectedLoadedRosterModel.createNBARoster().size(), actualModel.createNBARoster().size());

  }

  @Test
  void testBeanToPlayer() {
    Set<Player> actualSet = new TreeSet<>(PlayerSortStrategy.getSort(ColumnData.FIRST_NAME));
    List<PlayerBean> testList = new ArrayList<PlayerBean>();
    testList.add(testPlayerBean1);
    testList.add(testPlayerBean2);
    testList.add(testPlayerBean3);
    testList.add(testPlayerBean4);
    testList.add(testPlayerBean5);
    testList.add(testPlayerBean6);
    System.out.println(actualLoadedRosterModel.getRoster().size());
    actualSet = actualLoadedRosterModel.beanToPlayer(testList);
    System.out.println(actualLoadedRosterModel.getRoster().size());
    assertEquals(actualLoadedRosterModel.getRoster(), actualSet);

  }

  @Test
  void testFilterSortNBARoster() {
    Set<Player>


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