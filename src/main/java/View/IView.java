package View;

import Model.SortFilter.ColumnData;

import java.awt.event.ActionListener;

/**
 * Interface to the view.
 */
public interface IView {

    /**
     * Displays information to the lower panel of the JFrame.
     * @param text String that the user wants to display.
     */
    void display(String text);

    /**
     * Sets up ActionListeners for JButtons.
     * @param clicks Button clicks
     */
    void setListeners(ActionListener clicks);

    /**
     * Gets the String input from the JTextField.
     * @return String user input
     */
    String getInputString();

    /**
     * Gets the user selected filter choice from JComboBox.
     * @return ColumnData filter enum
     */
    ColumnData getFilterChoice();

    /**
     * Gets the user selected sort choice from JComboBox.
     * @return boolean true for Ascending, false for Descending
     */
    boolean getSortChoice();

    /**
     * Clears the JTextField.
     */
    void clearInputField();

    /**
     * Clears the lower panel display in the JFrame.
     */
    void clearDisplay();

    /**
     * Help message providing instructions on how to use the program.
     * @return String instructions
     */
    String getHelp();

    /**
     * Sets JFrame as visible.
     */
    void start();
}
