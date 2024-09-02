import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TesteEnemy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TesteEnemy extends Enemy
{
    /**
     * Act - do whatever the TesteEnemy wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        checkCollisionAndAttack();
        if(getIsAttacking()){
            killMainPerson();
        }
        
        if(getHealth() <= 0 ){
            removePerson(this);
        }
    }
    
    public TesteEnemy(int health){
        super(health);
    }
}
