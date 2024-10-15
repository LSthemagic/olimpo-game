import greenfoot.*;

public class FinalHistoryPt4 extends World {
    //GreenfootSound sound = new GreenfootSound("honra ao merito.mp3");
    public FinalHistoryPt4() {    
        super(1067, 600, 1); 
        setBackground("world/final historia/final4.png");
        //sound.play();
    }

    public void act() {
        
        if (Greenfoot.isKeyDown("enter")) {
            Greenfoot.setWorld(new FinalScreen());
        }
    }
}
