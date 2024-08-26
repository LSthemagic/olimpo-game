import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class world here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FirstWorld extends World
{
    public FirstWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        MainPerson mainPerson = new MainPerson();
       
        addObject(mainPerson, 100, 100); 
        
        Enemy enemy = new FirstEnemy();
        Enemy enemy1 = new FirstEnemy();
        Enemy enemy2 = new FirstEnemy(); 
        Enemy enemy3 = new FirstEnemy();
        Enemy enemy4 = new FirstEnemy();
        addObject(enemy, 400, 100);
        addObject(enemy1, 350, 120);
        addObject(enemy2, 400, 10);
        addObject(enemy3, 200, 210);
        addObject(enemy4, 100, 350);
        
        
    }
}
