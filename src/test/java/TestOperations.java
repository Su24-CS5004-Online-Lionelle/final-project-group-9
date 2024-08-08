import static org.junit.jupiter.api.Assertions.*;
import Model.SortFilter.Operations;
import org.junit.jupiter.api.Test;

class TestOperations {

  /**
   * Test getting string from Operations operator.
   */
  @Test
  void getOperator() {
    assertEquals("==", Operations.EQUALS.getOperator());
    assertEquals("!=", Operations.NOT_EQUALS.getOperator());
    assertEquals("~=", Operations.CONTAINS.getOperator());
    assertEquals(">", Operations.GREATER_THAN.getOperator());
    assertEquals(">=", Operations.GREATER_THAN_EQUALS.getOperator());
    assertEquals("<", Operations.LESS_THAN.getOperator());
    assertEquals("<=", Operations.LESS_THAN_EQUALS.getOperator());
  }

  /**
   * Tests getting operators from string for parsed values.
   */
  @Test
  void getOperatorFromStr() {
    assertEquals(Operations.EQUALS, Operations.getOperatorFromStr("=="));
    assertEquals(Operations.NOT_EQUALS, Operations.getOperatorFromStr("!="));
    assertEquals(Operations.GREATER_THAN, Operations.getOperatorFromStr(">"));
    assertEquals(Operations.GREATER_THAN_EQUALS, Operations.getOperatorFromStr(">="));
    assertEquals(Operations.LESS_THAN, Operations.getOperatorFromStr("<"));
    assertEquals(Operations.LESS_THAN, Operations.getOperatorFromStr("<="));
    assertEquals(Operations.CONTAINS, Operations.getOperatorFromStr("~="));
  }
}