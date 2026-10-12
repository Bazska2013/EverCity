package org.elte.view.game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.*;

import org.elte.model.enums.BuildingPrices;
import org.elte.model.enums.BuildingTypes;
import org.elte.model.enums.FieldTypes;
import org.elte.utils.ImageResource;
import org.elte.utils.ScreenUtils;
import org.elte.view.Localization;
import org.elte.view.MainFrame;

/** A BuildablePanel osztály egy építkezési panelt reprezentál egy játékban. */
public class BuildablePanel extends JPanel {

  private JButton[] zoneButtons;
  private JButton[] buildingButtons;
  private JButton toggleButton;

  private static FieldTypes selectedFieldZoneType = null;
  private static BuildingTypes selectedBuildingType = null;
  /** Konstruktor, amely létrehozza a BuildablePanel objektumot. */
  public BuildablePanel() {
    super();
    buildBuildablePanel();
  }
  /** Az építési panel inicializálása és felépítése. */
  private void buildBuildablePanel() {
    this.setBounds(
        0, MainFrame.ZONE_BOX_Y, ScreenUtils.getScreenSize().width, MainFrame.ZONE_BOX_SIZE);
    this.setBackground(Color.GRAY);

    zoneButtons = createZoneTypeSelectionButtons();
    buildingButtons = createBuildingTypeSelectionButtons();

    toggleButton = new JButton(new ImageIcon(ImageResource.ZONE_MODE_TOGGLE));
    toggleButton.setBorder(BorderFactory.createEmptyBorder());
    toggleButton.setContentAreaFilled(false);
    toggleButton.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            GamePanel.zoneMode = !GamePanel.zoneMode;
            switchButtons();
            GamePanel.gamePanel.repaint();
          }
        });

    add(toggleButton);
    addButtonsToComponent(zoneButtons);
    addButtonsToComponent(buildingButtons);
    switchButtons();
  }
  /** Az építési panel újragenerálása. */
  public void regenerateBuildablePanel() {
    this.removeAll();
    this.buildingButtons = null;
    this.zoneButtons = null;
    this.toggleButton = null;
    buildBuildablePanel();
  }
  /**
   * Létrehoz és inicializál gombokat a zóna típus kiválasztásához.
   *
   * @return Az inicializált gombok tömbje.
   */
  private JButton[] createZoneTypeSelectionButtons() {
    JButton residentZoneButton =
        createZoneTypeSelectionButton(ImageResource.ZONE_RESIDENT, FieldTypes.RESIDENT);
    residentZoneButton.setToolTipText(Localization.getProp().getProperty("resident_zone"));
    JButton industryZoneButton =
        createZoneTypeSelectionButton(ImageResource.ZONE_INDUSTRY, FieldTypes.INDUSTRY);
    industryZoneButton.setToolTipText(Localization.getProp().getProperty("industry_zone"));
    JButton serviceZoneButton =
        createZoneTypeSelectionButton(ImageResource.ZONE_SERVICE, FieldTypes.SERVICE);
    serviceZoneButton.setToolTipText(Localization.getProp().getProperty("service_zone"));
    JButton emptyZoneButton =
        createZoneTypeSelectionButton(ImageResource.ZONE_EMPTY, FieldTypes.EMPTY);
    emptyZoneButton.setToolTipText(Localization.getProp().getProperty("empty_zone"));
    return new JButton[] {
      residentZoneButton, industryZoneButton, serviceZoneButton, emptyZoneButton
    };
  }
  /**
   * Létrehoz és inicializál gombokat az épület típus kiválasztásához.
   *
   * @return Az inicializált gombok tömbje.
   */
  private JButton[] createBuildingTypeSelectionButtons() {
    JButton roadButton =
        createBuildingTypeSelectionButton(
            ImageResource.ROAD,
            BuildingTypes.ROAD,
            "road",
            BuildingPrices.ROAD.getPriceOfBuilding());
    roadButton.setToolTipText(Localization.getProp().getProperty("road"));
    JButton emptyBuildingButton =
        createBuildingTypeSelectionButton(
            ImageResource.GRASS,
            BuildingTypes.EMPTY,
            "grass",
            BuildingPrices.EMPTY.getPriceOfBuilding());
    emptyBuildingButton.setToolTipText(Localization.getProp().getProperty("grass"));
    JButton factoryButton =
        createBuildingTypeSelectionButton(
            ImageResource.FACTORY,
            BuildingTypes.FACTORY,
            "factory",
            BuildingPrices.FACTORY.getPriceOfBuilding());
    factoryButton.setToolTipText(Localization.getProp().getProperty("factory"));
    JButton fireStationButton =
        createBuildingTypeSelectionButton(
            ImageResource.FIRE_STATION,
            BuildingTypes.FIRE_STATION,
            "fire_station",
            BuildingPrices.FIRE_STATION.getPriceOfBuilding());
    fireStationButton.setToolTipText(Localization.getProp().getProperty("fire_station"));
    JButton forestButton =
        createBuildingTypeSelectionButton(
            ImageResource.FOREST1,
            BuildingTypes.FOREST1,
            "forest",
            BuildingPrices.FOREST.getPriceOfBuilding());
    forestButton.setToolTipText(Localization.getProp().getProperty("forest"));
    JButton policeButton =
        createBuildingTypeSelectionButton(
            ImageResource.POLICE,
            BuildingTypes.POLICE,
            "police",
            BuildingPrices.POLICE.getPriceOfBuilding());
    policeButton.setToolTipText(Localization.getProp().getProperty("police"));
    JButton stadiumButton =
        createBuildingTypeSelectionButton(
            ImageResource.STADIUM,
            BuildingTypes.STADIUM,
            "stadium",
            BuildingPrices.STADIUM.getPriceOfBuilding());
    stadiumButton.setToolTipText(Localization.getProp().getProperty("stadium"));
    return new JButton[] {
      roadButton,
      emptyBuildingButton,
      factoryButton,
      fireStationButton,
      forestButton,
      policeButton,
      stadiumButton
    };
  }
  /**
   * Létrehoz és inicializál egy gombot a zóna típus kiválasztásához.
   *
   * @param image Az ikonkép.
   * @param fieldType A zóna típusa.
   * @return Az inicializált gomb.
   */
  private JButton createZoneTypeSelectionButton(Image image, FieldTypes fieldType) {
    JButton button = new JButton(new ImageIcon(image));
    button.setBorder(BorderFactory.createEmptyBorder());
    button.setContentAreaFilled(false);
    button.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            handleZoneTypeSelectionButton(button, fieldType);
          }
        });
    return button;
  }
  /**
   * Létrehoz és inicializál egy gombot az épület típus kiválasztásához.
   *
   * @param image Az ikonkép.
   * @param buildingType Az épület típusa.
   * @param localizationString A lokalizációs kulcs szövege.
   * @param price Az épület ára.
   * @return Az inicializált gomb.
   */
  private JButton createBuildingTypeSelectionButton(
      Image image, BuildingTypes buildingType, String localizationString, int price) {

    String text =
        "<html><table style='width:80px'><tr><td style='text-align:center'>"
            + Localization.getProp().getProperty(localizationString)
            + "</td></tr><tr><td style='text-align:center'>"
            + price
            + " $</td></tr></table></html>";
    JButton button =
        new JButton(text, new ImageIcon(image)) {
          @Override
          public void doLayout() {
            super.doLayout();
            int preferedWidth = getPreferredSize().width;
            int actualWidth = getSize().width;
            if (preferedWidth != actualWidth) {
              int gap = getIconTextGap() + actualWidth - preferedWidth;
              gap = Math.max(gap, UIManager.getInt("Button.iconTextGap"));
              setIconTextGap(gap);
            }
          }
        };
    button.setVerticalTextPosition(SwingConstants.BOTTOM);
    button.setHorizontalTextPosition(SwingConstants.CENTER);
    // button.setBorder(BorderFactory.createEmptyBorder());
    button.setContentAreaFilled(true);
    button.setBackground(Color.LIGHT_GRAY);
    button.setBorder(BorderFactory.createLineBorder(Color.white, 1));
    button.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            handleBuildingTypeSelectionButton(button, buildingType);
          }
        });
    return button;
  }
  /**
   * Hozzáadja a gombokat a komponenshez.
   *
   * @param buttons A gombok tömbje.
   */
  private void addButtonsToComponent(JButton[] buttons) {
    Arrays.stream(buttons).forEach(jButton -> this.add(jButton, BorderLayout.CENTER));
  }
  /**
   * Megjeleníti a gombokat.
   *
   * @param buttons A gombok tömbje.
   */
  private void showButtons(JButton[] buttons) {
    Arrays.stream(buttons).forEach(btn -> btn.setVisible(true));
  }
  /**
   * Elrejti a gombokat.
   *
   * @param buttons A gombok tömbje.
   */
  private void removeButtons(JButton[] buttons) {
    Arrays.stream(buttons).forEach(btn -> btn.setVisible(false));
  }
  /**
   * Váltás a gombok között. Az épület és zóna típus kiválasztását kezeli, és megjeleníti vagy
   * elrejti a gombokat a jelenlegi játékállapot alapján.
   */
  private void switchButtons() {
    selectedBuildingType = null;
    selectedFieldZoneType = null;
    if (GamePanel.zoneMode) {
      removeButtons(buildingButtons);
      showButtons(zoneButtons);
    } else {
      removeButtons(zoneButtons);
      showButtons(buildingButtons);
    }
    repaint();
  }
  /**
   * Zóna típus kiválasztása esetén végrehajtandó műveletek kezelése.
   *
   * @param button Az érintett gomb.
   * @param fieldType Az aktuálisan kiválasztott zóna típusa.
   */
  private void handleZoneTypeSelectionButton(JButton button, FieldTypes fieldType) {
    if (selectedFieldZoneType != fieldType) {
      resetZoneTypeSelectionButtons();
      button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, false));
      button.setContentAreaFilled(true);
      selectedFieldZoneType = fieldType;
    } else {
      button.setBorder(BorderFactory.createEmptyBorder());
      button.setContentAreaFilled(false);
      selectedFieldZoneType = null;
    }
  }
  /**
   * Épület típus kiválasztása esetén végrehajtandó műveletek kezelése.
   *
   * @param button Az érintett gomb.
   * @param buildingType Az aktuálisan kiválasztott épület típusa.
   */
  private void handleBuildingTypeSelectionButton(JButton button, BuildingTypes buildingType) {
    if (selectedBuildingType != buildingType) {
      resetBuildingTypeSelectionButtons();
      button.setBackground(Color.decode("#ADD8E6"));
      button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, false));
      selectedBuildingType = buildingType;
    } else {
      button.setBorder(BorderFactory.createEmptyBorder());
      selectedBuildingType = null;
      button.setBackground(Color.LIGHT_GRAY);
      button.setBorder(BorderFactory.createLineBorder(Color.white, 1));
    }
  }
  /** Az összes kiválasztott zóna típus alaphelyzetbe állítását végrehajtó metódus. */
  private void resetZoneTypeSelectionButtons() {
    Arrays.stream(zoneButtons).forEach(this::resetZoneTypeSelectionButton);
  }
  /** Az összes kiválasztott épület típus alaphelyzetbe állítását végrehajtó metódus. */
  public void resetBuildingTypeSelectionButtons() {
    Arrays.stream(buildingButtons).forEach(this::resetBuildingTypeSelectionButton);
  }
  /**
   * Egy kiválasztott zóna típus alaphelyzetbe állítását végrehajtó metódus.
   *
   * @param button Az érintett gomb.
   */
  private void resetZoneTypeSelectionButton(JButton button) {
    button.setBorder(BorderFactory.createEmptyBorder());
    button.setContentAreaFilled(false);
  }
  /**
   * Egy kiválasztott épület típus alaphelyzetbe állítását végrehajtó metódus.
   *
   * @param button Az érintett gomb.
   */
  public void resetBuildingTypeSelectionButton(JButton button) {
    button.setBorder(BorderFactory.createEmptyBorder());
    button.setBackground(Color.LIGHT_GRAY);
    button.setBorder(BorderFactory.createLineBorder(Color.white, 1));
  }
  /**
   * Az aktuálisan kiválasztott zóna típusának visszaadása.
   *
   * @return Az aktuálisan kiválasztott zóna típusa.
   */
  public static FieldTypes getSelectedFieldZoneType() {
    return selectedFieldZoneType;
  }
  /**
   * Az aktuálisan kiválasztott épület típusának visszaadása.
   *
   * @return Az aktuálisan kiválasztott épület típusa.
   */
  public static BuildingTypes getSelectedBuildingType() {
    return selectedBuildingType;
  }
  /**
   * Az aktuálisan kiválasztott zóna típusának beállítása.
   *
   * @param selectedFieldZoneType Az aktuálisan kiválasztott zóna típusa.
   */
  public static void setSelectedFieldZoneType(FieldTypes selectedFieldZoneType) {
    BuildablePanel.selectedFieldZoneType = selectedFieldZoneType;
  }
  /**
   * Az aktuálisan kiválasztott épület típusának beállítása.
   *
   * @param selectedBuildingType Az aktuálisan kiválasztott épület típusa.
   */
  public static void setSelectedBuildingType(BuildingTypes selectedBuildingType) {
    BuildablePanel.selectedBuildingType = selectedBuildingType;
  }
}
