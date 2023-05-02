package org.elte.view.game.gamepanel;

import lombok.Getter;
import lombok.Setter;
import org.elte.model.Game;
import org.elte.utils.Position;
import org.elte.utils.ScreenUtils;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter
public class InfoBox extends JPanel {
    public static final int X = (int) (ScreenUtils.getScreenSize().width * 0.87);
    public static final int Y = (int) (ScreenUtils.getScreenSize().height * 0.3);
    public static final int HEIGHT = (int) (ScreenUtils.getScreenSize().height * 0.10);
    public static final int WIDTH = (int) (ScreenUtils.getScreenSize().width * 0.10);
    private Game game;
    private JLabel type;
    private JLabel message;

    public InfoBox() {
        super();
        this.game = Game.getInstance();
        this.setBounds(X, Y, WIDTH, HEIGHT);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        this.setLayout(new GridLayout(2, 1));
        this.setBackground(Color.BLUE);
        this.setVisible(true);

        Font labelFont = new Font("Arial", Font.PLAIN, 12);

        type = new JLabel();
        type.setForeground(Color.white);
        type.setFont(labelFont);
        this.add(type);

        message = new JLabel();
        message.setForeground(Color.white);
        message.setFont(labelFont);
        this.add(message);

        reRender("", "");
    }

    public void reRender(String type, String message) {
        this.type.setText(type);
        this.message.setText(message);
    }
}
