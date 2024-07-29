package Model.SortFilter;

import Model.PlayerBean;

/**
 * Filters class that filters board game data based on operations passed
 */
public final class Filters {

  private static final int ZERO_INT = 0;

  /**
   * Private constructor.
   */
  private Filters() {

  }

  /**
   * Generates boolean determining whether value passed and current game satisfies operation.
   * @param player
   * @param val
   * @return
   */
  public static boolean getFilter(PlayerBean player, String val) {
    return false;
  }

  /**
   * Compares string values passed on operations passed.
   * @return
   */
  private static boolean filterString(String name, Operations op, String value) {
    return false;
  }

  /**
   * Compares int values passed on operations passed.
   * @return
   */
  private static boolean filterNumber(int number, Operations op, int value) {
    return false;
  }

  private static boolean checkStringValue() {
    return false;
  }

}
