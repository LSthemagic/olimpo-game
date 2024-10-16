import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

public class FirstWorld extends World {
    
    private GoodPerson mainPerson;
    private GoodPerson second;
    private int weatherTimer = 0;
    private int weatherType = 0;
    private boolean addEffect = false;
    
    // Tolerâncias para o teletransporte
    private static final int TOLERANCE_X = 20;
    private static final int TOLERANCE_Y = 10;
    private GreenfootSound bgSound = new GreenfootSound("fundo.mp3");

    public FirstWorld() {    
        super(900, 600, 1); 
        
        setBackground("world/coliseu.png");
        
        mainPerson = new MainPerson(100);
        second = new SecondPerson(100);
        
        Enemy enemy1 = new FirstEnemy(1);
        Enemy enemy2 = new FirstEnemy(1);
        Enemy enemy3 = new FirstEnemy(1);
        // Adiciona objetos ao FirstWorld
        addObject(mainPerson, 500, 300); 
        addObject(second, 500, 200);
        
        addObject(enemy1, 200, 350);
        addObject(enemy2, 200, 270);
        addObject(enemy3, 200, 190);
        
        // Define a ordem de pintura para sobreposição
        setPaintOrder(Blood.class, Person.class, VictoryScene.class, Effects.class);   
        
    }
    
    @Override
    public void act() {
        TeleportUtils.teleportToHospital(this, 510, 10, TOLERANCE_X, TOLERANCE_Y);
        weatherTimer++;
        bgSound.setVolume(25);
        bgSound.play();
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