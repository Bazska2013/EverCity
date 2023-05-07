package org.elte.view.game.vehicles;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.elte.model.Direction;
import org.elte.model.Firetruck;
import org.elte.model.Game;
import org.elte.utils.ImageResource;
import org.elte.utils.Position;
import org.elte.view.game.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Getter
@EqualsAndHashCode(callSuper = false)
public class FiretruckView extends JPanel {
  private static final long serialVersionUID = 1L;
  Firetruck firetruck;

  public FiretruckView(Firetruck firetruck) {
    super();
    this.firetruck = firetruck;
    this.setOpaque(true);
    Position position = firetruck.getPosition();
    int x = position.x() * GamePanel.FIELD_SIZE;
    int y = position.y() * GamePanel.FIELD_SIZE;
    this.setVisible(true);
    this.setBounds(0, 0, GamePanel.FIELD_SIZE * Game.getColumns(), GamePanel.FIELD_SIZE * Game.getRows());
    System.out.println(this.getBounds());
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    // paint the component
    Graphics2D g2d = (Graphics2D) g;
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
    double angle = 0; // default angle (no rotation)
    Direction direction = firetruck.getDirection();
    if (direction != null) {
      switch (direction) {
        case UP:
          angle = Math.PI / 2;
          break;
        case DOWN:
          angle = -Math.PI / 2;
          break;
        case RIGHT:
          angle = Math.PI;
          break;
        default:
          angle = 0;
          break;
      }
      g2d.rotate(angle, GamePanel.FIELD_SIZE / 2, GamePanel.FIELD_SIZE / 2);
    }
    g2d.drawImage(ImageResource.FIRETRUCK, 0, 0, GamePanel.FIELD_SIZE, GamePanel.FIELD_SIZE, null);
    if (direction != null)
      g2d.rotate(-angle, GamePanel.FIELD_SIZE / 2, GamePanel.FIELD_SIZE / 2);

    // set the bounds of the component
  }

}
