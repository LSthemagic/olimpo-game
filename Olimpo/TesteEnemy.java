import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class TesteEnemy extends Enemy
{
    public void act()
    {
        checkCollisionAndAttack();
        if(getIsAttacking()){
            killMainPerson();
        }
        
        if(getHealth() <= 0 ){
            removePerson(this);
        }
        
        followMainPerson(1, null, null);
    }
    
    public TesteEnemy(int health){
        super(health);
    }
}
