import greenfoot.*;

public class StartScreen extends World {

    public StartScreen() {    
        
        super(1067, 600, 1); 
         
       
        setBackground("world/startScreen.png");
    }

    public void act() {
        
        if (Greenfoot.isKeyDown("enter")) {
            Greenfoot.setWorld(new FirstWorld());
        }
    }
}
