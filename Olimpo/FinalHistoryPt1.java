import greenfoot.*;

public class FinalHistoryPt1 extends World {
    GreenfootSound sound = new GreenfootSound("honra ao merito.mp3");
    public FinalHistoryPt1() {    
        super(1067, 600, 1); 
        setBackground("world/final historia/final1.png");
        sound.play();
    }

    public void act() {
        
        if (Greenfoot.isKeyDown("enter")) {
            Greenfoot.setWorld(new FinalHistoryPt2());
        }
    }
}
