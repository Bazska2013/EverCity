package org.elte.view.game.gamepanel;

import lombok.Getter;
import lombok.Setter;
import org.elte.model.Game;
import org.elte.utils.Position;
import org.elte.utils.ScreenUtils;
import org.elte.view.Localization;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter
public class FieldInfoBox extends JPanel {

		public static final int X = 0;
		public static final int Y = (int) (ScreenUtils.getScreenSize().height * 0.10);
		public static final int HEIGHT = (int) (ScreenUtils.getScreenSize().height * 0.50);
		public static final int WIDTH = (int) (ScreenUtils.getScreenSize().width * 0.15) - 10;
		private int selectedX;
		private int selectedY;
		private Game game;

		public FieldInfoBox() {
				super();
				this.game = Game.getInstance();
				this.setBounds(X, Y, WIDTH, HEIGHT);
				this.setBorder(BorderFactory.createLineBorder(Color.black, 3));
				this.setVisible(true);
		}

		protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2d = (Graphics2D) g;
				g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);

				g.setColor(Color.RED);
				g.fillRect(0, 0, WIDTH, HEIGHT);

				g.setColor(Color.white);
				g.drawString(
								Localization.getProp().getProperty("zone_type_title")
												+ " "
												+ game.getField(new Position(selectedX, selectedY))
																.getZone()
																.getZoneType()
																.name(),
								10,
								20);
				g.setColor(Color.white);
				g.drawString(
								Localization.getProp().getProperty("zone_type_title")
												+ " "
												+ game.getField(new Position(selectedX, selectedY))
																.getBuilding()
																.getBuildingType()
																.name(),
								10,
								40);
				g.drawString(
								Localization.getProp().getProperty("zone_position_x") + " " + selectedX, 10, 60);
				g.drawString(
								Localization.getProp().getProperty("zone_position_y") + " " + selectedY, 10, 80);
		}

		public void setSelectedX(int selectedX) {
				this.selectedX = selectedX;
				repaint();
		}

		public void setSelectedY(int selectedY) {
				this.selectedY = selectedY;
				repaint();
		}
}
