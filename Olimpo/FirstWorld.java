import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class world here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FirstWorld extends World
{
    private HealthBar healthBar;
    public FirstWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 500, 1); 
        GoodPerson mainPerson = (MainPerson) new MainPerson(100);
        GoodPerson second = (SecondPerson) new SecondPerson(100);
        Enemy enemy = (TesteEnemy) new TesteEnemy(100);
        
        addObject(mainPerson, 100, 100); 
        addObject(second, 400, 100);
        addObject(enemy, 300, 350);
    }
}
