import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class FirstWorld extends World
{
    //private HealthBar healthBar;
    public FirstWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 500, 1); 
        //setBackground("background-colessaum-test.jpg");
        GoodPerson mainPerson = (MainPerson) new MainPerson(10);
        GoodPerson second = (SecondPerson) new SecondPerson(10);
        Enemy enemy1 = (TesteEnemy) new TesteEnemy(100);
        Enemy enemy2 = (TesteEnemy) new TesteEnemy(100);
        
        addObject(mainPerson, 200, 150); 
        addObject(second, 400, 100);
        addObject(enemy1, 200, 350);
        addObject(enemy2, 700, 250);
        setPaintOrder(Blood.class, Person.class, VictoryScene.class ,Effects.class);        
    }
}
