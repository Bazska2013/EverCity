package org.elte.view;

import lombok.Setter;
import org.elte.model.Game;
import org.elte.model.enums.LanguageEnum;
import org.elte.utils.ScreenUtils;
import org.elte.view.game.BuildablePanel;
import org.elte.view.game.GamePanel;
import org.elte.view.game.TimeButtons;
import org.elte.view.game.gamepanel.FieldInfoBox;

import javax.swing.*;
import java.awt.*;

@Setter

public class MainFrame {
		public static String language = "hu_HU";
		public static final int ZONE_BOX_Y = (int) (ScreenUtils.getScreenSize().height * 0.75);
		public static final int ZONE_BOX_SIZE = (ScreenUtils.getScreenSize().height - ZONE_BOX_Y);
		public static final int TIME_PANEL_SIZE = 30;
		public static final int TIME_PANEL_Y =
						ScreenUtils.getScreenSize().height - ZONE_BOX_SIZE - TIME_PANEL_SIZE;
		private static final int RERENDER_INTERVAL = 500;
		public static final String BALANCE_CURRENCY = "$";
		private final JFrame frame;
		private static GamePanel gamePanel;
		private final TimeButtons timeButtons;
		private final BuildablePanel buildablePanel;
		private final FieldInfoBox fieldInfoBox;
		private final Game game;
		static int balance = 20000;
		JMenu timeMenu;
		JMenu balanceMenu;
		Timer timer;
		Dimension screenSize;

		private static MainFrame instance = null;

		private MainFrame() {
				frame = new JFrame("EverCity");
				screenSize = ScreenUtils.getScreenSize();
				game = Game.getInstance();

				frame.setJMenuBar(createMenuBar());

				frame.setSize(screenSize.width, screenSize.height);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				frame.setLocationRelativeTo(null);
				frame.setLayout(null);
				frame.setResizable(false);

				timeButtons = new TimeButtons();
				frame.add(timeButtons);

				buildablePanel = new BuildablePanel();
				frame.add(buildablePanel);

				fieldInfoBox = new FieldInfoBox();
				frame.add(fieldInfoBox);

				gamePanel = new GamePanel(fieldInfoBox);
				frame.add(gamePanel);
				frame.requestFocus();
				frame.setVisible(true);
				timer =
								new Timer(
												RERENDER_INTERVAL,
												e -> {
														updateTimer();
														gamePanel.repaint();
														updateBalance();
												});
				timer.start();
				timer.setRepeats(true);
		}

		public static MainFrame getInstance() {
			if (instance == null) {
				instance = new MainFrame();
			}
			return instance;
		}

		public static void newInstance() {
			instance = new MainFrame();
		}

		public JMenuBar createMenuBar() {
				JMenuBar menuBar = new JMenuBar();
				JMenu gameOptions = new JMenu(Localization.getProp().getProperty("menu_text"));
				// menu.setIcon(new ImageIcon(icon));
				JMenuItem newGame = new JMenuItem(Localization.getProp().getProperty("new_game_text"));
				gameOptions.add(newGame);

				JMenu languageSelectorMenuItem =
								new JMenu(Localization.getProp().getProperty("language_title"));
				JMenuItem hungarianLanguageSelectorItem =
								new JMenuItem(Localization.getProp().getProperty("hungarian"));
				hungarianLanguageSelectorItem.addActionListener(
								e -> {
										MainFrame.language = LanguageEnum.hu_HU.name();
										Localization.reloadProp();
										frame.setJMenuBar(createMenuBar());
										frame.repaint();
								});
				JMenuItem englishLanguageSelectorItem =
								new JMenuItem(Localization.getProp().getProperty("english"));
				englishLanguageSelectorItem.addActionListener(
								e -> {
										MainFrame.language = LanguageEnum.en_GB.name();
										Localization.reloadProp();
										frame.setJMenuBar(createMenuBar());
										frame.repaint();
								});
				languageSelectorMenuItem.add(hungarianLanguageSelectorItem);
				languageSelectorMenuItem.add(englishLanguageSelectorItem);
				gameOptions.add(languageSelectorMenuItem);

				JMenuItem exitMenuItem = new JMenuItem(Localization.getProp().getProperty("exit_text"));
				exitMenuItem.addActionListener(ae -> System.exit(0));
				gameOptions.add(exitMenuItem);

				menuBar.add(gameOptions);

				menuBar.add(Box.createHorizontalStrut(30));
				this.balanceMenu =
								new JMenu(
												Localization.getProp().getProperty("balance_text")
																+ balance
																+ BALANCE_CURRENCY);
				// balancemenu.setEnabled(false);
				menuBar.add(this.balanceMenu);

				menuBar.add(Box.createHorizontalStrut(50));

				JMenu moodLevel = new JMenu(Localization.getProp().getProperty("mood_level_text"));
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
			balanceMenu.setText(Localization.getProp().getProperty("balance_text")
					+ balance
					+ BALANCE_CURRENCY);
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
		public void modifyBalance(int value) {
			balance += value;
			balanceMenu.setText(
					Localization.getProp().getProperty("balance_text") + balance + BALANCE_CURRENCY);
		}


}
