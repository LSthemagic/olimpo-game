import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Bebe here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bebe extends Actor
{
    
    /**
     * Act - do whatever the Bebe wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
        walkPerson();
    }
    
    private void walkPerson(){
        int dx = 0;
        int dy = 0;
        final boolean walkLeft = Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("a");
        final boolean walkRight = Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("d");
        final boolean walkUp = Greenfoot.isKeyDown("up") || Greenfoot.isKeyDown("w");
        final boolean walkDown = Greenfoot.isKeyDown("down") || Greenfoot.isKeyDown("s");
        
        if(walkLeft){
            dx -= 4;
        }
         if(walkRight){
            dx += 4;
        }
         if(walkUp){
            dy -= 4;
        }
         if(walkDown){
            dy += 4;
        }
        if (dx != 0 || dy !=0 ){
            if(!colisionObject(dx, dy)){
                setLocation(getX() + dx, getY() + dy);
            }
        }
        System.out.println(colisionObject(dx, dy));
    }
    
    
    private boolean colisionObject(int dx, int dy){
        int futureX = getX() + dx;
        int futureY = getY() + dy;
        
        setLocation(futureX, futureY);
        
        boolean colision = isTouching(WallCorner.class);
        
        
        setLocation(getX() - dx, getY() - dy);
        
        return colision;
    }
}
