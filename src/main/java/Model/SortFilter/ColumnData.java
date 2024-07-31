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
  NAME("name"), AGE("age"),
  POSITION("position"), HEIGHT("height"), DRAFTYEAR("draftYear"),
  DRAFTROUND("draftRound"), DRAFTPICK("draftpick"), TEAM("team"), CONFERENCE("conference"),
  PPG("ppg"), RPG("rpg"), APG("apg"),
  BPG("bpg"), SPG("spg"), TPG("tpg"), MPG("mpg"), FGP("fgp"),
  FTP("ftp"), FP3P("fg3p");

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
