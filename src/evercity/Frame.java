package evercity;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Frame {
    
    private JFrame frame;
    private Board board;
    int balance = 20000;
    String time = "2020-02-20";
    //private JPanel boardPanel;

    public Frame() throws IOException {

        frame = new JFrame("EverCity");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
        board = new Board();
        frame.getContentPane().add(board.getBoardPanel(), BorderLayout.CENTER);
        frame.setResizable(false);

        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        Image img = ImageIO.read(getClass().getResource("../pic/fogaskerek.png"));
        Image icon = img.getScaledInstance(15, 15, Image.SCALE_DEFAULT);
        
        JMenu menu = new JMenu("");
        menu.setIcon(new ImageIcon(icon));
        
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

        frame.pack();
        frame.setVisible(true);
    }
    
}
