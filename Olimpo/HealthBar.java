import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class HealthBar here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HealthBar extends Actor
{   
    private int health;
    private int maxHealth;
    private int barWidth;
    private int barHeight;
    private int pixelsPerHealthPoint;
   
    public HealthBar(int maxHealth, int barWidth, int barHeight){    
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.barWidth = barWidth;
        this.barHeight = barHeight;
        this.pixelsPerHealthPoint = barWidth / maxHealth;
        updateHealthBar();
    }
   
    private void updateHealthBar(){
        GreenfootImage image = new GreenfootImage(barWidth + 2, barHeight + 2);
        image.setColor(Color.BLACK);
        image.drawRect(0, 0, barWidth + 1, barHeight + 1);
        image.setColor(Color.RED);
        image.fillRect(1,1,health * pixelsPerHealthPoint, barHeight);
        setImage(image);
    }  
    
     public void loseHealth(int amount) {
        health -= amount;
        if (health < 0) {
            health = 0;
        }
        updateHealthBar();
    }

    public void gainHealth(int amount) {
        health += amount;
        if (health > maxHealth) {
            health = maxHealth;
        }
        updateHealthBar();
    }

    public int getHealth() {
        return health;
    }
    
}
