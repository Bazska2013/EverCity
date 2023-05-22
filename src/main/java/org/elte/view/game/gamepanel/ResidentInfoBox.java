package org.elte.view.game.gamepanel;

import java.awt.*;
import javax.swing.*;

import org.elte.model.Field;
import org.elte.model.Game;
import org.elte.model.building.House;
import org.elte.model.people.Citizen;
import org.elte.model.zone.Resident;
import org.elte.model.zone.Service;
import org.elte.utils.Position;
import org.elte.utils.ScreenUtils;
import org.elte.view.Localization;
import org.elte.view.game.GamePanel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResidentInfoBox extends JPanel {

  public static final int HEIGHT = (int) (ScreenUtils.getScreenSize().height * 0.50);
  public static final int WIDTH = (int) (ScreenUtils.getScreenSize().width * 0.15) - 10;
  public static final int X = (ScreenUtils.getScreenSize().width) - WIDTH;
  public static final int Y = (int) (ScreenUtils.getScreenSize().height * 0.10);

  private Game game;
  private GamePanel gamePanel;
  private Position selectedPosition;

  public ResidentInfoBox() {
    super();
    this.game = Game.getInstance();
    this.setBounds(X, Y, WIDTH, HEIGHT);
    this.setBorder(BorderFactory.createLineBorder(Color.black, 3));
    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // add one more row for the button
    this.setBackground(Color.gray);
    this.setVisible(true);
    reRender();
  }

  public void reRender() {
    if (selectedPosition != null) {
      Field selectedField = game.getField(selectedPosition);
      if (selectedField.getZone() instanceof Resident
          && selectedField.getBuilding() instanceof House) {
        this.removeAll();
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(Color.gray);
        int i = 1;
        JLabel citizens = new JLabel();
        citizens.setText(Localization.getProp().getProperty("residents") + ":");
        citizens.setFont(new Font("Arial", Font.BOLD, 20));
        citizens.setForeground(Color.WHITE);
        contentPanel.add(citizens);
        for (Citizen c : ((Resident) selectedField.getZone()).getCitizens()) {
          JLabel number = new JLabel();
          number.setText(Integer.toString(i) + ":");
          contentPanel.add(number);
          JLabel age =
              setLabelProperties(
                  Localization.getProp().getProperty("age") + ": ", Integer.toString(c.getAge()));
          contentPanel.add(age);
          if (c.getWorksAt() != null) {
            String workplace;
            if (c.getWorksAt() instanceof Service) {
              workplace =
                  Localization.getProp().getProperty("service_zone")
                      + " ("
                      + c.getWorksAt().getPosition().x()
                      + ","
                      + c.getWorksAt().getPosition().y()
                      + ")";
            } else {
              workplace =
                  Localization.getProp().getProperty("industry_zone")
                      + " ("
                      + c.getWorksAt().getPosition().x()
                      + ","
                      + c.getWorksAt().getPosition().y()
                      + ")";
            }
            JLabel worksAt =
                setLabelProperties(Localization.getProp().getProperty("worksat") + ": ", workplace);
            contentPanel.add(worksAt);
          }
          JLabel satisfaction =
              setLabelProperties(
                  Localization.getProp().getProperty("mood") + ": ", c.getSatisfaction() + "%");
          contentPanel.add(satisfaction);
          JLabel retired =
              setLabelProperties(
                  Localization.getProp().getProperty("retired") + ": ",
                  Localization.getProp().getProperty(Boolean.toString(c.isRetired())));
          contentPanel.add(retired);
          i++;
        }

        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        scrollPane.getViewport().setBackground(Color.gray);

        JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
        verticalScrollBar.setUnitIncrement(20);
        verticalScrollBar.setBlockIncrement(20);

        setLayout(new BorderLayout());
        this.add(scrollPane, BorderLayout.CENTER);
        this.setVisible(true);
      } else {
        this.setVisible(false);
      }
    } else {
      this.setVisible(false);
    }
  }

  public JLabel setLabelProperties(String textToDisplay, String property) {
    Font labelFont = new Font("Arial", Font.PLAIN, 14);
    JLabel label = new JLabel();
    label.setForeground(Color.WHITE);
    label.setFont(labelFont);
    label.setText(textToDisplay + property);
    return label;
  }

  public void setGamePanel(GamePanel gamePanel) {
    this.gamePanel = gamePanel;
  }

  public void setSelectedPosition(Position position) {
    this.selectedPosition = position;
    reRender();
  }
}
