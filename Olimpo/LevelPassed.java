import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class LevelPassed here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LevelPassed extends World
{

    World proxWorld;
    GreenfootSound sound = new GreenfootSound("tambores.mp3");
    public LevelPassed(World proxWorld)
    {    
        super(1000, 600, 1);   
        this.proxWorld = proxWorld;
        setBackground("LevelPassed.png");
        sound.play();
    }
    
    public void act() {
        
        if (Greenfoot.isKeyDown("enter")) {
            if(proxWorld.getClass().getName().equalsIgnoreCase("FirstWorld")){
                Greenfoot.setWorld(new LevelOneForTwoScene());
            }else if (proxWorld.getClass().getName().equalsIgnoreCase("SecondWorld")){
                Greenfoot.setWorld(new LevelTwoForThreeScene());
            }else{
                Greenfoot.setWorld(new FinalHistoryPt1());
            }
        }
    }
}
