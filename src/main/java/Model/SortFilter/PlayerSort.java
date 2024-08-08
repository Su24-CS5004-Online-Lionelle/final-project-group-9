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
 * Sorts playerss into a set of players in ascending order by default based on sort type of players attribute.
 * @param players set of players
 * @param sortType string to be converted to column data enum
 * @return set of players
 */
public static Set<Player> sortPlayers(Set<Player> players, String sortType) {
    return sortPlayers(players, sortType, true);
  }

  /**
   * Sorts players into a set of players in specified order based on boolean and sort type of players attribute.
   * @param players set of players
   * @param sortType string to be converted to column data enum
   * @param direction direction for ascending or descending order
   * @return set of players
   */
  public static Set<Player> sortPlayers(Set<Player> players, String sortType,
                                        boolean direction) {
    // Convert lowercase and trimmed string sortType to playersData
    ColumnData type = ColumnData.fromString(sortType.toLowerCase().trim());

    return players.stream().sorted(PlayerSortStrategy.getSort(type, direction))
        .collect(Collectors.toCollection(LinkedHashSet::new));

  }
}
