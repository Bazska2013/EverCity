package org.elte.view.game;

import org.elte.model.Field;
import org.elte.model.Game;
import org.elte.model.zone.Empty;
import org.elte.model.zone.Industry;
import org.elte.model.zone.Resident;
import org.elte.model.zone.Service;
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

	private MouseListener listener = new MouseAdapter() {
		public void mouseClicked(MouseEvent e) {
			int current_x = (int) Math.floor((e.getX() - MainFrame.START_X) / 32);
			int current_y = (int) Math.floor((e.getY() - MainFrame.START_Y) / 32);
			System.out.println("Clicked - X: " + current_x + ", Y: " + current_y);

			try{
				if(BuildablePanel.getSelectedFieldZoneType() != null) {
					switch (BuildablePanel.getSelectedFieldZoneType()) {
						case EMPTY -> fields[current_y][current_x].setZone(new Empty(1));
						case SERVICE -> fields[current_y][current_x].setZone(new Service(1, 2, 3, 4, 5));
						case INDUSTRY -> fields[current_y][current_x].setZone(new Industry(1, 2, 3, 4, 5));
						case RESIDENT -> fields[current_y][current_x].setZone(new Resident(1, 2, 3, 4, 5, null));
					}
				}

				repaint();
			} catch (ArrayIndexOutOfBoundsException ex) {
				System.out.println("Clicked outside of playing area...");
			}

		}
	};

	/** Létrehozunk egy mezőket tartalmazó mátrixot. */
	private Field[][] fields;

	public GamePanel() {
		addMouseListener(listener);
		this.game = Game.getInstance();
		this.fields = new Field[Game.getRows()][Game.getColumns()];
		this.screenSize = ScreenUtils.getScreenSize();
		this.setBounds(0, 0, screenSize.width, MainFrame.ZONE_BOX_Y - 50);
		this.setVisible(true);

		/** Inicializáljuk a mátrixunkat. */
		for (int i = 0; i < Game.getRows(); i++) {
			for (int j = 0; j < Game.getColumns(); j++) {
				fields[i][j] = new Field(j * 32 + MainFrame.START_X, i * 32 + MainFrame.START_Y);
			}
		}

		/** Feltöltjük ideiglenesen dummy adatokkal a fields tömbünket. */
		for (int i = 0; i < Game.getRows(); i++) {
			for (int j = 0; j < Game.getColumns(); j++) {
				switch((i+j)%4) {
					case 0:
						fields[i][j].setZone(new Empty(1000));
						break;
					case 1:
						fields[i][j].setZone(new Industry(1, 2, 3, 4, 5));
						break;
					case 2:
						fields[i][j].setZone(new Resident(1, 2, 3, 4, 5, null));
						break;
					case 3:
						fields[i][j].setZone(new Service(1, 2, 3, 4, 5));
						break;
				}
			}
		}

		gamePanel = this;

	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);

		/** Megrajzolja a játékterület hátterét. */
		g.setColor(new Color(0, 200, 0));
		g.fillRect(MainFrame.START_X, MainFrame.START_Y, 960, 480);

		/** Kirajzolja a négyzeteket. */
		g.setColor(Color.black);
		for (int i = 0; i < Game.getRows(); i++) {
			for (int j = 0; j < Game.getColumns(); j++) {

				/** Beállítjuk az adott mező értékének megfelelő rajzolási képet. */

				/** Kirajzoljuk az adott mezőt. */

				fields[i][j].drawField(g, zoneMode);

				g.drawRect(
					j * 32 + MainFrame.START_X,
					i * 32 + MainFrame.START_Y,
					Game.getBoxheight(),
					Game.getBoxheight());
			}
		}

		/** Kirajzolja a keretet. */
		g.setColor(Color.black);
		g.drawRect(
			MainFrame.START_X,
			MainFrame.START_Y,
			Game.getBoxheight() * Game.getColumns(),
			Game.getBoxheight() * Game.getRows());

		/** Kirajzolja a csíkot, amin az elhelyezhető Zónák és épületek lesznek. */

		/** Kirajzolja az információs doboz hátterét. */
		g.setColor(Color.blue);
		g.fillRect(screenSize.width - 150, 10, 150, 150);

	}

}
