package Controller;

import Model.IModel;
import Model.PlayerBean;
import View.IView;

public class ArgsController {
  private IView view;
  private IModel model;
  private String playerName = "all";

  public ArgsController(IView view, IModel model) {
    this.view = view;
    this.model = model;
  }

  public String processCommands(String command) {
    throw new UnsupportedOperationException("processCommands() Not implemented yet.");
  }

  private PlayerBean getPlayer(String playerName, boolean add) {
    throw new UnsupportedOperationException("getPlayer() Not implemented yet.");
  }

  private String listAllPlayers(String playerName) {
    throw new UnsupportedOperationException("listAllPlayers() Not implemented yet.");
  }

  private String generateRoster() {
    throw new UnsupportedOperationException("generateRoster() Not implemented yet.");
  }

  private String filterPlayers(String filterOperation) {
    throw new UnsupportedOperationException("filterPlayers() Not implemented yet.");
  }

  private String removePlayerFromRoster(String playerName) {
    throw new UnsupportedOperationException("removePlayerFromRoster() Not implemented yet.");
  }

  private void exportPlayers() {
    throw new UnsupportedOperationException("exportPlayers() Not implemented yet.");
  }
}
