import greenfoot.*;

public class StartScreen extends World {

    public StartScreen() {    
        
        super(600, 400, 1); 
         
       
        setBackground("startscreen.jpg");
        
        showText("Pressione 'Enter' ou 'Space' para começar", getWidth() / 2, getHeight() / 2);
    }

    public void act() {
        
        if (Greenfoot.isKeyDown("enter") || Greenfoot.isKeyDown("space")) {
            Greenfoot.setWorld(new FirstWorld());
        }
    }
}
