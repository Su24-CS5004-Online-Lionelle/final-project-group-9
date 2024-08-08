package Model.SortFilter;

import Model.Player;

import static java.lang.Double.parseDouble;

/**
 * Filters class that filters board player data based on operations passed
 */
public final class Filters {

  private static final int ZERO_INT = 0;

  /**
   * Private constructor to prevent instantiation.
   */
  private Filters() {
    // Empty constructor.
  }

  /**
   * Generates boolean determining whether value passed and current player satisfies operation.
   *
   * @param player Player object being compared
   * @param val String
   * @return
   */
  public static boolean getFilter(Player player, ColumnData type, Operations op,
                                  String val) {
    // Trim extra white space in value
    val = val.trim();

    switch (type) {
      case FIRST_NAME:
        return filterString(player.getFirstName(), op, val);
      case LAST_NAME:
        return filterString(player.getLastName(), op, val);
      case POSITION:
        return filterString(player.getPosition(), op, val);
      case HEIGHT:
        if (op == Operations.CONTAINS) {
          return filterString(player.getHeight(), op, val);
        }
          return convertHeightToDouble(player.getHeight(), op, val);
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
        return filterString(player.getMpg(), op, val);
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
   *
   * @return
   */
  private static boolean filterString(String name, Operations op,
                                      String value) {
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
   * Compares numbers.
   *
   * @param number
   * @param op
   * @param doubleValue
   * @return
   */
  private static boolean filterNumericalValues(double number, Operations op,
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

  /**
   * Convert height string to double.
   * Handles empty string and null strings
   * @return boolean value
   */
    private static boolean convertHeightToDouble (String playerOne, Operations op, String playerTwo){
      // Convert height string to decimal
      String[] partsOne = playerOne.split("-");
      String[] partsTwo = playerTwo.split("-");

      double playerOneHeight;
      double playerOneFeet = ZERO_INT;
      if (!partsOne[0].isEmpty()) {
        playerOneFeet = Double.valueOf(partsOne[0]) * 12;
      }
      double playerOneInches = ZERO_INT;
      if (!partsOne[1].isEmpty()) {
        playerOneInches = (Double.valueOf(partsOne[1]));
      }

      double playerTwoHeight;
      double playerTwoFeet = ZERO_INT;
      if (!partsTwo[0].isEmpty()) {
        playerTwoFeet = Double.valueOf(partsTwo[0]) * 12;

      } else {
        return false; // return false if second string is empty to prevent index out of bounds
      }

      double playerTwoInches = ZERO_INT;
      if (!partsTwo[1].isEmpty()) {
        playerTwoInches = (Double.valueOf(partsTwo[1]));
      }

      // Compare feet
      try {
        playerOneHeight = playerOneFeet + playerOneInches;
        playerTwoHeight = playerTwoFeet + playerTwoInches;
      } catch (IllegalArgumentException e){
        throw new IllegalArgumentException("Height could not be converted to double.");
      }

      // Compare converted heights
      return filterNumericalValues(playerOneHeight, op, playerTwoHeight);
    }

    /**
     * Compares int values passed on operations passed.
     * @return
     */
    private static boolean convertInt(int number, Operations op, String value)
    {
      // Convert String value to numeric
      int intValue;

      try {
        intValue = Integer.valueOf(value);
      } catch (IllegalArgumentException e) {
        throw new IllegalArgumentException(
            "Value being compared is not an integer.");
      }

      // Switch case for filter
      return filterNumericalValues(number, op, intValue);
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
      return filterNumericalValues(number, op, doubleValue);
    }
  }
