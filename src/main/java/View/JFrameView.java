package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class JFrameView extends JFrame implements IView {
    private JLabel prompt;
    private JTextField input;
    private JComboBox<String> dropDown, sortChoice;
    private List<String> items, sortOrder;
    private JButton searchButton, showListButton, loadButton, exportButton, clearButton, exitButton;
    private JTextArea lowerTextArea;


    public JFrameView(String caption) {
        super(caption);

        setSize(700, 700);
        // centers window
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        // adding text prompt to enter search criteria
        prompt = new JLabel("Enter search criteria:");
        prompt.setAlignmentX((JComponent.CENTER_ALIGNMENT));
        prompt.setFont(new Font("Arial", Font.PLAIN, 18));
        this.add(prompt);

        // sub-panel to hold JTextField and JComboBox
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

        // setting sort order list
        sortOrder = new ArrayList<>();
        sortOrder.add("Ascending");
        sortOrder.add("Descending");

        dropDown = new JComboBox<>(items.toArray(new String[0]));

        dropDown.setMaximumSize(dropDown.getPreferredSize());
        searchPanel.add(dropDown);

        sortChoice = new JComboBox<>(sortOrder.toArray(new String[0]));
        searchPanel.add(sortChoice);

        // Add search button
        searchButton = new JButton("Search");
        searchButton.setActionCommand("search");
        searchPanel.add(searchButton);

        // Add searchPanel to the main JFrame
        searchPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(searchPanel);

        // Create sub-panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        // Add the buttons
        showListButton = new JButton("Show Current List");
        showListButton.setActionCommand("showList");
        buttonPanel.add(showListButton);

        loadButton = new JButton("Load List");
        loadButton.setActionCommand("load");
        buttonPanel.add(loadButton);

        exportButton = new JButton("Export Current List");
        exportButton.setActionCommand("export");
        buttonPanel.add(exportButton);

        clearButton = new JButton("Clear Display");
        clearButton.setActionCommand("clear");
        buttonPanel.add(clearButton);

        exitButton = new JButton("Exit Program");
        exitButton.setActionCommand("exit");
        buttonPanel.add(exitButton);

        // Add buttonPanel to the main JFrame
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(buttonPanel);

        // Create lower display panel
        JPanel lowerPanel = new JPanel();
        lowerPanel.setBackground(Color.LIGHT_GRAY);
        lowerPanel.setLayout(new BorderLayout());

        // Initialize JTextArea
        lowerTextArea = new JTextArea();
        lowerTextArea.setEditable(false);

        // Add scroll bar to lower panel
        lowerPanel.add(new JScrollPane(lowerTextArea), BorderLayout.CENTER);

        // Add lower panel to the frame
        this.add(lowerPanel);

    }

    @Override
    public void display(String text) {
        lowerTextArea.append(text + "\n");
    }

    @Override
    public void setListeners(ActionListener clicks) {
        this.searchButton.addActionListener(clicks);
        this.showListButton.addActionListener(clicks);
        this.loadButton.addActionListener(clicks);
        this.exportButton.addActionListener(clicks);
        this.clearButton.addActionListener(clicks);
        this.exitButton.addActionListener(clicks);
    }

    @Override
    public String getInputString() {
        return input.getText();
    }

    @Override
    public void clearInputField() {
        input.setText("");
    }

    @Override
    public void clearDisplay() {
        lowerTextArea.setText("");
    }

    @Override
    public void start() {
        setVisible(true);
    }
}
