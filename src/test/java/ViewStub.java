import Model.SortFilter.ColumnData;
import Model.Player;
import Model.PlayerBean;
import Model.IModel;
import View.IView;

import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ViewStub implements IView {
  private String inputString;
  private ColumnData filterChoice;
  private boolean sortChoice;
  private StringBuilder displayContent = new StringBuilder();
  private ActionListener actionListener;

  public void setInput(String inputString, ColumnData filterChoice, boolean sortChoice) {
    this.inputString = inputString;
    this.filterChoice = filterChoice;
    this.sortChoice = sortChoice;
  }

  @Override
  public String getInputString() {
    return inputString;
  }

  @Override
  public ColumnData getFilterChoice() {
    return filterChoice;
  }

  @Override
  public boolean getSortChoice() {
    return sortChoice;
  }

  @Override
  public void display(String message) {
    displayContent.append(message);
  }

  public StringBuilder getDisplayContent() {
    return displayContent;
  }

  @Override
  public void clearDisplay() {
    displayContent.setLength(0);
  }

  @Override
  public void setListeners(ActionListener actionListener) {
    this.actionListener = actionListener;
  }

  @Override
  public void clearInputField() {
    this.inputString = "";
    this.filterChoice = null; // or set to default value
    this.sortChoice = false; // or set to default value
  }

  @Override
  public String getHelp() {
    return "Help";
  }

  @Override
  public void start() {
    // This method might be intended to start the view or GUI, leave it empty for the stub
  }
}
