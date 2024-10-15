import greenfoot.*;

public class StartHistoryPt2 extends World {

    public StartHistoryPt2() {    
        super(1067, 600, 1); 
        setBackground("world/inicio historia/inicio2.png");
    }

    public void act() {
        
        if (Greenfoot.isKeyDown("enter")) {
            Greenfoot.setWorld(new StartHistoryPt3());
        }
    }
}
