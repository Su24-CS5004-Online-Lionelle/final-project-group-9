package Model.SortFilter;

/**
 * Enum to represent the columns in the game data.
 *
 * This is to make it easier to access the column names
 * from the JSON file, without knowing
 * the names of the specific columns anywhere else in the program.
 *
 * Throughout your program, use ColumnData when parsing anything
 * that is associated with column names (filter) and sorting.
 */
public enum ColumnData {
  /**
   * Enums matching CODE(jsonname) pattern.
   *
   * names are used for game uniqueness.
   */
  FIRST_NAME("First Name"), LAST_NAME("Last Name"), AGE("Age"),
  POSITION("Position"), HEIGHT("Height"), DRAFTYEAR("Draft year"),
  DRAFTROUND("Draft round"), DRAFTPICK("Draft pick"), TEAM("Team"), CONFERENCE("Conference"),
  PPG("Points per game"), RPG("Rebounds per game"), APG("Assists per game"),
  BPG("Blocks per game"), SPG("Steals per game"), MPG("Minutes per game"), FGP("Field goal percentage"),
  FTP("Free throw percentage"), FP3P("3 point percentage");

  /** stores the original csv name in the enum. */
  private final String columnName;

  /**
   * Constructor for the enum.
   *
   * @param columnName the name of the column in the CSV file.
   */
  ColumnData(String columnName) {
    this.columnName = columnName;
  }

  /**
   * Getter for the column name.
   *
   * @return the name of the column in the CSV file.
   */
  public String getColumnName() {
    return columnName;
  }

  /**
   * Get the enum from the column name.
   *
   * @param columnName the name of the column in the CSV file.
   * @return the enum that matches the column name.
   */
  public static ColumnData fromColumnName(String columnName) {
    for (ColumnData col : ColumnData.values()) {
      if (col.getColumnName().equals(columnName)) {
        return col;
      }
    }
    throw new IllegalArgumentException("fromColumnName: No column with name " + columnName);
  }

  /**
   * Get the enum from the enum name.
   *
   * Can use the enum name or the column name. Useful for filters and sorts
   * as they can use both.
   *
   * @param name the name of the enum.
   * @return the enum that matches the name.
   */
  public static ColumnData fromString(String name) {
    for (ColumnData col : ColumnData.values()) {
      if (col.name().equalsIgnoreCase(name) || col.getColumnName().equalsIgnoreCase(name)) {
        return col;
      }
    }
    throw new IllegalArgumentException("fromString: No column with name " + name);
  }
}
