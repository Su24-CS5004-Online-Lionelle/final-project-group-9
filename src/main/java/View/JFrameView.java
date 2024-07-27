package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class JFrameView extends JFrame implements IView {
    private JLabel search;
    private JTextField input;
    private JComboBox<String> dropDown;
    private List<String> items;


    public JFrameView(String caption) {
        super(caption);

        setSize(700, 700);
        // centers window
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        // adding text prompt to enter search criteria
        search = new JLabel("Enter search criteria:");
        search.setAlignmentX((JComponent.CENTER_ALIGNMENT));
        search.setFont(new Font("Arial", Font.PLAIN, 18));
        this.add(search);

        // panel to hold JTextField and JComboBox
        JPanel searchPanel = new JPanel();

        // adding input text field
        input = new JTextField(20);
        // places textfield below JLabel
        input.setMaximumSize(input.getPreferredSize());
        searchPanel.add(input);

        // JComboBox setup
        // setting drop down items
        items = new ArrayList<>();
        items.add("Player Name");
        items.add("Age");
        items.add("Team");
        items.add("Conference");
        items.add("Points per game");
        items.add("Rebounds per game");
        items.add("Assists per game");
        items.add("Blocks per game");
        items.add("Steals per game");
        items.add("Field goal percentage");
        items.add("3 point percentage");

        dropDown = new JComboBox<>(items.toArray(new String[0]));

        dropDown.setMaximumSize(dropDown.getPreferredSize());
        searchPanel.add(dropDown);

        // Add searchPanel to the main JFrame
        searchPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(searchPanel);

    }

    @Override
    public void display(String text) {

    }

    @Override
    public void setListeners(ActionListener clicks) {

    }

    @Override
    public String getInputString() {
        return "";
    }

    @Override
    public void clearInputField() {

    }

    @Override
    public void clearDisplay() {

    }

    @Override
    public void start() {
        setVisible(true);
    }
}
