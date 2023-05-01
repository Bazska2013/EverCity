package org.elte.view.game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.*;

import org.elte.model.enums.BuildingTypes;
import org.elte.model.enums.FieldTypes;
import org.elte.utils.ImageResource;
import org.elte.utils.ScreenUtils;
import org.elte.view.Localization;
import org.elte.view.MainFrame;

public class BuildablePanel extends JPanel {

  private final JButton[] zoneButtons;
  private final JButton[] buildingButtons;
  private final JButton toggleButton;

  private static FieldTypes selectedFieldZoneType = null;
  private static BuildingTypes selectedBuildingType = null;

  public BuildablePanel() {
    super();
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

  private JButton[] createBuildingTypeSelectionButtons() {
    JButton roadButton = createBuildingTypeSelectionButton(ImageResource.ROAD, BuildingTypes.ROAD);
    roadButton.setToolTipText(Localization.getProp().getProperty("road"));
    JButton emptyBuildingButton =
        createBuildingTypeSelectionButton(ImageResource.GRASS, BuildingTypes.EMPTY);
    emptyBuildingButton.setToolTipText(Localization.getProp().getProperty("grass"));
    JButton factoryButton =
        createBuildingTypeSelectionButton(ImageResource.FACTORY, BuildingTypes.FACTORY);
    factoryButton.setToolTipText(Localization.getProp().getProperty("factory"));
    JButton fireStationButton =
        createBuildingTypeSelectionButton(ImageResource.FIRE_STATION, BuildingTypes.FIRE_STATION);
    fireStationButton.setToolTipText(Localization.getProp().getProperty("fire_station"));
    JButton forestButton =
        createBuildingTypeSelectionButton(ImageResource.FOREST, BuildingTypes.FOREST);
    forestButton.setToolTipText(Localization.getProp().getProperty("forest"));
    JButton policeButton =
        createBuildingTypeSelectionButton(ImageResource.POLICE, BuildingTypes.POLICE);
    policeButton.setToolTipText(Localization.getProp().getProperty("police"));
    JButton stadiumButton =
        createBuildingTypeSelectionButton(ImageResource.STADIUM, BuildingTypes.STADIUM);
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

  private JButton createBuildingTypeSelectionButton(Image image, BuildingTypes buildingType) {
    JButton button = new JButton(new ImageIcon(image));
    button.setBorder(BorderFactory.createEmptyBorder());
    button.setContentAreaFilled(false);
    button.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            handleBuildingTypeSelectionButton(button, buildingType);
          }
        });
    return button;
  }

  private void addButtonsToComponent(JButton[] buttons) {
    Arrays.stream(buttons).forEach(this::add);
  }

  private void showButtons(JButton[] buttons) {
    Arrays.stream(buttons).forEach(btn -> btn.setVisible(true));
  }

  private void removeButtons(JButton[] buttons) {
    Arrays.stream(buttons).forEach(btn -> btn.setVisible(false));
  }

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

  private void handleBuildingTypeSelectionButton(JButton button, BuildingTypes buildingType) {
    if (selectedBuildingType != buildingType) {
      resetBuildingTypeSelectionButtons();
      button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, false));
      button.setContentAreaFilled(true);
      selectedBuildingType = buildingType;
    } else {
      button.setBorder(BorderFactory.createEmptyBorder());
      button.setContentAreaFilled(false);
      selectedBuildingType = null;
    }
  }

  private void resetZoneTypeSelectionButtons() {
    Arrays.stream(zoneButtons).forEach(this::resetZoneTypeSelectionButton);
  }

  public void resetBuildingTypeSelectionButtons() {
    Arrays.stream(buildingButtons).forEach(this::resetBuildingTypeSelectionButton);
  }

  private void resetZoneTypeSelectionButton(JButton button) {
    button.setBorder(BorderFactory.createEmptyBorder());
    button.setContentAreaFilled(false);
  }

  public void resetBuildingTypeSelectionButton(JButton button) {
    button.setBorder(BorderFactory.createEmptyBorder());
    button.setContentAreaFilled(false);
  }

  public static FieldTypes getSelectedFieldZoneType() {
    return selectedFieldZoneType;
  }

  public static BuildingTypes getSelectedBuildingType() {
    return selectedBuildingType;
  }

  public static void setSelectedFieldZoneType(FieldTypes selectedFieldZoneType) {
    BuildablePanel.selectedFieldZoneType = selectedFieldZoneType;
  }

  public static void setSelectedBuildingType(BuildingTypes selectedBuildingType) {
    BuildablePanel.selectedBuildingType = selectedBuildingType;
  }
}
