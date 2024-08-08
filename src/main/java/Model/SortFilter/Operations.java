package Model.SortFilter;

/**
 * Enum class for Operations used to filter players.
 */
public enum Operations {
  /** Equals Operation enum. */
  EQUALS("=="),

  /** Not Equals Operation enum. */
  NOT_EQUALS("!="),

  /** Contains Operation enum. */
  CONTAINS("~="),

  /** Greater than equals Operation enum. */
  GREATER_THAN_EQUALS(">="),

  /** Less than equals Operation enum. */
  LESS_THAN_EQUALS("<="),

  /** Greater than Operation enum. */
  GREATER_THAN(">"),

  /** Less than Operation enum. */
  LESS_THAN("<");

  /**
   * String attribute of enum.
   */
  private final String operator;

  /**
   * Constructor of Operations enum class.
   * @param value string of operations
   */
  Operations(String value) {
    this.operator = value;
  }

  /**
   * Generates string of operation enum instance.
   * @return string value of operator
   */
  public String getOperator() {
    return operator;
  }

  /**
   * Generates operations enum from string argument.
   * @param str of operation
   * @return Operation enum
   */
  public static Operations getOperatorFromStr(String str) {
    if (str.contains(">=")) {
      return Operations.GREATER_THAN_EQUALS;
    } else if (str.contains("<=")) {
      return Operations.LESS_THAN_EQUALS;
    } else if (str.contains(">")) {
      return Operations.GREATER_THAN;
    } else if (str.contains("<")) {
      return Operations.LESS_THAN;
    } else if (str.contains("==")) {
      return Operations.EQUALS;
    } else if (str.contains("!=")) {
      return Operations.NOT_EQUALS;
    } else if (str.contains("~=")) {
      return Operations.CONTAINS;
    } else {
      return null;
    }
  }
}
