import static org.junit.jupiter.api.Assertions.*;
import Model.IModel;
import Model.Model;
import Model.Player;
import Model.SortFilter.ColumnData;
import Model.SortFilter.Filters;
import Model.SortFilter.Operations;
import org.junit.jupiter.api.BeforeEach;
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


  @Test
  void getFilterHeight() {
    boolean expected = Filters.getFilter(testPlayer1, ColumnData.HEIGHT, Operations.GREATER_THAN, "6-2");
    assertEquals(false, expected);

    boolean expected2 = Filters.getFilter(testPlayer3, ColumnData.HEIGHT, Operations.EQUALS, "6-6");
    assertEquals(true, expected2);


  }

  @Test
  void alphaHeight() {
    IModel model1 = new Model("data/database.json");

    model1.filterSortNBARoster(">6-0", ColumnData.HEIGHT, true);

  }
}