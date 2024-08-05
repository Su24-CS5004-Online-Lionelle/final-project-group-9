package Model.Format;

import java.util.Collection;
import Model.IModel;
import Model.Player;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class PlayerJSONWrapper {
  @JsonProperty("data")
  private Collection<Player> data;

  /**
   * List of records
   */
  public PlayerJSONWrapper(Collection<Player> players) {
    this.data = players;
  }
}


