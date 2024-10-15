import greenfoot.*;

public class StartHistoryPt3 extends World {

    public StartHistoryPt3() {    
        super(1067, 600, 1); 
        setBackground("world/inicio historia/inicio3.png");
    }

    public void act() {
        
        if (Greenfoot.isKeyDown("enter")) {
            Greenfoot.setWorld(new StartScreen());
        }
    }
}
