import greenfoot.*;

public class FinalScreen extends World {
    
    public FinalScreen() {    
        super(1067, 600, 1); 
        setBackground("world/final.png");
    }

    public void act() {
        
        if (Greenfoot.isKeyDown("enter")) {
            Greenfoot.setWorld(new StartHistoryPt1());
        }
    }
}
