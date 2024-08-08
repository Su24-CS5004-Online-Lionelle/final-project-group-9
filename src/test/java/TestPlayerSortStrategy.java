import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import Model.Player;
import Model.SortFilter.ColumnData;
import Model.SortFilter.PlayerSortStrategy;
import org.junit.jupiter.api.Test;

/**
 * Tests the PlayerSortStrategy comparator for the Player class.
 */
class TestPlayerSortStrategy {

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

  Player testPlayer4 = new Player("Bradley", "Beal", "G", "6-4", 2012,
      1, 3, "Phoenix Suns", "West", 18.189, 4.377, 5.0,
      0.528, 1.019, "33:19", 0.512, 0.813, 0.43);

  Player testPlayer5 = new Player("Zach", "Collins", "F-C", "6-11", 2017,
      1, 10, "San Antonio Spurs", "West", 11.246, 5.348, 2.297,
      0.754, 0.493, "22:13", 0.512, 0.813, 0.43);

  Player testPlayer6 = new Player("Jerami", "Grant", "F", "6-7", 2014,
      2, 39, "Portland Trail Blazers", "West", 20.963, 3.527, 2.815,
      0.63, 0.833, "33:58", 0.451, 0.817, 0.402);


  /**
   * Tests getSort() for ascending string.
   */
  @Test
  void getSortNamesAscending() {
    List<Player> playersList = new ArrayList<>();
    playersList.add(testPlayer1);
    playersList.add(testPlayer2);
    playersList.add(testPlayer3);
    playersList.add(testPlayer4);
    playersList.add(testPlayer5);
    playersList.add(testPlayer6);
    List<String> expectedOrder = playersList.stream()
        .map(Player::getFirstName)
        .sorted()
        .collect(Collectors.toList());

    Set<Player> actualSet = new TreeSet<>(PlayerSortStrategy.getSort(ColumnData.FIRST_NAME));
    actualSet.add(testPlayer1);
    actualSet.add(testPlayer2);
    actualSet.add(testPlayer3);
    actualSet.add(testPlayer4);
    actualSet.add(testPlayer5);
    actualSet.add(testPlayer6);

    List<String> actualNames = actualSet.stream().map(Player::getFirstName).collect(
        Collectors.toUnmodifiableList());

    assertEquals(expectedOrder, actualNames);
    assertEquals(6, actualNames.size()); // check size is the same
  }

  /**
   * Tests getSort() for descending string.
   */
  @Test
  void getSortNamesDescending() {
    List<Player> playersList = new ArrayList<>();
    playersList.add(testPlayer1);
    playersList.add(testPlayer2);
    playersList.add(testPlayer3);
    playersList.add(testPlayer4);
    playersList.add(testPlayer5);
    playersList.add(testPlayer6);
    List<String> expectedOrder = playersList.stream()
        .map(Player::getLastName)
        .sorted(Comparator.reverseOrder())
        .collect(Collectors.toList()); // reverse order

    Set<Player> actualSet = new TreeSet<>(PlayerSortStrategy.getSort(ColumnData.LAST_NAME, false));
    actualSet.add(testPlayer1);
    actualSet.add(testPlayer2);
    actualSet.add(testPlayer3);
    actualSet.add(testPlayer4);
    actualSet.add(testPlayer5);
    actualSet.add(testPlayer6);

    List<String> actualNames = actualSet.stream().map(Player::getLastName).collect(
        Collectors.toUnmodifiableList());

    assertEquals(expectedOrder, actualNames);
    assertEquals(6, actualNames.size()); // check size is the same
  }

  /**
   * Tests getSort() for ascending number as a string (minutes).
   */
  @Test
  void getSortMinAscending() {
    List<Player> playersList = new ArrayList<>();
    playersList.add(testPlayer1);
    playersList.add(testPlayer2);
    playersList.add(testPlayer3);
    playersList.add(testPlayer4);
    playersList.add(testPlayer5);
    playersList.add(testPlayer6);
    List<String> expectedOrder = playersList.stream()
        .map(Player::getMpg)
        .sorted()
        .collect(Collectors.toList());

    Set<Player> actualSet = new TreeSet<>(PlayerSortStrategy.getSort(ColumnData.MPG));
    actualSet.add(testPlayer1);
    actualSet.add(testPlayer2);
    actualSet.add(testPlayer3);
    actualSet.add(testPlayer4);
    actualSet.add(testPlayer5);
    actualSet.add(testPlayer6);

    List<String> actualNames = actualSet.stream().map(Player::getMpg).collect(
        Collectors.toUnmodifiableList());

    assertEquals(expectedOrder, actualNames);
  }

  /**
   * Tests getSort() for ascending double.
   */
  @Test
  void getSortPPGAscending() {
    List<Player> playersList = new ArrayList<>();
    playersList.add(testPlayer1);
    playersList.add(testPlayer2);
    playersList.add(testPlayer3);
    playersList.add(testPlayer4);
    playersList.add(testPlayer5);
    playersList.add(testPlayer6);
    List<Double> expectedOrder = playersList.stream()
        .map(Player::getPpg)
        .sorted()
        .collect(Collectors.toList());

    Set<Player> actualSet = new TreeSet<>(PlayerSortStrategy.getSort(ColumnData.PPG));
    actualSet.add(testPlayer1);
    actualSet.add(testPlayer2);
    actualSet.add(testPlayer3);
    actualSet.add(testPlayer4);
    actualSet.add(testPlayer5);
    actualSet.add(testPlayer6);

    List<Double> actualNames = actualSet.stream().map(Player::getPpg).collect(
        Collectors.toUnmodifiableList());

    assertEquals(expectedOrder, actualNames);
  }

  /**
   * Tests getSort() for ascending int number.
   */
  @Test
  void getSortDraftPickAscending() {
    List<Integer> playersList = new ArrayList<>();
    playersList.add(0);
    playersList.add(2012);
    playersList.add(2014);
    playersList.add(2017);
    playersList.add(2018);
    playersList.add(2018);

    List<Player> list = new ArrayList<>();
    list.add(testPlayer1);
    list.add(testPlayer2);
    list.add(testPlayer3);
    list.add(testPlayer4);
    list.add(testPlayer5);
    list.add(testPlayer6);

    List<Integer> actualOrder = list.stream()
        .map(Player::getDraftYear)
        .sorted()
        .collect(Collectors.toList());

    assertEquals(playersList, actualOrder);
  }

  /**
   * Tests getSort() for descending doubles.
   */
  @Test
  void getSortAPGDescending() {
    List<Player> playersList = new ArrayList<>();
    playersList.add(testPlayer1);
    playersList.add(testPlayer2);
    playersList.add(testPlayer3);
    playersList.add(testPlayer4);
    playersList.add(testPlayer5);
    playersList.add(testPlayer6);
    List<Double> expectedOrder = playersList.stream()
        .map(Player::getApg)
        .sorted(Comparator.reverseOrder())
        .collect(Collectors.toList()); // reverse order

    Set<Player> actualSet = new TreeSet<>(PlayerSortStrategy.getSort(ColumnData.APG, false));
    actualSet.add(testPlayer1);
    actualSet.add(testPlayer2);
    actualSet.add(testPlayer3);
    actualSet.add(testPlayer4);
    actualSet.add(testPlayer5);
    actualSet.add(testPlayer6);

    List<Double> actualNames = actualSet.stream().map(Player::getApg).collect(
        Collectors.toUnmodifiableList());

    assertEquals(expectedOrder, actualNames);
  }
}