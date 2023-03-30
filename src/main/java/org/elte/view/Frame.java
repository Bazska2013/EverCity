package org.elte.view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import org.elte.draw.Draw;
import org.elte.model.Game;

public class Frame {
    private JFrame frame;
    Draw d;
    Game g = new Game();
    int balance = 20000;
    String time = "2020-02-20";

    public void create() throws IOException{

        Toolkit tk=Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();

        frame = new JFrame("EverCity");
        frame.setSize(screenSize.width,screenSize.height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        //Image img = ImageIO.read(getClass().getResource("../pic/fogaskerek.png"));
        //Image icon = img.getScaledInstance(15, 15, Image.SCALE_DEFAULT);

        JMenu menu = new JMenu("Menü");
        //menu.setIcon(new ImageIcon(icon));

        menuBar.add(menu);

        JMenuItem newGame = new JMenuItem("Új játék");
        menu.add(newGame);

        JMenuItem exitMenuItem = new JMenuItem("Kilépés");
        menu.add(exitMenuItem);
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.exit(0);
            }
        });

        menuBar.add( Box.createHorizontalStrut(30 ) );

        JMenu balancemenu = new JMenu("Egyenleg: " + balance + "$" );
        //balancemenu.setEnabled(false);
        menuBar.add(balancemenu);

        menuBar.add( Box.createHorizontalStrut(50 ) );

        JMenu feelingmenu = new JMenu("Jókedvmérő" );
        //balancemenu.setEnabled(false);
        menuBar.add(feelingmenu);


        menuBar.add(Box.createHorizontalGlue());
        JMenu timemenu = new JMenu(time);
        //timemenu.setEnabled(false);
        menuBar.add(timemenu);

        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setResizable(false);
        //frame.addMouseListener(new MouseHandler());

        d = new Draw();
        d.setBounds(0,0,screenSize.width,screenSize.height);
        d.setVisible(true);
        frame.add(d);

        frame.requestFocus();
        frame.setVisible(true);

    }
}
