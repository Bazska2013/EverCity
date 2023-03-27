package evercity;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Board {
    private JButton[][] buttons;
    private Game game;
    private JPanel boardPanel;
    
    public Board() throws IOException {

        Toolkit tk=Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        System.out.println(screenSize); 
        
        game = new Game();
        boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(10, 20));      
      
        buttons = new JButton[10][20];
        for (int i = 0; i < 10; ++i) {
            for (int j = 0; j < 20; ++j) {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(60, 60));
                try {
                    Image img = ImageIO.read(getClass().getResource("../pic/grass.jpg"));
                    button.setIcon(new ImageIcon(img));
                 } catch (Exception ex) {
                    System.out.println(ex);
                 }
                
                //button.addActionListener(new ButtonListener(i, j));
                buttons[i][j] = button;
                boardPanel.add(button);
            }
        }
    }
    
    public JPanel getBoardPanel() {
        return boardPanel;
    }
    
    
    /*class ButtonListener implements ActionListener {

        private int x, y;

        public ButtonListener(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            
            refresh();
            }
        }*/
    
    
    
}
