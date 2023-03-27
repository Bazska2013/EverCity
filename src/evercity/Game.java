package evercity;

import java.io.IOException;
import javax.imageio.ImageIO;


public class Game {
    private Field[][] board;
    
    public Game() throws IOException {
        board = new Field[10][20];
        for (int i = 0; i < 10; ++i) {
            for (int j = 0; j < 20; ++j) {
                board[i][j] = new Field(ImageIO.read(getClass().getResource("../pic/fogaskerek.png")));
            }
        }
    }


}
