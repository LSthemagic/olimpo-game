import greenfoot.*;

public class FinalHistoryPt2 extends World {
    //GreenfootSound sound = new GreenfootSound("honra ao merito.mp3");
    public FinalHistoryPt2() {    
        super(1067, 600, 1); 
        setBackground("world/final historia/final2.png");
        //sound.play();
    }

    public void act() {
        
        if (Greenfoot.isKeyDown("enter")) {
            Greenfoot.setWorld(new FinalHistoryPt3());
        }
    }
}
