import greenfoot.*;

public class StartScreen extends World {

    public StartScreen() {    
        
        super(600, 400, 1); 
         
       
        setBackground("startscreen.jpg");
        
        showText("Pressione 'Enter' para começar", getWidth() / 2, getHeight() / 2);
    }

    public void act() {
        
        if (Greenfoot.isKeyDown("enter")) {
            Greenfoot.setWorld(new FirstWorld());
        }
    }
}
