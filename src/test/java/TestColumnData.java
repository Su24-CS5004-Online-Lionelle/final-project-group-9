import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import Model.SortFilter.ColumnData;
import org.junit.jupiter.api.Test;

class TestColumnData {

  /**
   * Test case-sensitivestring returns correct column data
   */
  @Test
  void fromColumnName() {
    // Happy cases
    ColumnData actual = ColumnData.fromColumnName("Assists per game");
    assertEquals(ColumnData.APG, actual);

    ColumnData actualTwo = ColumnData.fromColumnName("3 point percentage");
    assertEquals(ColumnData.FP3P, actualTwo);

    assertEquals(ColumnData.DRAFTPICK, ColumnData.fromString("Draft pick"));

    // Edge Cases - case-sensitive
    assertThrows(IllegalArgumentException.class, () -> {
      ColumnData.fromColumnName("LAST name");
    });

    assertThrows(IllegalArgumentException.class,
        () -> ColumnData.fromColumnName("   rebOUNDS   Per game   "));
  }

  @Test
  void fromString() {
    // Happy cases
    ColumnData actual = ColumnData.fromString("Points per game");
    assertEquals(ColumnData.PPG, actual);

    ColumnData actualTwo = ColumnData.fromString("Minutes per game");
    assertEquals(ColumnData.MPG, actualTwo);

    assertEquals(ColumnData.DRAFTPICK, ColumnData.fromString("draft pick"));

    assertEquals(ColumnData.CONFERENCE, ColumnData.fromString("conference"));

    assertEquals(ColumnData.LAST_NAME, ColumnData.fromString("LAST name"));
    assertEquals(ColumnData.RPG, ColumnData.fromString("rebounds per game"));
  }
}