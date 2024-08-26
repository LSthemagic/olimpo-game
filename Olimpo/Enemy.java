import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Enemy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Enemy extends Person
{
    /**
     * Act - do whatever the Enemy wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
    
        public void randomWalk(int speed, boolean isRightFacing, GreenfootImage[] walkingImages, GreenfootImage[] atackImages) {
        int direction = Greenfoot.getRandomNumber(4);
        
        switch (direction) {
            case 0: 
                setLocation(getX() + speed, getY());
                if (!isRightFacing) {
                    isRightFacing = true;
                    invertImage(walkingImages, atackImages);
                }
                break;
            case 1: 
                setLocation(getX() - speed, getY());
                if (isRightFacing) {
                    isRightFacing = false;
                    invertImage(walkingImages, atackImages);
                }
                break;
            case 2: 
                setLocation(getX(), getY() - speed);
                break;
            case 3: 
                setLocation(getX(), getY() + speed);
                break;
        }

        if (atWorldEdge()) {
            moveToCenter();
        }
    }
    
    private boolean atWorldEdge() {
        int x = getX();
        int y = getY();
        int worldWidth = getWorld().getWidth();
        int worldHeight = getWorld().getHeight();

        return x < 5 || x > worldWidth - 5 || y < 5 || y > worldHeight - 5;
    }
    
    private void moveToCenter() {
        int centerX = getWorld().getWidth() / 2;
        int centerY = getWorld().getHeight() / 2;
        setLocation(centerX, centerY);
    }
}
