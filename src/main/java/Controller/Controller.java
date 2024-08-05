package Controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
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
  private String playerName = "all";

  /**
   * Constructor for the controller with the specified view and model.
   * 
   * @param view  the view interface
   * @param model the model interface
   */
  public Controller(IView view, IModel model) {
    this.view = view;
    this.model = model;
    view.setListeners(this); // Sets this controller as listener for the view
  }

    // @Override
    // public void actionPerformed(ActionEvent e) {
    //   // get input string
    //   String inputString = view.getInputString();

    //   // Get filter criteria
    //   ColumnData selectedFilter = view.getFilterChoice();

    //   // get sort criteria
    //   boolean selectedSort = view.getSortChoice();

    //   // Get filter/sorted list of players
    //   Set<Player> filteredPlayers = model.filterSortNBARoster(inputString, selectedFilter, selectedSort);

    //   String players = "";

    //   switch (e.getActionCommand()) {
    //     case "search":
    //       // Clear input field
    //       view.clearInputField();

    //       // Clear display field
    //       view.clearDisplay();

    //       for (Player player : filteredPlayers) {
    //         switch (selectedFilter) {
    //           case FIRST_NAME:
    //           case LAST_NAME:
    //             players = players + player.getFirstName() + player.getLastName();
    //             break;
    //           case POSITION:
    //             players = players + player.getFirstName() + player.getLastName() + player.getPosition();
    //             break;
    //           case HEIGHT:
    //             players = players + player.getFirstName() + player.getLastName() + player.getHeight();
    //             break;
    //           case DRAFTYEAR:
    //             players = players + player.getFirstName() + player.getLastName() + player.getDraftYear();
    //             break;
    //           case DRAFTROUND:
    //             players = players + player.getFirstName() + player.getLastName() + player.getDraftRound();
    //             break;
    //           case DRAFTPICK:
    //             players = players + player.getFirstName() + player.getLastName() + player.getDraftPick();
    //             break;
    //           case TEAM:
    //             players = players + player.getFirstName() + player.getLastName() + player.getTeam();
    //             break;
    //           case CONFERENCE:
    //             players = players + player.getFirstName() + player.getLastName() + player.getConference();
    //             break;
    //           case PPG:
    //             players = players + player.getFirstName() + player.getLastName() + player.getPpg();
    //             break;
    //           case RPG:
    //             players = players + player.getFirstName() + player.getLastName() + player.getRpg();
    //             break;
    //           case APG:
    //             players = players + player.getFirstName() + player.getLastName() + player.getApg();
    //             break;
    //           case BPG:
    //             players = players + player.getFirstName() + player.getLastName() + player.getBpg();
    //             break;
    //           case SPG:
    //             players = players + player.getFirstName() + player.getLastName() + player.getSpg();
    //             break;
    //           case MPG:
    //             players = players + player.getFirstName() + player.getLastName() + player.getMpg();
    //             break;
    //           case FGP:
    //             players = players + player.getFirstName() + player.getLastName() + player.getFgp();
    //           case FTP:
    //             players = players + player.getFirstName() + player.getLastName() + player.getFtp();
    //             break;
    //           case FP3P:
    //             players = players + player.getFirstName() + player.getLastName() + player.getFg3p();
    //             break;
    //         }
    //     }
    //       view.display(players);
    //       break;

    //     case "add":
    //       model.buildRoster(filteredPlayers, inputString);
    //       break;

    //     case "remove":
    //       model.removeFromRoster(inputString);
    //       break;

    //     case "showRoster":
    //       for (Player player : model.getRoster()) {
    //         players = players + player.getFirstName() + player.getLastName();
    //       }
    //       view.display(players);
    //       break;

    //     case "export":
    //       JFileChooser fileChooser = new JFileChooser();
    //       fileChooser.setAcceptAllFileFilterUsed(false);
    //       fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
    //       fileChooser.setDialogTitle("Export List");

    //       // Add file filters for the different formats
    //       fileChooser.setFileFilter(new FileNameExtensionFilter("XML (*.xml)", "xml"));
    //       fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("JSON (*.json)", "json"));
    //       fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("CSV (*.csv)", "csv"));
    //       fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Text (*.txt)", "txt"));

    //       // Open save dialog. If user approves the save action, if block below will execute
    //       int userSelection = fileChooser.showSaveDialog(this);

    //       if (userSelection == JFileChooser.APPROVE_OPTION) {
    //         File file = fileChooser.getSelectedFile();
    //         String filePath = file.getAbsolutePath();
    //         FileNameExtensionFilter selectedFileFilter = (FileNameExtensionFilter) fileChooser.getFileFilter();
    //         String extension = getFileExtension(filePath);

    //         // Check if the file extension is missing or incorrect
    //         if (extension.isEmpty() || !selectedFileFilter.getExtensions()[0].equals(extension)) {
    //           // Append the file extension
    //           filePath += "." + selectedFileFilter.getExtensions()[0];
    //           file = new File(filePath);
    //         }

    //         Format format = null;
    //         switch (selectedFileFilter.getExtensions()[0]) {
    //           case "xml":
    //             format = Format.XML;
    //             break;
    //           case "json":
    //             format = Format.JSON;
    //             break;
    //           case "csv":
    //             format = Format.CSV;
    //             break;
    //           case "txt":
    //             format = Format.PRETTY;
    //             break;
    //         }

    //         try (OutputStream os = new FileOutputStream(file)) {
    //           if (format != null) {
    //             DataFormatter.write(model.getRoster(), format, os);
    //             view.clearDisplay();
    //             view.display("Exported to " + file.getAbsolutePath());
    //           }
    //         } catch (IOException ex) {
    //           throw new RuntimeException(ex);
    //         }
    //       }
    //       break;

    //     case "load":
    //       IModel newModel = new Model(inputString);
    //       setModel(newModel);
    //       break;

    //     case "clear":
    //       view.display("Clear button clicked");
    //       view.clearDisplay();
    //       break;

    //     case "help":
    //       view.display(getHelp());
    //       break;
        
    //     case "exit":
    //       System.exit(0);
    //       break;
    //   }
    // }

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
          view.display(getHelp());
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
    Set<Player> filteredPlayers = model.filterSortNBARoster(inputString, selectedFilter, selectedSort);
    displayPlayers(filteredPlayers, selectedFilter);
  }

  /**
   * Adds players to the roster based on the provided input from the user, filter selected, and order to sort in.
   * 
   * @param inputString the input string given by the user
   * @param selectedFilter  the filter chose by the user
   * @param selectedSort  the sorting order chosen by the user
   */
  private void addMethod(String inputString, ColumnData selectedFilter, boolean selectedSort) {
    Set<Player> filteredPlayers = model.filterSortNBARoster(inputString, selectedFilter, selectedSort);
    model.buildRoster(filteredPlayers, inputString);
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
    fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Text (*.txt)", "txt"));

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
    IModel newModel = new Model(inputString);
    setModel(newModel);
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
    for (Player player : players) {
      playersString.append(formatPlayer(player, filter)).append("\n");
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
   * Provide a help menu to guide user on how to use the program.
   *
   * @return a string containing instructions on what the program does/how to use it
   */
  public String getHelp() {
    return "Instructions: \n"
            + "1. There is an empty text field you can type into to search for specific players.\n"
            + "2. Next to it is a drop down menu that you can use to filter your search.\n" 
            + "E.g. If you want to see all the players in the league with the first name 'Ray', you'd select 'First Name'"
            + " from the drop down menu and type 'Ray' into the text field.\n"
            + "There are many different filter options to pick from, most of which will be integers or doubles.\n"
            + "You can also use operators to help refine your search (==, !=, ~=, >=, <=, >, <) \n"
            + "E.g. If you want to see players who were drafted in the year 2000, you'd select the 'Draft Year' option"
            + " from the drop down menu and type 2000 into the text field.\n"
            + "If you wanted to see the players who were drafted after the year 2000,"
            + "you'd type '> 2000' (not including year 2000) or '>= 2000' (including year 2000) into the text field.\n"
            + "3. You can adjust the order that your search results are displayed. Either ascending (first to last) or descending (last to first).\n"
            + "4. The 'Add' button allows you to add a player or a range of players to your roster.\n"
            + "5. The 'Remove' button allows you to remove a player or a range of players from your roster.\n"
            + "6. The 'Show Current List' button shows you the list of players you have added to your roster.\n"
            + "7. The 'Export Current List' button saves your current list of players to a specified file format.\n"
            + "8. The 'Load List' button loads a roster of players based on the file name given.\n"
            + "9. The 'Clear Display' button clears the display of any information.\n"
            + "10. The 'Exit Program' button closes the GUI.\n";
  }

  /**
   * Sets the model to the specified model.
   * 
   * @param model the model to set
   */
  public void setModel(IModel model) {
    this.model = model;
  }
}
