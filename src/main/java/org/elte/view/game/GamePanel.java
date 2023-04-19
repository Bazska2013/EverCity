package org.elte.view.game;

import org.elte.model.Game;
import org.elte.model.building.*;
import org.elte.model.zone.Empty;
import org.elte.model.zone.Industry;
import org.elte.model.zone.Resident;
import org.elte.model.zone.Service;
import org.elte.utils.ImageResource;
import org.elte.utils.Position;
import org.elte.utils.ScreenUtils;
import org.elte.view.Localization;
import org.elte.view.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class GamePanel extends JPanel {
		private static final long serialVersionUID = 1L;
		private int current_x_mouse_position, current_y_mouse_position;
		private boolean cursor_inside_bounderies = false;
		private Game game;
		private Dimension screenSize;
		Point p;

		public static GamePanel gamePanel = null;

		public static boolean zoneMode = false;

		private MouseListener mouseListener =
						new MouseAdapter() {
								public void mouseClicked(MouseEvent e) {
										int current_x = (int) Math.floor((e.getX() - MainFrame.START_X) / 32);
										int current_y = (int) Math.floor((e.getY() - MainFrame.START_Y) / 32);
										System.out.println("Clicked - X: " + current_x + ", Y: " + current_y);

										try {

												if (BuildablePanel.getSelectedFieldZoneType() != null) {
														switch (BuildablePanel.getSelectedFieldZoneType()) {
																case EMPTY -> {
																		if (!(game.getField(new Position(current_x, current_y))
																										.getZone()
																						instanceof Empty))
																				game.getField(new Position(current_x, current_y))
																								.setZone(new Empty(1000));
																}
																case SERVICE -> {
																		if (!(game.getField(new Position(current_x, current_y))
																										.getZone()
																						instanceof Service))
																				game.getField(new Position(current_x, current_y))
																								.setZone(new Service(1, 2, 3, 4, 5));
																}
																case INDUSTRY -> {
																		if (!(game.getField(new Position(current_x, current_y))
																										.getZone()
																						instanceof Industry))
																				game.getField(new Position(current_x, current_y))
																								.setZone(new Industry(1, 2, 3, 4, 5));
																}
																case RESIDENT -> {
																		if (!(game.getField(new Position(current_x, current_y))
																										.getZone()
																						instanceof Resident))
																				game.getField(new Position(current_x, current_y))
																								.setZone(new Resident(1, 2, 3, 4, 5, null));
																}
														}
												}
												if (BuildablePanel.getSelectedBuildingType() != null) {
														switch (BuildablePanel.getSelectedBuildingType()) {
																case ROAD -> game.getField(new Position(current_x, current_y))
																				.setBuilding(new Road());
																case EMPTY -> game.getField(new Position(current_x, current_y))
																				.setBuilding(new org.elte.model.building.Empty());
																case FACTORY -> game.getField(new Position(current_x, current_y))
																				.setBuilding(new Factory());
																case FIRE_STATION -> game.getField(
																								new Position(current_x, current_y))
																				.setBuilding(new FireStation());
																case FOREST -> game.getField(new Position(current_x, current_y))
																				.setBuilding(new Forest());
																case POLICE -> game.getField(new Position(current_x, current_y))
																				.setBuilding(new Police());
																case STADIUM -> game.getField(new Position(current_x, current_y))
																				.setBuilding(new Stadium());
														}
												}
												repaint();
										} catch (ArrayIndexOutOfBoundsException ex) {
												System.out.println("Clicked outside of playing area...");
										}
								}
						};

		private MouseMotionListener mouseMotionListener =
						new MouseMotionListener() {
								@Override
								public void mouseDragged(MouseEvent e) {}

								@Override
								public void mouseMoved(MouseEvent e) {
										int current_x = (int) Math.floor((e.getX() - MainFrame.START_X) / 32);
										int current_y = (int) Math.floor((e.getY() - MainFrame.START_Y) / 32);

										if (checkIfCursorInsideMapBoundaries(current_x, current_y)) {
												current_x_mouse_position =
																(int) Math.floor((e.getX() - MainFrame.START_X) / 32);
												current_y_mouse_position =
																(int) Math.floor((e.getY() - MainFrame.START_Y) / 32);
												cursor_inside_bounderies = true;
												repaint();
										} else if (cursor_inside_bounderies) {
												cursor_inside_bounderies = false;
												repaint();
										}
								}
						};

		public GamePanel() {
				addMouseListener(mouseListener);
				addMouseMotionListener(mouseMotionListener);
				this.game = Game.getInstance();
				this.screenSize = ScreenUtils.getScreenSize();
				this.setBounds(0, 0, screenSize.width, MainFrame.ZONE_BOX_Y - 50);
				this.setVisible(true);
				gamePanel = this;
		}

		protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2d = (Graphics2D) g;
				g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);

				/** Kirajzolja a négyzeteket. */
				g.setColor(Color.black);
				for (int i = 0; i < Game.getColumns(); i++) {
						for (int j = 0; j < Game.getRows(); j++) {
								if (!zoneMode) {
										g.drawImage(
														ImageResource.getImageOfClass(
																		game.getField(new Position(i, j)).getBuilding().getClass()),
														i * 32 + MainFrame.START_X,
														j * 32 + MainFrame.START_Y,
														Game.getBoxWidth(),
														Game.getBoxheight(),
														null);
								} else {
										g.drawImage(
														ImageResource.getZoneImageOfClass(
																		game.getField(new Position(i, j)).getZone().getClass()),
														i * 32 + MainFrame.START_X,
														j * 32 + MainFrame.START_Y,
														Game.getBoxWidth(),
														Game.getBoxheight(),
														null);
								}
								/** Keret megrajzolása * */
								g.drawRect(
												i * 32 + MainFrame.START_X,
												j * 32 + MainFrame.START_Y,
												Game.getBoxheight(),
												Game.getBoxheight());
						}
				}

				/** Kirajzolja az információs doboz hátterét. */
				g.setColor(Color.blue);
				g.fillRect(screenSize.width - 200, screenSize.height / 2 - 150, 180, 150);
				g.setColor(Color.white);
				if (cursor_inside_bounderies) {
						g.drawString(
										Localization.getProp().getProperty("zone_type_title")
														+ " "
														+ game.getField(
																						new Position(
																										current_x_mouse_position,
																										current_y_mouse_position))
																		.getZoneType()
																		.name(),
										screenSize.width - 190,
										screenSize.height / 2 - 130);
						g.drawString(
										Localization.getProp().getProperty("zone_position_x")
														+ " "
														+ current_x_mouse_position,
										screenSize.width - 190,
										screenSize.height / 2 - 110);
						g.drawString(
										Localization.getProp().getProperty("zone_position_y")
														+ " "
														+ current_y_mouse_position,
										screenSize.width - 190,
										screenSize.height / 2 - 90);
				}
		}

		private boolean checkIfCursorInsideMapBoundaries(int x, int y) {
				return (y >= 0) && (y < Game.getRows()) && (x >= 0) && (x < Game.getColumns());
		}
}
