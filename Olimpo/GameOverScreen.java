import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GameOver here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameOverScreen extends World
{

    /**
     * Constructor for objects of class GameOver.
     * 
     */
    public GameOverScreen()
    {    
        super(600, 400, 1); 
        setBackground(new GreenfootImage("world/gameover.jpg"));
        showText("Para jogar novamente pressione 'Enter' \n caso contrário pressione 'Esc'", getWidth() / 2, getHeight() / 2);
    }
    
    public void act(){
               
        if (Greenfoot.isKeyDown("enter")) {
            Greenfoot.setWorld(new FirstWorld());
        }
         if (Greenfoot.isKeyDown("escape")) {
          Greenfoot.stop();
        }
    
    }
}
