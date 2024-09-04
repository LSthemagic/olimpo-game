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
    protected static int quantityGoodPersonsDead = 0; 
    int delay = 10;
    int count = 0;
    
    public GoodPerson(int health) {
        super(health);
    }
    
    public void gameOverScreen() {
        if (getIsDead()) {
            quantityGoodPersonsDead++;
            System.out.println("lonely..."+ quantityGoodPersonsDead);
        }
        
        if (quantityGoodPersonsDead >= 2) {
            Greenfoot.playSound("gameover.mp3");
            System.out.println("bem amigos, acabou...");
            Greenfoot.setWorld(new GameOverScreen());
        }
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
                if (Greenfoot.isKeyDown("space") || Greenfoot.isKeyDown("q")) {
                    count++;
                    if(count >= delay){
                        count = 0;
                        enemy.updateHealth(powerAttack);
                    }
                }
                if (enemy.getHealth() <= 0) {
                    getWorld().removeObject(enemy);
                    Greenfoot.playSound("win.mp3");
                }
                List<Enemy> listEnemys = getWorld().getObjects(Enemy.class);
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
