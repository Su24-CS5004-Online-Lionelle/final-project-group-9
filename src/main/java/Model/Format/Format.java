package Model.Format;

/** A list of format types allowed for export and import. */
public enum Format {
  /** Different formatting options. */
  JSON, XML, CSV, PRETTY;

  /**
   * Helper function to check if a value is in the list of formats.
   *
   * @param value the value to check
   * @return the format if found, null otherwise
   */
  public static Format containsValues(String value) {
    for (Format format : Format.values()) {
      if (format.toString().equalsIgnoreCase(value)) {
        return format;
      }
    }
    return null;
  }
}

