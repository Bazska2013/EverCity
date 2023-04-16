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
import org.elte.view.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GamePanel extends JPanel {
		private static final long serialVersionUID = 1L;
		private Game game;
		private Dimension screenSize;
		Point p;

		public static GamePanel gamePanel = null;

		public static boolean zoneMode = false;

		private MouseListener listener =
						new MouseAdapter() {
								public void mouseClicked(MouseEvent e) {
										int current_x = (int) Math.floor((e.getX() - MainFrame.START_X) / 32.0);
										int current_y = (int) Math.floor((e.getY() - MainFrame.START_Y) / 32.0);

										try {
												if (BuildablePanel.getSelectedFieldZoneType() != null) {
														switch (BuildablePanel.getSelectedFieldZoneType()) {
																case EMPTY -> game.getField(new Position(current_x, current_y))
																				.setZone(new Empty(1));
																case SERVICE -> game.getField(new Position(current_x, current_y))
																				.setZone(new Service(1, 2, 3, 4, 5));
																case INDUSTRY -> game.getField(new Position(current_x, current_y))
																				.setZone(new Industry(1, 2, 3, 4, 5));
																case RESIDENT -> game.getField(new Position(current_x, current_y))
																				.setZone(new Resident(1, 2, 3, 4, 5, null));
														}
												}

												if (BuildablePanel.getSelectedBuildingType() != null) {
                                                    switch (BuildablePanel.getSelectedBuildingType()) {
                                                        case ROAD -> {
                                                            if (game.getField(new Position(current_x, current_y)).getBuilding().isEmpty()) {
                                                                game.getField((new Position(current_x, current_y))).setBuilding(new Road());
                                                            }
                                                        }
                                                        case EMPTY -> {
                                                            if (game.getField(new Position(current_x, current_y)).getBuilding().isDeletable()) {
                                                                if (!game.getField(new Position(current_x,current_y)).getBuilding().isRoad()){
                                                                    game.removeTensionFromRoad(current_x, current_y);
                                                                }
                                                                game.getField(new Position(current_x, current_y)).setBuilding(new org.elte.model.building.Empty());
                                                            }
                                                        }
                                                        case FACTORY -> {
                                                            if (game.getField(new Position(current_x, current_y)).getBuilding().isEmpty() && game.getField(new Position(current_x, current_y)).getZone() instanceof Industry && game.isBuildingPlaceableHere(current_x, current_y)) {
                                                                game.getField(new Position(current_x, current_y)).setBuilding(new Factory());
                                                            }
                                                        }
                                                        case FIRE_STATION -> {
                                                            if (game.getField(new Position(current_x, current_y)).getBuilding().isEmpty() && game.isBuildingPlaceableHere(current_x, current_y)) {
                                                                game.getField(new Position(current_x, current_y)).setBuilding(new FireStation());
                                                            }
                                                        }
                                                        case FOREST -> {
                                                            if (game.getField(new Position(current_x, current_y)).getBuilding().isEmpty()) {
                                                                game.getField(new Position(current_x, current_y)).setBuilding(new Forest());
                                                            }
                                                        }
                                                        case POLICE -> {
                                                            if (game.getField(new Position(current_x, current_y)).getBuilding().isEmpty() && game.isBuildingPlaceableHere(current_x, current_y)) {
                                                                game.getField(new Position(current_x, current_y)).setBuilding(new Police());
                                                            }
                                                        }
                                                        case STADIUM -> {
                                                            if (game.getField(new Position(current_x, current_y)).getBuilding().isEmpty() && game.isBuildingPlaceableHere(current_x, current_y)) {
                                                                game.getField(new Position(current_x, current_y)).setBuilding(new Stadium());
                                                            }
                                                        }
                                                    }
												}

												repaint();
										} catch (ArrayIndexOutOfBoundsException ex) {
												System.out.println("Clicked outside of playing area...");
										}
								}
						};

		public GamePanel() {
				addMouseListener(listener);
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
				g.fillRect(screenSize.width - 150, 10, 150, 150);
		}
}
