package View;

import java.awt.event.ActionListener;

/**
 * Interface to the view.
 */
public interface IView {
    void display(String text);

    void setListeners(ActionListener clicks);

    String getInputString();

    void clearInputField();

    void clearDisplay();

    void start();
}
