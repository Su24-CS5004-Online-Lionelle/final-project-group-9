import static org.junit.jupiter.api.Assertions.*;
import Model.Player;
import Model.SortFilter.ColumnData;
import Model.SortFilter.Filters;
import Model.SortFilter.Operations;
import org.junit.jupiter.api.Test;

class TestFilters {

  // test player objects.
  Player testPlayer1 = new Player("Seth", "Curry", "G", "6-1", 0, 0, 0,
      "Charlotte Hornets", "East", 5.136, 1.545, 1.0, 0.136, 0.5,
      "14:01", 0.392, 0.903, 0.352);

  Player testPlayer2 = new Player("Luka", "Doncic", "F-G", "6-7", 2018,
      1, 3, "Dallas Mavericks", "West", 33.857, 9.243, 9.786,
      0.543, 1.4, "37:26", 0.487, 0.786, 0.382);

  Player testPlayer3 = new Player("Shai", "Gilgeous-Alexander", "G", "6-6",
      2018, 1, 11, "Oklahoma City Thunder", "West",
      30.053, 5.52, 6.213, 0.893, 1.987, "34:08", 0.535, 0.874, 0.353);

  Player testPlayer4 = new Player("Bradley", "Beal", "G", "6-4", 2012,
      1, 3, "Phoenix Suns", "West", 18.189, 4.377, 5.0,
      0.528, 1.019, "33:19", 0.512, 0.813, 0.43);

  Player testPlayer5 = new Player("Zach", "Collins", "F-C", "6-11", 2017,
      1, 10, "San Antonio Spurs", "West", 11.246, 5.348, 2.297,
      0.754, 0.493, "22:13", 0.512, 0.813, 0.43);

  Player testPlayer6 = new Player("Jerami", "Grant", "F", "6-7", 2014,
      2, 39, "Portland Trail Blazers", "West", 20.963, 3.527, 2.815,
      0.63, 0.833, "33:58", 0.451, 0.817, 0.402);



  /**
   * Happy getFilter() test cases for first name.
   */
  @Test
  void getFilterFirstNameHappyCase() {
    boolean actualOne = Filters.getFilter(testPlayer1, ColumnData.FIRST_NAME, Operations.EQUALS, "Seth");
    assertEquals(true, actualOne); // Seth == SETH

    boolean actualTwo = Filters.getFilter(testPlayer3, ColumnData.FIRST_NAME, Operations.NOT_EQUALS, "Gilgeous-Alexander");
    assertEquals(true, actualTwo); // Shai != Gilgeous-Alexander

    boolean actualThree = Filters.getFilter(testPlayer1, ColumnData.FIRST_NAME, Operations.CONTAINS, "set");
    assertEquals(true, actualThree); // Seth ~= set

    boolean actualFour = Filters.getFilter(testPlayer4, ColumnData.FIRST_NAME, Operations.GREATER_THAN_EQUALS, "Adam");
    assertEquals(true, actualFour); // Bradley >= Adam

    boolean actualFive = Filters.getFilter(testPlayer1, ColumnData.FIRST_NAME, Operations.GREATER_THAN, "Adam");
    assertEquals(true, actualFive); // Seth > Adam

    boolean actualSix = Filters.getFilter(testPlayer1, ColumnData.FIRST_NAME, Operations.LESS_THAN, "Stephen");
    assertEquals(true, actualSix); // Seth < Stephen

    boolean actualSeven = Filters.getFilter(testPlayer4, ColumnData.FIRST_NAME, Operations.LESS_THAN_EQUALS, "Zach");
    assertEquals(true, actualSeven); // Bradley <= Zach
  }

  /**
   * Happy getFilter() test cases for first name.
   */
  @Test
  void getFilterFirstNameEdgeCase() {
    // Test case-sensitivity
    boolean actualOne = Filters.getFilter(testPlayer1, ColumnData.FIRST_NAME, Operations.EQUALS, "SETH");
    assertEquals(true, actualOne); // Seth == SETH

    boolean actualTwo = Filters.getFilter(testPlayer1, ColumnData.FIRST_NAME, Operations.EQUALS, "sEtH");
    assertEquals(true, actualTwo); // Seth == SETH

    // Test single letter
    boolean actualThree = Filters.getFilter(testPlayer1, ColumnData.FIRST_NAME, Operations.CONTAINS, "E");
    assertEquals(true, actualThree); // Seth ~= set

    // Test equals in greater than equals
    boolean actualFour = Filters.getFilter(testPlayer4, ColumnData.FIRST_NAME, Operations.GREATER_THAN_EQUALS, "Bradley");
    assertEquals(true, actualFour); // Bradley >= Adam

    // Test null
    assertThrows(NullPointerException.class, () -> {
      Filters.getFilter(testPlayer1, ColumnData.FIRST_NAME, Operations.GREATER_THAN, null);
    });

    // Empty string
    boolean actualSix = Filters.getFilter(testPlayer1, ColumnData.FIRST_NAME, Operations.LESS_THAN, "");
    assertEquals(false, actualSix); // Seth ~= ""

    // White space
    boolean actualSeven = Filters.getFilter(testPlayer4, ColumnData.FIRST_NAME, Operations.EQUALS, "   Bradley ");
    assertEquals(true, actualSeven); // Bradley == Bradley
  }

  /**
   * Happy getFilter() test cases for last name.
   */
  @Test
  void getFilterLastNameHappyCase() {
    boolean actualOne = Filters.getFilter(testPlayer1, ColumnData.LAST_NAME, Operations.EQUALS, "Beal");
    assertEquals(false, actualOne); // Curry == Beal

    boolean actualTwo = Filters.getFilter(testPlayer3, ColumnData.LAST_NAME, Operations.NOT_EQUALS, "Gilgeous-Alexander");
    assertEquals(false, actualTwo); // Gilgeous-Alexander != Gilgeous-Alexander is false

    boolean actualThree = Filters.getFilter(testPlayer1, ColumnData.LAST_NAME, Operations.CONTAINS, "-Alex");
    assertEquals(false, actualThree); // "Curry" ~= set

    boolean actualFour = Filters.getFilter(testPlayer4, ColumnData.LAST_NAME, Operations.GREATER_THAN_EQUALS, "Doncic");
    assertEquals(false, actualFour); // Beal >= Doncic

    boolean actualFive = Filters.getFilter(testPlayer2, ColumnData.LAST_NAME, Operations.GREATER_THAN, "Collins");
    assertEquals(true, actualFive); // Doncic > Collins

    boolean actualSix = Filters.getFilter(testPlayer5, ColumnData.LAST_NAME, Operations.LESS_THAN, "Grant");
    assertEquals(true, actualSix); // Collins < Grant

    boolean actualSeven = Filters.getFilter(testPlayer6, ColumnData.LAST_NAME, Operations.LESS_THAN_EQUALS, "James");
    assertEquals(true, actualSeven); // Grant <= James
  }

  /**
   * Happy getFilter() test cases for last name.
   */
  @Test
  void getFilterLastNameEdgeCase() {
    // Test case-sensitivity
    boolean actualOne = Filters.getFilter(testPlayer1, ColumnData.LAST_NAME, Operations.EQUALS, "curRY");
    assertEquals(true, actualOne); // Seth == SETH

    boolean actualTwo = Filters.getFilter(testPlayer1, ColumnData.LAST_NAME, Operations.EQUALS, "CURRY");
    assertEquals(true, actualTwo); // Seth == SETH

    // Test single letter
    boolean actualThree = Filters.getFilter(testPlayer1, ColumnData.LAST_NAME, Operations.CONTAINS, "Y");
    assertEquals(true, actualThree); // Curry ~= Y

    // Test equals in greater than equals
    boolean actualFour = Filters.getFilter(testPlayer5, ColumnData.LAST_NAME, Operations.GREATER_THAN_EQUALS, "Beal");
    assertEquals(true, actualFour);

    // Test null
    assertThrows(NullPointerException.class, () -> {
      Filters.getFilter(testPlayer6, ColumnData.LAST_NAME, Operations.GREATER_THAN, null);
    });

    // Empty string
    boolean actualSix = Filters.getFilter(testPlayer1, ColumnData.LAST_NAME, Operations.LESS_THAN, "");
    assertEquals(false, actualSix); // Seth ~= ""

    // White space
    boolean actualSeven = Filters.getFilter(testPlayer5, ColumnData.LAST_NAME, Operations.EQUALS, "   cOllins   ");
    assertEquals(true, actualSeven);
  }

  /**
   * Happy getFilter() test cases for height.
   */
  @Test
  void getFilterHeightHappyCase() {
    boolean actualOne = Filters.getFilter(testPlayer1, ColumnData.HEIGHT, Operations.EQUALS, "6-4");
    assertEquals(false, actualOne);

    boolean actualTwo = Filters.getFilter(testPlayer3, ColumnData.HEIGHT, Operations.NOT_EQUALS, "5-1");
    assertEquals(true, actualTwo);

    boolean actualThree = Filters.getFilter(testPlayer1, ColumnData.HEIGHT, Operations.CONTAINS, "6-0");
    assertEquals(false, actualThree);

    boolean actualFour = Filters.getFilter(testPlayer4, ColumnData.HEIGHT, Operations.GREATER_THAN_EQUALS, "6-0");
    assertEquals(true, actualFour);

    boolean actualFive = Filters.getFilter(testPlayer2, ColumnData.HEIGHT, Operations.GREATER_THAN, "6-10");
    assertEquals(false, actualFive);

    boolean actualSix = Filters.getFilter(testPlayer5, ColumnData.HEIGHT, Operations.LESS_THAN, "6-10");
    assertEquals(false, actualSix);

    boolean actualSeven = Filters.getFilter(testPlayer6, ColumnData.HEIGHT, Operations.LESS_THAN_EQUALS, "5-10");
    assertEquals(false, actualSeven);
  }

  /**
   * Happy getFilter() test cases for height.
   */
  @Test
  void getFilterHeightEdgeCase() {
    // Test trailing spaces
    boolean actualOne = Filters.getFilter(testPlayer1, ColumnData.HEIGHT, Operations.EQUALS, "  6-1  ");
    assertEquals(true, actualOne);

    // Test single letter
    boolean actualThree = Filters.getFilter(testPlayer1, ColumnData.HEIGHT, Operations.CONTAINS, "6");
    assertEquals(true, actualThree);

    // Test equals in greater than equals
    boolean actualFour = Filters.getFilter(testPlayer5, ColumnData.HEIGHT, Operations.GREATER_THAN_EQUALS, "6-11");
    assertEquals(true, actualFour);

    // Test null
    assertThrows(NullPointerException.class, () -> {
      Filters.getFilter(testPlayer6, ColumnData.HEIGHT, Operations.GREATER_THAN, null);
    });

    // Empty string
    boolean actualSix = Filters.getFilter(testPlayer1, ColumnData.HEIGHT, Operations.LESS_THAN, "");
    assertEquals(false, actualSix);

    // White space
    boolean actualSeven = Filters.getFilter(testPlayer5, ColumnData.HEIGHT, Operations.EQUALS, "   6 - 11   ");
    assertEquals(true, actualSeven);
  }

}