import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class GoodPerson here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GoodPerson extends Person
{
    protected static int powerAttack = 1;
    private boolean isAtacking = false;
    
    public void act()
    {
        // Add your action code here.
    }
    
    public GoodPerson(int health) {
        super(health);
    }
    
    protected void animateAtackPerson(boolean key, GreenfootImage[] images, int[] currentImage, int[] animationCounter, int delay) {
        if (key) {
            if (!isAtacking) {
                isAtacking = true;
                Greenfoot.playSound("espada.mp3");
            }
            animationPerson(images, currentImage, animationCounter, delay);
        } else {
            isAtacking = false; // Reseta o estado de ataque
        }
    }
    
    protected void eliminateEnemy() {
        if (isTouching(Enemy.class)) {
            Enemy enemy = (Enemy) getOneIntersectingObject(Enemy.class);
            
            if (enemy != null) {
                // Verifica a quantidade de inimigos restantes após remover o derrotado
                List<Enemy> listEnemys = getWorld().getObjects(Enemy.class);
                
                if (Greenfoot.isKeyDown("space") || Greenfoot.isKeyDown("q")) {
                    enemy.updateHealth(powerAttack);
                }
    
                if (enemy.getHealth() <= 0) {
                    getWorld().removeObject(enemy); // Remove o inimigo do mundo
                    Greenfoot.playSound("win.mp3");
                }
                
              
                
                if (listEnemys.size() <= 0) {
                    Greenfoot.playSound("victory.mp3");
                    getWorld().addObject(new VictoryScene(), 375, 250);
                }
                
              
            }
        }
    }
    
    public boolean getIsAttacking(){
        return isAtacking;
    }
}
