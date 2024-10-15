import greenfoot.*;

public class StartHistoryPt1 extends World {
    GreenfootSound sound = new GreenfootSound("introducao.mp3");
    public StartHistoryPt1() {    
        super(1067, 600, 1); 
        setBackground("world/inicio historia/inicio1.png");
        sound.play();
    }

    public void act() {
        
        if (Greenfoot.isKeyDown("enter")) {
            Greenfoot.setWorld(new StartHistoryPt2());
        }
    }
}
