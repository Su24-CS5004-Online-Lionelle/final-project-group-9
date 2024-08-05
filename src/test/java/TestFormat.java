import static org.junit.jupiter.api.Assertions.*;
import java.text.Normalizer;
import Model.Format.Format;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestFormat {

  String json = "";
  String xml;
  String preTTy;
  String cSV;
  String CsW;
  String notPretty;

  @BeforeEach
  void setUp() {
  }

  /**
   * Checks if String equal value.
   */
  @Test
  void containsValues() {
    // Case-insensitive
    assertEquals(Format.JSON, Format.containsValues("json"));
    assertEquals(Format.XML, Format.containsValues("xML"));
    assertEquals(Format.PRETTY, Format.containsValues("preTTY"));
    assertEquals(Format.CSV, Format.containsValues("CSV"));
  }

  /**
   * Test that string does not equal value.
   */
  @Test
  void DoesNotContainsValues() {
    assertEquals(null, Format.containsValues("j son"));
    assertEquals(null, Format.containsValues("NOTxML"));
    assertEquals(null, Format.containsValues("XML preTTY"));
    assertEquals(null, Format.containsValues(" csv"));
  }
}