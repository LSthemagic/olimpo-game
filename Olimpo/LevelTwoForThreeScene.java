import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class LevelTwoForThreeScene here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LevelTwoForThreeScene extends World
{
    GreenfootSound sound = new GreenfootSound("TERCEIRA FASE.mp3");
    public LevelTwoForThreeScene()
    {    
        super(1067, 600, 1);
        setBackground("LevelTwoForThree.png");
        sound.play();
    }
    
    public void act() {
        
        if (Greenfoot.isKeyDown("enter")) {
            Greenfoot.setWorld(new ThirdWorld());
        }
    }
}
