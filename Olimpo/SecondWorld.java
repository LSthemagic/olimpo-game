import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SecondWord here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SecondWorld extends World
{

    private GoodPerson mainPerson;
    private GoodPerson second;
    private int weatherTimer = 0;
    private int weatherType = 0;
    private boolean addEffect = false;
    
    // Tolerâncias para o teletransporte
    private static final int TOLERANCE_X = 20;
    private static final int TOLERANCE_Y = 10;
    
    public SecondWorld()
    {    
        super(1000, 600, 1); 
        setBackground("world/coliseu2.png");
        
        mainPerson = new MainPerson(100);
        second = new SecondPerson(100);
                
        Enemy enemy1 = new FirstEnemy(1);
        Enemy enemy2 = new SecondEnemy(1);
        Enemy enemy3 = new ThirdEnemy(1);
        // Adiciona objetos ao FirstWorld
        addObject(mainPerson, 510, 300); 
        addObject(second, 500, 200);
        
        addObject(enemy1, 350, 350);
        addObject(enemy2, 370, 100);
        addObject(enemy3, 300, 190);
        
        // Define a ordem de pintura para sobreposição
        setPaintOrder(Blood.class, Person.class, VictoryScene.class, Effects.class);
    }
    
    @Override
    public void act() {
        TeleportUtils.teleportToHospital(this, 535, 10, TOLERANCE_X, TOLERANCE_Y);
        weatherTimer++;
        if (weatherTimer > 600) {
            weatherTimer = 0;
            invertEffect();
        }
        if(addEffect){
            if (Greenfoot.getRandomNumber(100) < 50) {
                int x = Greenfoot.getRandomNumber(getWidth());
                addObject(new Raindrop(), x, 0);
            }
            
        }    
    }

    private void invertEffect(){
        addEffect = !addEffect;
    }

}
