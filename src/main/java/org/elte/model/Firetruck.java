package org.elte.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Firetruck {
  private int position_x;
  private int position_y;
  private Direction direction;
  private int speed;

  public boolean isMoving(){
    return false;
  }

  public boolean isColliding(){
    return false;
  }

  public void goTo(int x, int y){

  }
}
