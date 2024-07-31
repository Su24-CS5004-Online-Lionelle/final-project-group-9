package Model.SortFilter;

import Model.Player;

/**
 * Filters class that filters board player data based on operations passed
 */
public final class Filters {

  private static final int ZERO_INT = 0;

  /**
   * Private constructor.
   */
  private Filters() {

  }

  /**
   * Generates boolean determining whether value passed and current player satisfies operation.
   * @param player
   * @param val
   * @return
   */
  public static boolean getFilter(Player player, ColumnData type, Operations op,
                                  String val) {
    // Trim extra white space in value
    val = val.trim();
    
    switch(type) {
      case NAME:
        return filterString(player.getName(), op, val);
      case AGE:
        return convertInt(player.getAge(), op, val);
      case POSITION:
        return filterString(player.getPosition(), op, val);
      case HEIGHT:
        return filterString(player.getHeight(), op, val);
      case DRAFTYEAR:
        return convertInt(player.getDraftYear(), op, val);
      case DRAFTROUND:
        return convertInt(player.getDraftRound(), op, val);
        case DRAFTPICK:
          return convertInt(player.getDraftPick(), op, val);
      case TEAM:
        return filterString(player.getTeam(), op, val);
      case CONFERENCE:
        return filterString(player.getConference(), op, val);
      case PPG:
        return convertDouble(player.getPpg(), op, val);
      case RPG:
        return convertDouble(player.getRpg(), op, val);
      case APG:
        return convertDouble(player.getApg(), op, val);
      case BPG:
        return convertDouble(player.getBpg(), op, val);
      case SPG:
        return convertDouble(player.getSpg(), op, val);
      case MPG:
        return convertDouble(player.getMpg(), op, val);
      case FGP:
        return convertDouble(player.getFgp(), op, val);
      case FTP:
        return convertDouble(player.getFtp(), op, val);
      case FP3P:
        return convertDouble(player.getFg3p(), op, val);
      default:
        break;
    }
    return false;
  }

  /**
   * Compares string values passed on operations passed.
   * @return
   */
  private static boolean filterString(String name, Operations op, String value) {
    name = name.toLowerCase();
    value = value.toLowerCase();

    switch (op) {
      case EQUALS:
        return name.equalsIgnoreCase(value);
      case NOT_EQUALS:
        return !name.equalsIgnoreCase(value);
      case CONTAINS:
        return name.contains(value);
      case GREATER_THAN_EQUALS:
        return name.compareToIgnoreCase(value) >= ZERO_INT;
      case GREATER_THAN:
        return name.compareToIgnoreCase(value) > ZERO_INT;
      case LESS_THAN_EQUALS:
        return name.compareToIgnoreCase(value) <= ZERO_INT;
      case LESS_THAN:
        return name.compareToIgnoreCase(value) < ZERO_INT;
      default:
        return false;
    }
  }

  /**
   * Compares int values passed on operations passed.
   * @return
   */
  private static boolean convertInt(int number, Operations op, String value) {
    // Convert String value to numeric
    int intValue;

    try {
      intValue = Integer.valueOf(value);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Value being compared is not an integer.");
    }

    // Switch case for filter
    return checkNumericalValues(number, op, intValue);
  }

  /**
   * Compares double values.
   * @param number
   * @param op
   * @param value
   * @return
   */
  private static boolean convertDouble(double number, Operations op, String value) {
    double doubleValue;
    try {
      doubleValue = Double.valueOf(value);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException(
          "Value being compared is not a double.");
    }
    // Switch case for filter
    return checkNumericalValues(number, op, doubleValue);
  }

  /**
   *  Compares numbers.
   * @param number
   * @param op
   * @param doubleValue
   * @return
   */
  private static boolean checkNumericalValues(double number, Operations op,
                                          double doubleValue) {
    switch (op) {
      case EQUALS, CONTAINS:
        return number == doubleValue;
      case NOT_EQUALS:
        return !(number == doubleValue);
      case GREATER_THAN:
        return number > doubleValue;
      case GREATER_THAN_EQUALS:
        return number >= doubleValue;
      case LESS_THAN:
        return number < doubleValue;
      case LESS_THAN_EQUALS:
        return number <= doubleValue;
      default:
        return false;
    }
  }

}
