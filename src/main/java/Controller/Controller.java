package Controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Set;

import Model.Format.DataFormatter;
import Model.Format.Format;
import Model.IModel;
import Model.Model;
import Model.PlayerBean;
import Model.Player;
import Model.SortFilter.ColumnData;
import View.IView;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * The controller class handles user interaction and updates the view and model.
 */
public class Controller extends Component implements ActionListener {
  private IView view;
  private IModel model;
  private Set<Player> filterSortedSet;

  /**
   * Constructor for the controller with the specified view and model.
   *
   * @param view  the view interface
   * @param model the model interface
   */
  public Controller(IView view, IModel model) {
    this.view = view;
    this.model = model;
    this.filterSortedSet = filterSortedSet;
    view.setListeners(this); // Sets this controller as listener for the view
  }

  /**
   * Handles the action events from the view.
   *
   * @param e the action event
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    // Gets the input string
    String inputString = view.getInputString();
    // Gets the choice of filter
    ColumnData selectedFilter = view.getFilterChoice();
    // Gets the sort order
    boolean selectedSort = view.getSortChoice();

    try {
      switch (e.getActionCommand()) {
        case "search":
          searchMethod(inputString, selectedFilter, selectedSort);
          break;
        case "add":
          addMethod(inputString, selectedFilter, selectedSort);
          break;
        case "remove":
          removeMethod(inputString);
          break;
        case "showRoster":
          showRosterMethod();
          break;
        case "export":
          exportMethod();
          break;
        case "load":
          loadMethod(inputString);
          break;
        case "clear":
          clearMethod();
          break;
        case "exit":
          System.exit(0);
          break;
        case "help":
          view.display(view.getHelp());
          break;
        default:
          view.display("Unknown Command: " + e.getActionCommand());
      }
    } catch (Exception ex) {
      view.display("An error has occurred: " + ex.getMessage());
    }
  }

  /**
   * Searches for players based on the provided input from the user, filter selected, and order to sort in.
   *
   * @param inputString the input string given by the user
   * @param selectedFilter  the filter chosen by the user
   * @param selectedSort  the sorting order chosen by the user
   */
  private void searchMethod(String inputString, ColumnData selectedFilter, boolean selectedSort) {
    setFilterSortedSet(model.filterSortNBARoster(inputString, selectedFilter, selectedSort));
    displayPlayers(getFilterSortedSet(), selectedFilter);
  }

  /**
   * Adds players to the roster based on the provided input from the user, filter selected, and order to sort in.
   *
   * @param inputString the input string given by the user
   * @param selectedFilter  the filter chose by the user
   * @param selectedSort  the sorting order chosen by the user
   */
  private void addMethod(String inputString, ColumnData selectedFilter, boolean selectedSort) {
//    Set<Player> filteredPlayers = model.filterSortNBARoster(inputString, selectedFilter, selectedSort);
    model.buildRoster(getFilterSortedSet(), inputString);
  }

  /**
   * Removes the players from the roster based on the provided input from the user.
   *
   * @param inputString the input string given by the user
   */
  private void removeMethod(String inputString) {
    model.removeFromRoster(inputString);
  }

  /**
   * Shows the current roster.
   */
  private void showRosterMethod() {
    Set<Player> roster = model.getRoster();
    displayPlayers(roster, null);
  }

  /**
   * Exports the current roster to a file in the specified file format.
   *
   * @throws IOException if an I/O error occurs
   */
  private void exportMethod() throws IOException {
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setAcceptAllFileFilterUsed(false);
    fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
    fileChooser.setDialogTitle("Export List");

    // Add file filters for the different formats
    fileChooser.setFileFilter(new FileNameExtensionFilter("XML (*.xml)", "xml"));
    fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("JSON (*.json)", "json"));
    fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("CSV (*.csv)", "csv"));

    int userSelection = fileChooser.showSaveDialog(this);

    if (userSelection == JFileChooser.APPROVE_OPTION) {
      File file = fileChooser.getSelectedFile();
      String filePath = file.getAbsolutePath();
      FileNameExtensionFilter selectedFileFilter = (FileNameExtensionFilter) fileChooser.getFileFilter();
      String extension = getFileExtension(filePath);

      if (extension.isEmpty() || !selectedFileFilter.getExtensions()[0].equals(extension)) {
        filePath += "." + selectedFileFilter.getExtensions()[0];
        file = new File(filePath);
      }

      Format format = determineFormat(selectedFileFilter);
      if (format != null) {
        try (OutputStream os = new FileOutputStream(file)) {
          DataFormatter.write(model.getRoster(), format, os);
          view.clearDisplay();
          view.display("Exported to " + file.getAbsolutePath());
        }
      }
    }
  }

  /**
   * Loads a new roster based on the provided input from the user.
   *
   * @param inputString the input string given by the user
   */
  private void loadMethod(String inputString) {
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
    fileChooser.setDialogTitle("Load Roster");

    // Add file filters for the different formats
    fileChooser.setFileFilter(new FileNameExtensionFilter("JSON (*.json)", "json"));
    fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("XML (*.xml)", "xml"));
    fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("CSV (*.csv)", "csv"));

    int result = fileChooser.showOpenDialog(this);

    if (result == JFileChooser.APPROVE_OPTION) {
      File selectedFile = fileChooser.getSelectedFile();
      String filePath = selectedFile.getAbsolutePath();
      String fileExtension = getFileExtension(filePath);

      IModel newModel = new Model(filePath);

      setModel(newModel);

      view.display("Loaded roster from " + filePath);
    }
  }

  /**
   * Clears the display.
   */
  private void clearMethod() {
    view.display(("Clear button clicked"));
    view.clearDisplay();
  }

  /**
   * Displays the specified set of players.
   *
   * @param players the set of players to display
   * @param filter  the filter used for displaying player information
   */
  private void displayPlayers(Set<Player> players, ColumnData filter) {
    view.clearInputField();
    view.clearDisplay();
    StringBuilder playersString = new StringBuilder();
    List<Player> playersList = players.stream().toList();
    for (int i = 0; i < players.size(); i++) {
      playersString.append((i+1) + ". " + formatPlayer(playersList.get(i), filter)).append("\n");
    }
    view.display(playersString.toString());
  }

  /**
   * Formats the player's information based on the chosen filter.
   *
   * @param player  the player
   * @param filter  the chosen filter
   * @return  formatted player's information
   */
  private String formatPlayer(Player player, ColumnData filter) {
    StringBuilder playerString = new StringBuilder();
    playerString.append(player.getFirstName()).append(" ").append(player.getLastName());

    if (filter != null) {
      switch (filter) {
        case FIRST_NAME:
        case LAST_NAME:
          break;
        case POSITION:
          playerString.append(", Position: ").append(player.getPosition());
          break;
        case HEIGHT:
          playerString.append(", Height: ").append(player.getHeight());
          break;
        case DRAFTYEAR:
          playerString.append(", Draft Year: ").append(player.getDraftYear());
          break;
        case DRAFTROUND:
          playerString.append(", Draft Round: ").append(player.getDraftRound());
          break;
        case DRAFTPICK:
          playerString.append(", Draft Pick: ").append(player.getDraftPick());
          break;
        case TEAM:
          playerString.append(", Team: ").append(player.getTeam());
          break;
        case CONFERENCE:
          playerString.append(", Conference: ").append(player.getConference());
          break;
        case PPG:
          playerString.append(", PPG: ").append(player.getPpg());
          break;
        case RPG:
          playerString.append(", RPG: ").append(player.getRpg());
          break;
        case APG:
          playerString.append(", APG: ").append(player.getApg());
          break;
        case BPG:
          playerString.append(", BPG: ").append(player.getBpg());
          break;
        case SPG:
          playerString.append(", SPG: ").append(player.getSpg());
          break;
        case MPG:
          playerString.append(", MPG: ").append(player.getMpg());
          break;
        case FGP:
          playerString.append(", FGP: ").append(player.getFgp());
          break;
        case FTP:
          playerString.append(", FTP: ").append(player.getFtp());
          break;
        case FP3P:
          playerString.append(", FP3P: ").append(player.getFg3p());
          break;
      }
    }
    return playerString.toString();
  }

  /**
   * Determines the file format based on the chosen file filter.
   *
   * @param fileFilter  the chosen file filter
   * @return  the format
   */
  private Format determineFormat(FileNameExtensionFilter fileFilter) {
    switch (fileFilter.getExtensions()[0]) {
      case "xml":
        return Format.XML;
      case "json":
        return Format.JSON;
      case "csv":
        return Format.CSV;
      case "txt":
        return Format.PRETTY;
      default:
        return null;
    }
  }

  /**
   * Gets the file extension from the specified file path.
   *
   * @param filePath the file path
   * @return the file
   */
  private String getFileExtension(String filePath) {
    int lastIndexOfDot = filePath.lastIndexOf('.');
    // empty extension
    if (lastIndexOfDot == -1) {
      return "";
    }
    return filePath.substring(lastIndexOfDot + 1).toLowerCase();
  }

  /**
   * Sets the model to the specified model.
   *
   * @param model the model to set
   */
  public void setModel(IModel model) {
    this.model = model;
  }

  public Set<Player> getFilterSortedSet() {
    return this.filterSortedSet;
  }

  public void setFilterSortedSet(Set<Player> filteredSet) {
    this.filterSortedSet = filteredSet;
  }
}


