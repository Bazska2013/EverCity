package org.elte.view.game;

import org.elte.model.Game;
import org.elte.model.enums.FieldTypes;
import org.elte.utils.ImageResource;
import org.elte.utils.ScreenUtils;
import org.elte.view.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BuildablePanel extends JPanel {

  private final JButton residentButton;
  private final JButton industryButton;
  private final JButton serviceButton;
  private final JButton emptyButton;
  private final JButton zoneModeToggleButton;

  private final Image residentImage = ImageResource.ZONE_RESIDENT;
  private final Image industryImage = ImageResource.ZONE_INDUSTRY;
  private final Image serviceImage = ImageResource.ZONE_SERVICE;
  private final Image emptyImage = ImageResource.ZONE_EMPTY;
  private final Image zoneModeToggleImage = ImageResource.ZONE_MODE_TOGGLE;

  private static FieldTypes selectedFieldZoneType = null;

  public BuildablePanel() {
    super();
    this.setBounds(0, MainFrame.ZONE_BOX_Y, ScreenUtils.getScreenSize().width, MainFrame.ZONE_BOX_SIZE);
    this.setBackground(Color.GRAY);

    residentButton = createZoneTypeSelectionButton(residentImage);
    industryButton = createZoneTypeSelectionButton(industryImage);
    serviceButton = createZoneTypeSelectionButton(serviceImage);
    emptyButton = createZoneTypeSelectionButton(emptyImage);

    zoneModeToggleButton = createZoneTypeSelectionButton(zoneModeToggleImage);
    zoneModeToggleButton.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        GamePanel.zoneMode = !GamePanel.zoneMode;
        GamePanel.gamePanel.repaint();
      }
    });

    addClickListenerToZoneTypeSelectionButton(residentButton, FieldTypes.RESIDENT);
    addClickListenerToZoneTypeSelectionButton(industryButton, FieldTypes.INDUSTRY);
    addClickListenerToZoneTypeSelectionButton(serviceButton, FieldTypes.SERVICE);
    addClickListenerToZoneTypeSelectionButton(emptyButton, FieldTypes.EMPTY);

    add(zoneModeToggleButton);
    add(residentButton);
    add(industryButton);
    add(serviceButton);
    add(emptyButton);

  }

  private JButton createZoneTypeSelectionButton(Image image) {
    JButton newJButton = new JButton(new ImageIcon(image));
    newJButton.setBorder(BorderFactory.createEmptyBorder());
    newJButton.setContentAreaFilled(false);
    return newJButton;
  }

  private void addClickListenerToZoneTypeSelectionButton(JButton button, final FieldTypes buttonFieldType) {
    button.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        if(selectedFieldZoneType != buttonFieldType) {
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

  private void resetZoneTypeSelectionButtons() {
    resetZoneTypeSelectionButton(residentButton);
    resetZoneTypeSelectionButton(industryButton);
    resetZoneTypeSelectionButton(serviceButton);
    resetZoneTypeSelectionButton(emptyButton);
  }

  private void resetZoneTypeSelectionButton(JButton button) {
    button.setBorder(BorderFactory.createEmptyBorder());
    button.setContentAreaFilled(false);
  }

  public static FieldTypes getSelectedFieldZoneType() {
    return selectedFieldZoneType;
  }
}
