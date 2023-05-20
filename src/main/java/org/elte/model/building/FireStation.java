package org.elte.model.building;

import org.elte.model.Firetruck;
import org.elte.model.Game;
import org.elte.model.enums.BuildingTypes;
import org.elte.utils.Position;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FireStation extends Building {

  Firetruck firetruck;

  public FireStation(Position position) {
    this.position = position;
    buildingType = BuildingTypes.FIRE_STATION;
    price = 30;
    yearlyFee = 10;
    socialImpact = 5;
    impactRange = 30;
    width = 1;
    height = 1;
    chanceToFire = 0;
    chanceToCollapse = 0.005;
    burning = false;
  }

  public boolean isPlaceable() {
    return hasEnoughSpace() && hasRoadAtLeastOneSide();
  }

  @Override
  public boolean isConnected() {
    return false;
  }

  public boolean upgradeable() {
    return true;
  }

  public boolean isBurning() {
    return false;
  }

  @Override
  public boolean isFireStation() {
    return true;
  }

  @Override
  public boolean isEmpty() {
    return false;
  }

  @Override
  public void delete() {
    super.delete();
    Game.getInstance().getFiretrucks().remove(firetruck);
    // List<FiretruckView> oldFiretruckViews = GamePanel.gamePanel.getFiretruckViews();
    // FiretruckView oldFiretruckView = oldFiretruckViews.stream().filter(item ->
    // item.getFiretruck().equals(firetruck)).findFirst().get();
    // GamePanel.gamePanel.remove(oldFiretruckView);
    // GamePanel.gamePanel.setFiretruckViews(oldFiretruckViews.stream().filter(item ->
    // !item.getFiretruck().equals(firetruck)).collect(Collectors.toList()));
    // System.out.println(Game.getInstance().getFiretrucks());
  }

  @Override
  public void create(String s) {
    if (this.isPlaceable()) {
      super.create("fire_station");
      firetruck = new Firetruck(this);
      Game.getInstance().getFiretrucks().add(firetruck);
      // FiretruckView firetruckView = new FiretruckView(firetruck);
      // GamePanel.gamePanel.add(firetruckView);
      // GamePanel.gamePanel.getFiretruckViews().add(firetruckView);
    }
  }

  public int getTax() {
    return yearlyFee;
  }
}
