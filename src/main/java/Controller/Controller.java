package Controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Set;

import Model.IModel;
import Model.Model;
import Model.PlayerBean;
import Model.Player;
import Model.SortFilter.ColumnData;
import View.IView;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Controller extends Component implements ActionListener {
  private IView view;
  private IModel model;
  private String playerName = "all";

  public Controller(IView view, IModel model) {
    this.view = view;
    this.model = model;
    view.setListeners(this); // Sets this controller as listener for the view
  }

    @Override
    public void actionPerformed(ActionEvent e) {
      // get input string
      String inputString = view.getInputString();

      // Get filter criteria
      ColumnData selectedFilter = view.getFilterChoice();

      // get sort criteria
      boolean selectedSort = view.getSortChoice();

      // Get filter/sorted list of players
      Set<Player> filteredPlayers = model.filterSortNBARoster(inputString, selectedFilter, selectedSort);

      String players = "";

      switch (e.getActionCommand()) {
        case "search":
          // Clear input field
          view.clearInputField();

          // Clear display field
          view.clearDisplay();

          for (Player player : filteredPlayers) {
            switch (selectedFilter) {
              case NAME:
                players = players + player.getName();
                break;
              case AGE:
                players = players + player.getName() + player.getAge();
                break;
              case POSITION:
                players = players + player.getName() + player.getPosition();
                break;
              case HEIGHT:
                players = players + player.getName() + player.getHeight();
                break;
              case DRAFTYEAR:
                players = players + player.getName() + player.getDraftYear();
                break;
              case DRAFTROUND:
                players = players + player.getName() + player.getDraftRound();
                break;
              case DRAFTPICK:
                players = players + player.getName() + player.getDraftPick();
                break;
              case TEAM:
                players = players + player.getName() + player.getTeam();
                break;
              case CONFERENCE:
                players = players + player.getName() + player.getConference();
                break;
              case PPG:
                players = players + player.getName() + player.getPpg();
                break;
              case RPG:
                players = players + player.getName() + player.getRpg();
                break;
              case APG:
                players = players + player.getName() + player.getApg();
                break;
              case BPG:
                players = players + player.getName() + player.getBpg();
                break;
              case SPG:
                players = players + player.getName() + player.getSpg();
                break;
              case MPG:
                players = players + player.getName() + player.getMpg();
                break;
              case FTP:
                players = players + player.getName() + player.getFtp();
                break;
              case FP3P:
                players = players + player.getName() + player.getFg3p();
                break;
            }
        }
          view.display(players);
          break;

        case "add":
          model.buildRoster(filteredPlayers, inputString);
          break;

        case "remove":
          model.removeFromRoster(inputString);
          break;

        case "showRoster":
          for (Player player : model.getRoster()) {
            players = players + player.getName();
          }
          view.display(players);
          break;

        case "export":
          JFileChooser fileChooser = new JFileChooser();
          fileChooser.setAcceptAllFileFilterUsed(false);
          fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
          fileChooser.setDialogTitle("Export List");

          // Add file filters for the different formats
          fileChooser.setFileFilter(new FileNameExtensionFilter("XML (*.xml)", "xml"));
          fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("JSON (*.json)", "json"));
          fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("CSV (*.csv)", "csv"));
          fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Text (*.txt)", "txt"));

          // Open save dialog. If user approves the save action, if block below will execute
          int userSelection = fileChooser.showSaveDialog(this);

          if (userSelection == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            String filePath = file.getAbsolutePath();
            FileNameExtensionFilter selectedFileFilter = (FileNameExtensionFilter) fileChooser.getFileFilter();
            String extension = getFileExtension(filePath);

            // Check if the file extension is missing or incorrect
            if (extension.isEmpty() || !selectedFileFilter.getExtensions()[0].equals(extension)) {
              // Append the file extension
              filePath += "." + selectedFileFilter.getExtensions()[0];
              file = new File(filePath);
            }

            Formats format = null;
            switch (selectedFileFilter.getExtensions()[0]) {
              case "xml":
                format = Formats.XML;
                break;
              case "json":
                format = Formats.JSON;
                break;
              case "csv":
                format = Formats.CSV;
                break;
              case "txt":
                format = Formats.PRETTY;
                break;
            }

            try (OutputStream os = new FileOutputStream(file)) {
              if (format != null) {
                DataFormatter.write(model.getRoster(), format, os);
                view.clearDisplay();
                view.display("Exported to " + file.getAbsolutePath());
              }
            } catch (IOException ex) {
              throw new RuntimeException(ex);
            }
          }
          break;

        case "load":
          IModel newModel = new Model(inputString);
          setModel(newModel);
          break;

        case "clear":
          view.display("Clear button clicked");
          view.clearDisplay();
          break;

        case "exit":
          System.exit(0);
          break;
      }
    }

  /**
   * Provide a help menu to guide user on how to use the program.
   *
   * @return a string containing instructions on what the program does/how to use it
   */
  public String getHelp() {
    throw new UnsupportedOperationException("getHelp() Not implemented yet.");
  }

  public void setModel(IModel model) {
    this.model = model;
  }

  private String getFileExtension(String filePath) {
    int lastIndexOfDot = filePath.lastIndexOf('.');
    // empty extension
    if (lastIndexOfDot == -1) {
      return "";
    }
    return filePath.substring(lastIndexOfDot + 1).toLowerCase();
  }

//  public void addPlayer(String playerName) {
//    Player player = model.getPlayer(playerName);
////    if (player != null) {
////      model.getUserRoster().add(player);
////    }
//  }
//
//  public Player getPlayer(String playerName, boolean add) {
//    // throw new UnsupportedOperationException("getPlayer() Not implemented yet.");
//    Player player = model.getPlayer(playerName);
//
//
//
//  }
//
//  public String listAllPlayers(String playerName) {
//    throw new UnsupportedOperationException("listAllPlayers() Not implemented yet.");
//  }
//
//  public String generateRoster() {
//    Set<Player> userRoster = model.getRoster();
//    StringBuilder result = new StringBuilder("Generated Roster:\n");
//    for (Player player : userRoster) {
//      result.append(player.toString()).append("\n");
//    }
//    return result.toString();
//  }
//
//  public String filterPlayers(String filterOperation) {
//    throw new UnsupportedOperationException("filterPlayers() Not implemented yet.");
//  }
//
//  public String removePlayerFromRoster(String playerName) {
//    Set<Player> userRoster = getUserRoster();
//    Player removePlayer = null;
//    for (Player player : userRoster) {
//      if (player.getName().equalsIgnoreCase(playerName)) {
//        removePlayer = player;
//        break;
//      }
//    }
//    if (removePlayer != null) {
//      userRoster.remove(removePlayer);
//      return "Player: " + playerName + "has been removed from the roster.";
//    } else {
//      return "Player was not found in the roster.";
//    }
//  }
//
//  public void exportPlayers() {
//    throw new UnsupportedOperationException("exportPlayers() Not implemented yet.");
//    Set<Player> userRoster = model.getUserRoster(); // might need to add this to Model
//
//  }

  // find some way to parse the argument input to the text field
}
