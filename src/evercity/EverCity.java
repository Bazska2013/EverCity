package evercity;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EverCity {

    public static void main(String[] args){
        try {
            Frame gui = new Frame();
        } catch (IOException ex) {
            Logger.getLogger(EverCity.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
