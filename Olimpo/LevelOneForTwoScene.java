import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class LevelOneForTwoScene here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LevelOneForTwoScene extends World
{
    public LevelOneForTwoScene()
    {    
        super(1067, 600, 1);
        setBackground("LevelOneForTwo.png");
    }
    
    public void act() {
        
        if (Greenfoot.isKeyDown("enter")) {
            Greenfoot.setWorld(new SecondWorld());
        }
    }
}
