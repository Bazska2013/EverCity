package org.elte.view.common;

import javax.swing.*;
import java.awt.*;

public class CustomButton extends JButton {
  public CustomButton(String text){
    super(text);
    this.setBackground(new Color(3, 193, 60));
    this.setForeground(Color.WHITE);
    this.setFocusPainted(false);
    this.setFont(new Font("Tahoma", Font.BOLD, 12));
  }
}
