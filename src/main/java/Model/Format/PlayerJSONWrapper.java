package Model.Format;

import java.util.Collection;
import Model.IModel;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class PlayerJSONWrapper {
  @JsonProperty("data")
  private Collection<IModel.PlayerBackground> data;

  /**
   * List of records
   */
  public PlayerJSONWrapper(Collection<IModel.PlayerBackground> players) {
    this.data = players;
  }
}


