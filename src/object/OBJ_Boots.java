package object;

import java.io.IOException;
import javax.imageio.ImageIO;



public class OBJ_Boots extends superopject {
    public OBJ_Boots(){
        name = "Boots";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/object/boots.png"));
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}