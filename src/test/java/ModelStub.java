import Model.SortFilter.ColumnData;
import Model.Player;
import Model.PlayerBean;
import Model.IModel;
import View.IView;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ModelStub implements IModel {
  private Set<Player> roster = new HashSet<>();

  @Override
  public Set<Player> filterSortNBARoster(String inputString, ColumnData filter, boolean sort) {
      // Return a stubbed result based on the input
      return roster;
  }

  @Override
  public void buildRoster(Set<Player> filteredSet, String inputString) {
      roster.addAll(filteredSet);
  }

  @Override
  public void removeFromRoster(String inputString) {
    roster.removeIf(player -> player.getFirstName().equalsIgnoreCase(inputString));
  }

  @Override
  public Set<Player> getRoster() {
      return roster;
  }

  @Override
  public Set<Player> getAllPlayers() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getAllPlayers'");
  }

  @Override
  public String getFilePath() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getFilePath'");
  }

  @Override
  public void setFilePath(String filePath) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'setFilePath'");
  }

  @Override
  public String toString(Player player) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'toString'");
  }

  @Override
  public Set<Player> createNBARoster() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'createNBARoster'");
  }

  @Override
  public Set<Player> filterSortNBARoster(String filter) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'filterSortNBARoster'");
  }

  @Override
  public Set<Player> filterSortNBARoster(String filter, ColumnData sortOn) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'filterSortNBARoster'");
  }

  @Override
  public Player createPlayer(PlayerBackground background, PlayerAverages seasonAverages) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'createPlayer'");
  }

  @Override
  public Set<Player> beanToPlayer(List<PlayerBean> beanList) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'beanToPlayer'");
  }
}