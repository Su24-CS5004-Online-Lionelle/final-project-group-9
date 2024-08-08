import static Model.SortFilter.PlayerSort.sortPlayers;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import Model.Player;
import org.junit.jupiter.api.Test;

/**
 * Tests for sorting sets in PlayerSort class.
 */
class TestPlayerSort {


  // test player objects.
  Player testPlayer1 =
      new Player("Seth", "Curry", "G", "6-1", 0, 0, 0, "Charlotte Hornets",
          "East", 5.136, 1.545, 1.0, 0.136, 0.5, "14:01", 0.392, 0.903, 0.352);

  Player testPlayer2 =
      new Player("Luka", "Doncic", "F-G", "6-7", 2018, 1, 3, "Dallas Mavericks",
          "West", 33.857, 9.243, 9.786, 0.543, 1.4, "37:26", 0.487, 0.786,
          0.382);

  Player testPlayer3 =
      new Player("Shai", "Gilgeous-Alexander", "G", "6-6", 2018, 1, 11,
          "Oklahoma City Thunder", "West", 30.053, 5.52, 6.213, 0.893, 1.987,
          "34:08", 0.535, 0.874, 0.353);

  Player testPlayer4 =
      new Player("Bradley", "Beal", "G", "6-4", 2012, 1, 3, "Phoenix Suns",
          "West", 18.189, 4.377, 5.0, 0.528, 1.019, "33:19", 0.512, 0.813,
          0.43);

  Player testPlayer5 = new Player("Zach", "Collins", "F-C", "6-11", 2017, 1, 10,
      "San Antonio Spurs", "West", 11.246, 5.348, 2.297, 0.754, 0.493, "22:13",
      0.512, 0.813, 0.43);

  Player testPlayer6 = new Player("Jerami", "Grant", "F", "6-7", 2014, 2, 39,
      "Portland Trail Blazers", "West", 20.963, 3.527, 2.815, 0.63, 0.833,
      "33:58", 0.451, 0.817, 0.402);

  /**
   * Test sort players descending by string attribute.
   */
  @Test
  void sortPlayersTeamDescending() {
    List<Player> playersList = new ArrayList<>();
    playersList.add(testPlayer1);
    playersList.add(testPlayer2);
    playersList.add(testPlayer3);
    playersList.add(testPlayer4);
    playersList.add(testPlayer5);
    playersList.add(testPlayer6);
    Set<Player> playerSet = playersList.stream().collect(Collectors.toSet());

    String expected = """
        [First_Name: Zach
        Last_Name: Collins
        Position: F-C
        Height: 6-11
        Draft Year: 2017
        Draft Round: 1
        Draft Pick: 10
        Team: San Antonio Spurs
        Conference: West
        Points per game: 11.25
        Rebounds per game: 5.35
        Assists per game: 2.30
        Blocks per game: 0.75
        Steals per game: 0.49
        Minutes per game: 22:13
        Field goal percentage: 0.51
        Free throw percentage: 0.81
        Three-point percentage: 0.43, First_Name: Jerami
        Last_Name: Grant
        Position: F
        Height: 6-7
        Draft Year: 2014
        Draft Round: 2
        Draft Pick: 39
        Team: Portland Trail Blazers
        Conference: West
        Points per game: 20.96
        Rebounds per game: 3.53
        Assists per game: 2.82
        Blocks per game: 0.63
        Steals per game: 0.83
        Minutes per game: 33:58
        Field goal percentage: 0.45
        Free throw percentage: 0.82
        Three-point percentage: 0.40, First_Name: Bradley
        Last_Name: Beal
        Position: G
        Height: 6-4
        Draft Year: 2012
        Draft Round: 1
        Draft Pick: 3
        Team: Phoenix Suns
        Conference: West
        Points per game: 18.19
        Rebounds per game: 4.38
        Assists per game: 5.00
        Blocks per game: 0.53
        Steals per game: 1.02
        Minutes per game: 33:19
        Field goal percentage: 0.51
        Free throw percentage: 0.81
        Three-point percentage: 0.43, First_Name: Shai
        Last_Name: Gilgeous-Alexander
        Position: G
        Height: 6-6
        Draft Year: 2018
        Draft Round: 1
        Draft Pick: 11
        Team: Oklahoma City Thunder
        Conference: West
        Points per game: 30.05
        Rebounds per game: 5.52
        Assists per game: 6.21
        Blocks per game: 0.89
        Steals per game: 1.99
        Minutes per game: 34:08
        Field goal percentage: 0.54
        Free throw percentage: 0.87
        Three-point percentage: 0.35, First_Name: Luka
        Last_Name: Doncic
        Position: F-G
        Height: 6-7
        Draft Year: 2018
        Draft Round: 1
        Draft Pick: 3
        Team: Dallas Mavericks
        Conference: West
        Points per game: 33.86
        Rebounds per game: 9.24
        Assists per game: 9.79
        Blocks per game: 0.54
        Steals per game: 1.40
        Minutes per game: 37:26
        Field goal percentage: 0.49
        Free throw percentage: 0.79
        Three-point percentage: 0.38, First_Name: Seth
        Last_Name: Curry
        Position: G
        Height: 6-1
        Draft Year: 0
        Draft Round: 0
        Draft Pick: 0
        Team: Charlotte Hornets
        Conference: East
        Points per game: 5.14
        Rebounds per game: 1.55
        Assists per game: 1.00
        Blocks per game: 0.14
        Steals per game: 0.50
        Minutes per game: 14:01
        Field goal percentage: 0.39
        Free throw percentage: 0.90
        Three-point percentage: 0.35]
        """.trim();

    System.out.println(sortPlayers(playerSet, "team", false));

    assertEquals(expected, sortPlayers(playerSet, "team", false).toString().trim());
  }

  /**
   * Test sort players ascending by string attribute.
   */
  @Test
  void sortPlayersHeight() {
    List<Player> playersList = new ArrayList<>();
    playersList.add(testPlayer1);
    playersList.add(testPlayer2);
    playersList.add(testPlayer3);
    playersList.add(testPlayer4);
    playersList.add(testPlayer5);
    playersList.add(testPlayer6);
    Set<Player> playerSet = playersList.stream().collect(Collectors.toSet());

    String expected = """
        [First_Name: Seth
        Last_Name: Curry
        Position: G
        Height: 6-1
        Draft Year: 0
        Draft Round: 0
        Draft Pick: 0
        Team: Charlotte Hornets
        Conference: East
        Points per game: 5.14
        Rebounds per game: 1.55
        Assists per game: 1.00
        Blocks per game: 0.14
        Steals per game: 0.50
        Minutes per game: 14:01
        Field goal percentage: 0.39
        Free throw percentage: 0.90
        Three-point percentage: 0.35, First_Name: Bradley
        Last_Name: Beal
        Position: G
        Height: 6-4
        Draft Year: 2012
        Draft Round: 1
        Draft Pick: 3
        Team: Phoenix Suns
        Conference: West
        Points per game: 18.19
        Rebounds per game: 4.38
        Assists per game: 5.00
        Blocks per game: 0.53
        Steals per game: 1.02
        Minutes per game: 33:19
        Field goal percentage: 0.51
        Free throw percentage: 0.81
        Three-point percentage: 0.43, First_Name: Shai
        Last_Name: Gilgeous-Alexander
        Position: G
        Height: 6-6
        Draft Year: 2018
        Draft Round: 1
        Draft Pick: 11
        Team: Oklahoma City Thunder
        Conference: West
        Points per game: 30.05
        Rebounds per game: 5.52
        Assists per game: 6.21
        Blocks per game: 0.89
        Steals per game: 1.99
        Minutes per game: 34:08
        Field goal percentage: 0.54
        Free throw percentage: 0.87
        Three-point percentage: 0.35, First_Name: Luka
        Last_Name: Doncic
        Position: F-G
        Height: 6-7
        Draft Year: 2018
        Draft Round: 1
        Draft Pick: 3
        Team: Dallas Mavericks
        Conference: West
        Points per game: 33.86
        Rebounds per game: 9.24
        Assists per game: 9.79
        Blocks per game: 0.54
        Steals per game: 1.40
        Minutes per game: 37:26
        Field goal percentage: 0.49
        Free throw percentage: 0.79
        Three-point percentage: 0.38, First_Name: Jerami
        Last_Name: Grant
        Position: F
        Height: 6-7
        Draft Year: 2014
        Draft Round: 2
        Draft Pick: 39
        Team: Portland Trail Blazers
        Conference: West
        Points per game: 20.96
        Rebounds per game: 3.53
        Assists per game: 2.82
        Blocks per game: 0.63
        Steals per game: 0.83
        Minutes per game: 33:58
        Field goal percentage: 0.45
        Free throw percentage: 0.82
        Three-point percentage: 0.40, First_Name: Zach
        Last_Name: Collins
        Position: F-C
        Height: 6-11
        Draft Year: 2017
        Draft Round: 1
        Draft Pick: 10
        Team: San Antonio Spurs
        Conference: West
        Points per game: 11.25
        Rebounds per game: 5.35
        Assists per game: 2.30
        Blocks per game: 0.75
        Steals per game: 0.49
        Minutes per game: 22:13
        Field goal percentage: 0.51
        Free throw percentage: 0.81
        Three-point percentage: 0.43]
        """.trim();

    assertEquals(expected, sortPlayers(playerSet, "height").toString().trim());
  }

  /**
   * Test sort players ascending by numerical attribute with capitalized sort type.
   */
  @Test
  void testSortAscendingDraftRound() {
    List<Player> playersList = new ArrayList<>();
    playersList.add(testPlayer1);
    playersList.add(testPlayer2);
    playersList.add(testPlayer3);
    playersList.add(testPlayer4);
    playersList.add(testPlayer5);
    playersList.add(testPlayer6);
    Set<Player> playerSet = playersList.stream().collect(Collectors.toSet());

    String expected = """
        [First_Name: Seth
        Last_Name: Curry
        Position: G
        Height: 6-1
        Draft Year: 0
        Draft Round: 0
        Draft Pick: 0
        Team: Charlotte Hornets
        Conference: East
        Points per game: 5.14
        Rebounds per game: 1.55
        Assists per game: 1.00
        Blocks per game: 0.14
        Steals per game: 0.50
        Minutes per game: 14:01
        Field goal percentage: 0.39
        Free throw percentage: 0.90
        Three-point percentage: 0.35, First_Name: Shai
        Last_Name: Gilgeous-Alexander
        Position: G
        Height: 6-6
        Draft Year: 2018
        Draft Round: 1
        Draft Pick: 11
        Team: Oklahoma City Thunder
        Conference: West
        Points per game: 30.05
        Rebounds per game: 5.52
        Assists per game: 6.21
        Blocks per game: 0.89
        Steals per game: 1.99
        Minutes per game: 34:08
        Field goal percentage: 0.54
        Free throw percentage: 0.87
        Three-point percentage: 0.35, First_Name: Luka
        Last_Name: Doncic
        Position: F-G
        Height: 6-7
        Draft Year: 2018
        Draft Round: 1
        Draft Pick: 3
        Team: Dallas Mavericks
        Conference: West
        Points per game: 33.86
        Rebounds per game: 9.24
        Assists per game: 9.79
        Blocks per game: 0.54
        Steals per game: 1.40
        Minutes per game: 37:26
        Field goal percentage: 0.49
        Free throw percentage: 0.79
        Three-point percentage: 0.38, First_Name: Jerami
        Last_Name: Grant
        Position: F
        Height: 6-7
        Draft Year: 2014
        Draft Round: 2
        Draft Pick: 39
        Team: Portland Trail Blazers
        Conference: West
        Points per game: 20.96
        Rebounds per game: 3.53
        Assists per game: 2.82
        Blocks per game: 0.63
        Steals per game: 0.83
        Minutes per game: 33:58
        Field goal percentage: 0.45
        Free throw percentage: 0.82
        Three-point percentage: 0.40, First_Name: Zach
        Last_Name: Collins
        Position: F-C
        Height: 6-11
        Draft Year: 2017
        Draft Round: 1
        Draft Pick: 10
        Team: San Antonio Spurs
        Conference: West
        Points per game: 11.25
        Rebounds per game: 5.35
        Assists per game: 2.30
        Blocks per game: 0.75
        Steals per game: 0.49
        Minutes per game: 22:13
        Field goal percentage: 0.51
        Free throw percentage: 0.81
        Three-point percentage: 0.43, First_Name: Bradley
        Last_Name: Beal
        Position: G
        Height: 6-4
        Draft Year: 2012
        Draft Round: 1
        Draft Pick: 3
        Team: Phoenix Suns
        Conference: West
        Points per game: 18.19
        Rebounds per game: 4.38
        Assists per game: 5.00
        Blocks per game: 0.53
        Steals per game: 1.02
        Minutes per game: 33:19
        Field goal percentage: 0.51
        Free throw percentage: 0.81
        Three-point percentage: 0.43]
        """.trim();
    assertEquals(expected,
        sortPlayers(playerSet, "3 POINT percentage", true).toString().trim());
  }

  /**
   * Test sort players descending by numerical attribute.
   */
  @Test
  void testSortDescendingPointsPerGame() {
    List<Player> playersList = new ArrayList<>();
    playersList.add(testPlayer1);
    playersList.add(testPlayer2);
    playersList.add(testPlayer3);
    playersList.add(testPlayer4);
    playersList.add(testPlayer5);
    playersList.add(testPlayer6);
    Set<Player> playerSet = playersList.stream().collect(Collectors.toSet());

    String expected = """
        [First_Name: Luka
        Last_Name: Doncic
        Position: F-G
        Height: 6-7
        Draft Year: 2018
        Draft Round: 1
        Draft Pick: 3
        Team: Dallas Mavericks
        Conference: West
        Points per game: 33.86
        Rebounds per game: 9.24
        Assists per game: 9.79
        Blocks per game: 0.54
        Steals per game: 1.40
        Minutes per game: 37:26
        Field goal percentage: 0.49
        Free throw percentage: 0.79
        Three-point percentage: 0.38, First_Name: Shai
        Last_Name: Gilgeous-Alexander
        Position: G
        Height: 6-6
        Draft Year: 2018
        Draft Round: 1
        Draft Pick: 11
        Team: Oklahoma City Thunder
        Conference: West
        Points per game: 30.05
        Rebounds per game: 5.52
        Assists per game: 6.21
        Blocks per game: 0.89
        Steals per game: 1.99
        Minutes per game: 34:08
        Field goal percentage: 0.54
        Free throw percentage: 0.87
        Three-point percentage: 0.35, First_Name: Jerami
        Last_Name: Grant
        Position: F
        Height: 6-7
        Draft Year: 2014
        Draft Round: 2
        Draft Pick: 39
        Team: Portland Trail Blazers
        Conference: West
        Points per game: 20.96
        Rebounds per game: 3.53
        Assists per game: 2.82
        Blocks per game: 0.63
        Steals per game: 0.83
        Minutes per game: 33:58
        Field goal percentage: 0.45
        Free throw percentage: 0.82
        Three-point percentage: 0.40, First_Name: Bradley
        Last_Name: Beal
        Position: G
        Height: 6-4
        Draft Year: 2012
        Draft Round: 1
        Draft Pick: 3
        Team: Phoenix Suns
        Conference: West
        Points per game: 18.19
        Rebounds per game: 4.38
        Assists per game: 5.00
        Blocks per game: 0.53
        Steals per game: 1.02
        Minutes per game: 33:19
        Field goal percentage: 0.51
        Free throw percentage: 0.81
        Three-point percentage: 0.43, First_Name: Zach
        Last_Name: Collins
        Position: F-C
        Height: 6-11
        Draft Year: 2017
        Draft Round: 1
        Draft Pick: 10
        Team: San Antonio Spurs
        Conference: West
        Points per game: 11.25
        Rebounds per game: 5.35
        Assists per game: 2.30
        Blocks per game: 0.75
        Steals per game: 0.49
        Minutes per game: 22:13
        Field goal percentage: 0.51
        Free throw percentage: 0.81
        Three-point percentage: 0.43, First_Name: Seth
        Last_Name: Curry
        Position: G
        Height: 6-1
        Draft Year: 0
        Draft Round: 0
        Draft Pick: 0
        Team: Charlotte Hornets
        Conference: East
        Points per game: 5.14
        Rebounds per game: 1.55
        Assists per game: 1.00
        Blocks per game: 0.14
        Steals per game: 0.50
        Minutes per game: 14:01
        Field goal percentage: 0.39
        Free throw percentage: 0.90
        Three-point percentage: 0.35]
        """.trim();

    assertEquals(expected,
        sortPlayers(playerSet, "points per game", false).toString().trim());
  }
}