package org.elte.view;

import lombok.Setter;
import org.elte.model.Game;
import org.elte.utils.ScreenUtils;
import org.elte.view.game.BuildablePanel;
import org.elte.view.game.GamePanel;
import org.elte.view.game.TimeButtons;

import javax.swing.*;
import java.awt.*;

@Setter

public class MainFrame {
		public static final int START_X = (int) (ScreenUtils.getScreenSize().width * 0.12);
		public static final int START_Y = (int) (ScreenUtils.getScreenSize().height * 0.05);

		public static final int ZONE_BOX_Y = (int) (ScreenUtils.getScreenSize().height * 0.75);
		public static final int ZONE_BOX_SIZE = (ScreenUtils.getScreenSize().height - ZONE_BOX_Y);
		public static final int TIME_PANEL_SIZE = 30;
		public static final int TIME_PANEL_Y =
						ScreenUtils.getScreenSize().height - ZONE_BOX_SIZE - TIME_PANEL_SIZE;
		public static final int GAME_SIZE =
						ScreenUtils.getScreenSize().height - TIME_PANEL_SIZE - ZONE_BOX_Y;
		private static final int RERENDER_INTERVAL = 500;
		public static final String MENU_TEXT = "Menü";
		public static final String NEW_GAME_TEXT = "Új játék";
		public static final String EXIT_TEXT = "Kilépés";
		public static final String MOOD_LEVEL_TEXT = "Jókedvmérő";
		public static final String BALANCE_TEXT = "Egyenleg:";
		public static final String BALANCE_CURRENCY = "$";
		private final JFrame frame;
		static GamePanel gamePanel;
		TimeButtons timeButtons;
		BuildablePanel buildablePanel;
		Game game;
		static int balance = 20000;
		JMenu timeMenu;
		JMenu balanceMenu;
		Timer timer;
		Dimension screenSize;

		public MainFrame() {
				frame = new JFrame("EverCity");
				this.game = Game.getInstance();
				timeButtons = new TimeButtons();
				screenSize = ScreenUtils.getScreenSize();

				gamePanel = new GamePanel();

				frame.setSize(screenSize.width, screenSize.height);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.add(gamePanel);

				frame.setJMenuBar(createMenuBar());
				// Image img = ImageIO.read(getClass().getResource("../pic/fogaskerek.png"));
				// Image icon = img.getScaledInstance(15, 15, Image.SCALE_DEFAULT);

				frame.setLocationRelativeTo(null);
				frame.setLayout(null);
				frame.setResizable(false);
				//				 frame.addMouseListener(new MouseHandler());

				timeButtons = new TimeButtons();
				frame.add(timeButtons);

				buildablePanel = new BuildablePanel();
				frame.add(buildablePanel);

				frame.requestFocus();
				frame.setVisible(true);
				timer =
								new Timer(
												RERENDER_INTERVAL,
												e -> {
														updateTimer();
														updateBalance();
												});
				timer.start();
				timer.setRepeats(true);
		}

		public JMenuBar createMenuBar() {
				JMenuBar menuBar = new JMenuBar();
				JMenu gameOptions = new JMenu(MENU_TEXT);
				// menu.setIcon(new ImageIcon(icon));
				JMenuItem newGame = new JMenuItem(NEW_GAME_TEXT);
				gameOptions.add(newGame);

				JMenuItem exitMenuItem = new JMenuItem(EXIT_TEXT);
				exitMenuItem.addActionListener(ae -> System.exit(0));
				gameOptions.add(exitMenuItem);

				menuBar.add(gameOptions);

				menuBar.add(Box.createHorizontalStrut(30));
				balanceMenu = new JMenu(BALANCE_TEXT + balance + BALANCE_CURRENCY);
				// balancemenu.setEnabled(false);
				menuBar.add(balanceMenu);

				menuBar.add(Box.createHorizontalStrut(50));

				JMenu moodLevel = new JMenu(MOOD_LEVEL_TEXT);
				balanceMenu.setEnabled(false);
				menuBar.add(moodLevel);

				menuBar.add(Box.createHorizontalGlue());
				timeMenu = new JMenu(game.getGameTime().toString());
				timeMenu.setEnabled(false);
				menuBar.add(timeMenu);
				return menuBar;
		}

		private void updateTimer() {
				timeMenu.setText(game.getGameTime().toString());
		}
		private void updateBalance() {
			balanceMenu.setText(BALANCE_TEXT + balance + BALANCE_CURRENCY);
	}


		public static int GetBalance(){
			return balance;
		}
		public static void SetBalance(int bal){
			balance=bal;
		}

		public static GamePanel getGamePanel(){
			return gamePanel;
		}


}
