package Model.SortFilter;

import java.util.LinkedHashSet;
import java.util.Set;
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

    Set<Player> sorted =
        players.stream().sorted(PlayerSortStrategy.getSort(type, direction))
            .collect(
        Collectors.toCollection(LinkedHashSet::new));

    return sorted;
  }
}
