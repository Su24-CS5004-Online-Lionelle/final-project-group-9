package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import Model.IModel;
import Model.PlayerBean;
import View.IView;

public class Controller implements ActionListener {
  private IView view;
  private IModel model;
  private String playerName = "all";

  public Controller(IView view, IModel model) {
    this.view = view;
    this.model = model;
    this.view.setListeners(this); // Sets this controller as listener for the view
  }


  @Override
  public void actionPerformed(ActionEvent e) {
    String command = e.getActionCommand();
    view.display(processCommands(command));
  }

  public String processCommands(String command) {
    switch(command) {
      case "search":
        // do something
      case "add":
        // do something
      case "remove":
        // do something
      case "showList":
        // do something
      case "export":
        // do something
      case "load":
        // do something
      case "clear":
        // do something
      case "exit":
        System.exit(0);
      default:
        return "The command given was invalid.";
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

  public void addPlayer(String playerName) {
    Player player = model.getPlayer(playerName);
    if (player != null) {
      model.getUserRoster().add(player);
    }
  }

  public Player getPlayer(String playerName, boolean add) {
    // throw new UnsupportedOperationException("getPlayer() Not implemented yet.");
    Player player = model.getPlayer(playerName);



  }

  public String listAllPlayers(String playerName) {
    throw new UnsupportedOperationException("listAllPlayers() Not implemented yet.");
  }

  public String generateRoster() {
    Set<Player> userRoster = getUserRoster();
    StringBuilder result = new StringBuilder("Generated Roster:\n");
    for (Player player : userRoster) {
      result.append(player.toString()).append("\n");
    }
    return result.toString();
  }

  public String filterPlayers(String filterOperation) {
    throw new UnsupportedOperationException("filterPlayers() Not implemented yet.");
  }

  public String removePlayerFromRoster(String playerName) {
    Set<Player> userRoster = getUserRoster();
    Player removePlayer = null;
    for (Player player : userRoster) {
      if (player.getName().equalsIgnoreCase(playerName)) {
        removePlayer = player;
        break;
      }
    }
    if (removePlayer != null) {
      userRoster.remove(removePlayer);
      return "Player: " + playerName + "has been removed from the roster.";
    } else {
      return "Player was not found in the roster.";
    }
  }

  public void exportPlayers() {
    throw new UnsupportedOperationException("exportPlayers() Not implemented yet.");
    Set<Player> userRoster = model.getUserRoster(); // might need to add this to Model

  }

  // find some way to parse the argument input to the text field
}
