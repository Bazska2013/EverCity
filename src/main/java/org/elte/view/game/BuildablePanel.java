package org.elte.view.game;

import org.elte.utils.ScreenUtils;
import org.elte.view.MainFrame;

import javax.swing.*;
import java.awt.*;

public class BuildablePanel extends JPanel {
  public BuildablePanel() {
    super();
    this.setBounds(0, MainFrame.ZONE_BOX_Y, ScreenUtils.getScreenSize().width, MainFrame.ZONE_BOX_SIZE);
    this.setBackground(Color.GRAY);
  }

}
