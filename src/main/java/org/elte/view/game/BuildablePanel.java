package org.elte.view.game;

import org.elte.model.enums.BuildingTypes;
import org.elte.model.enums.FieldTypes;
import org.elte.utils.ImageResource;
import org.elte.utils.ScreenUtils;
import org.elte.view.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

public class BuildablePanel extends JPanel {

  private final JButton residentButton;
  private final JButton industryButton;
  private final JButton serviceButton;
  private final JButton emptyButton;
  private final JButton zoneModeToggleButton;
  private final JButton roadButton;
  private final JButton emptyBuildingButton;
  private final JButton factoryButton;
  private final JButton fireStationButton;
  private final JButton forestButton;
  private final JButton policeButton;
  private final JButton stadiumButton;

  private final Image residentImage = ImageResource.ZONE_RESIDENT;
  private final Image industryImage = ImageResource.ZONE_INDUSTRY;
  private final Image serviceImage = ImageResource.ZONE_SERVICE;
  private final Image emptyImage = ImageResource.ZONE_EMPTY;
  private final Image zoneModeToggleImage = ImageResource.ZONE_MODE_TOGGLE;
  private final Image roadImage = ImageResource.ROAD;
  private final Image emptyBuildingImage = ImageResource.GRASS;
  private final Image factoryImage = ImageResource.FACTORY;
  private final Image fireStationImage = ImageResource.FIRE_STATION;
  private final Image forestImage = ImageResource.FOREST;
  private final Image policeImage = ImageResource.POLICE;
  private final Image stadiumImage = ImageResource.STADIUM;

  private static FieldTypes selectedFieldZoneType = null;

  private static BuildingTypes selectedBuildingType = null;
  private final JButton[] zoneButtons;
  private final JButton[] buildingButtons;

  public BuildablePanel() {
    super();
    this.setBounds(
            0,
            MainFrame.ZONE_BOX_Y,
            ScreenUtils.getScreenSize().width,
            MainFrame.ZONE_BOX_SIZE);
    this.setBackground(Color.GRAY);

    residentButton = createZoneTypeSelectionButton(residentImage);
    industryButton = createZoneTypeSelectionButton(industryImage);
    serviceButton = createZoneTypeSelectionButton(serviceImage);
    emptyButton = createZoneTypeSelectionButton(emptyImage);

    roadButton = createBuildingTypeSelectionButton(roadImage);
    emptyBuildingButton = createBuildingTypeSelectionButton(emptyBuildingImage);
    factoryButton = createBuildingTypeSelectionButton(factoryImage);
    fireStationButton = createBuildingTypeSelectionButton(fireStationImage);
    forestButton = createBuildingTypeSelectionButton(forestImage);
    policeButton = createBuildingTypeSelectionButton(policeImage);
    stadiumButton = createBuildingTypeSelectionButton(stadiumImage);

    zoneModeToggleButton = createZoneTypeSelectionButton(zoneModeToggleImage);
    zoneModeToggleButton.addMouseListener(
            new MouseAdapter() {
              @Override
              public void mouseClicked(MouseEvent e) {
                toggleZoneMode();
              }
            });

    addClickListenerToZoneTypeSelectionButton(residentButton, FieldTypes.RESIDENT);
    addClickListenerToZoneTypeSelectionButton(industryButton, FieldTypes.INDUSTRY);
    addClickListenerToZoneTypeSelectionButton(serviceButton, FieldTypes.SERVICE);
    addClickListenerToZoneTypeSelectionButton(emptyButton, FieldTypes.EMPTY);

    addClickListenerToBuildingTypeSelectionButton(roadButton, BuildingTypes.ROAD);
    addClickListenerToBuildingTypeSelectionButton(emptyBuildingButton, BuildingTypes.EMPTY);
    addClickListenerToBuildingTypeSelectionButton(factoryButton, BuildingTypes.FACTORY);
    addClickListenerToBuildingTypeSelectionButton(
            fireStationButton, BuildingTypes.FIRE_STATION);
    addClickListenerToBuildingTypeSelectionButton(forestButton, BuildingTypes.FOREST);
    addClickListenerToBuildingTypeSelectionButton(policeButton, BuildingTypes.POLICE);
    addClickListenerToBuildingTypeSelectionButton(stadiumButton, BuildingTypes.STADIUM);

    add(zoneModeToggleButton);
    zoneButtons = new JButton[]{residentButton, industryButton, serviceButton, emptyButton};
    buildingButtons = new JButton[]{roadButton, emptyBuildingButton, factoryButton, fireStationButton, forestButton, policeButton, stadiumButton};

    switchButtons();
  }


  private void showButtons(JButton[] buttons) {
    Arrays.stream(buttons).forEach(this::add);
  }

  private void removeButtons(JButton[] buttons) {
    Arrays.stream(buttons).forEach(this::remove);
  }

  private void switchButtons(){
    if(GamePanel.zoneMode){
      removeButtons(buildingButtons);
      showButtons(zoneButtons);
    }else{
      removeButtons(zoneButtons);
      showButtons(buildingButtons);
    }
    repaint();
  }

  private void toggleZoneMode(){
    GamePanel.zoneMode = !GamePanel.zoneMode;
    GamePanel.gamePanel.repaint();
    switchButtons();
  }

  private JButton createZoneTypeSelectionButton(Image image) {
    JButton newJButton = new JButton(new ImageIcon(image));
    newJButton.setBorder(BorderFactory.createEmptyBorder());
    newJButton.setContentAreaFilled(false);
    return newJButton;
  }

  private JButton createBuildingTypeSelectionButton(Image image) {
    JButton newJButton = new JButton(new ImageIcon(image));
    newJButton.setBorder(BorderFactory.createEmptyBorder());
    newJButton.setContentAreaFilled(false);
    return newJButton;
  }

  private void addClickListenerToZoneTypeSelectionButton(
          JButton button, final FieldTypes buttonFieldType) {
    button.addMouseListener(
            new MouseAdapter() {
              @Override
              public void mouseClicked(MouseEvent e) {
                if (selectedFieldZoneType != buttonFieldType) {
                  resetZoneTypeSelectionButtons();
                  button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, false));
                  button.setContentAreaFilled(true);
                  selectedFieldZoneType = buttonFieldType;
                } else {
                  button.setBorder(BorderFactory.createEmptyBorder());
                  button.setContentAreaFilled(false);
                  selectedFieldZoneType = null;
                }
              }
            });
  }

  private void addClickListenerToBuildingTypeSelectionButton(
          JButton button, final BuildingTypes buildingType) {
    button.addMouseListener(
            new MouseAdapter() {
              @Override
              public void mouseClicked(MouseEvent e) {
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
            });
  }

  private void resetZoneTypeSelectionButtons() {
    resetZoneTypeSelectionButton(residentButton);
    resetZoneTypeSelectionButton(industryButton);
    resetZoneTypeSelectionButton(serviceButton);
    resetZoneTypeSelectionButton(emptyButton);
  }

  public void resetBuildingTypeSelectionButtons() {
    resetBuildingTypeSelectionButton(roadButton);
    resetBuildingTypeSelectionButton(emptyBuildingButton);
    resetBuildingTypeSelectionButton(factoryButton);
    resetBuildingTypeSelectionButton(fireStationButton);
    resetBuildingTypeSelectionButton(forestButton);
    resetBuildingTypeSelectionButton(policeButton);
    resetBuildingTypeSelectionButton(stadiumButton);
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
}
