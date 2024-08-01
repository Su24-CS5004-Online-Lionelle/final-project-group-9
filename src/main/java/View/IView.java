package View;

import Model.SortFilter.ColumnData;

import java.awt.event.ActionListener;

/**
 * Interface to the view.
 */
public interface IView {
    void display(String text);

    void setListeners(ActionListener clicks);

    String getInputString();

    ColumnData getFilterChoice();

    boolean getSortChoice();

    void clearInputField();

    void clearDisplay();

    void start();
}
