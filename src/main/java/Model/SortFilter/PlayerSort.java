package Model.SortFilter;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import Model.Player;

/**
 * PlayerSort class that generates set of sorted Players based on sort type.
 */
public class PlayerSort {

  /**
   * Private constructor.
   */
  private PlayerSort() {

  }

/**
 * Sorts board games into a set of games in ascending order by default based on sort type of board game attribute.
 */
public static Set<Player> sortPlayers(Set<Player> players, String sortType) {
    return sortPlayers(players, sortType, true);
  }

  /**
   * Sorts board games into a set of games in specified order based on boolean and sort type of board game attribute.
   */
  public static Set<Player> sortPlayers(Set<Player> players, String sortType,
                                        boolean direction) {
    // Convert lowercase and trimmed string sortType to GameData
    ColumnData type = ColumnData.fromString(sortType.toLowerCase().trim());

    List<Player> toSort = new ArrayList<>();
    for (Player player : players) {
      toSort.add(player);
    }
    toSort.sort(PlayerSortStrategy.getSort(type, direction));
    Set<Player> sorted =
        new TreeSet<>(PlayerSortStrategy.getSort(ColumnData.FIRST_NAME));
    for (Player player : toSort) {
      sorted.add(player);
    }

    return sorted;
  }
}
