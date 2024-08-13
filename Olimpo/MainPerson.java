import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MainPerson here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MainPerson extends Actor
{
    /**
     * Act - do whatever the MainPerson wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    
    private GreenfootImage[] walkingImages;
    private int currentImage = 0;
    private int animationCounter = 0;
    private int animationDelay = 9;
    private boolean isMoving = false;
    private boolean isFacingRight = true;
    
    public MainPerson(){
        walkingImages = new GreenfootImage[8];
        for(int i = 0; i<8; i++){
            walkingImages[i] = new GreenfootImage("Walk"+(i+1)+".png");
            System.out.println(walkingImages[i]);
        }
        setImage(walkingImages[currentImage]);
    }
    
    public void act()
    {
        // Add your action code here.
        walkPerson();  
        if(isMoving){
            animatePerson();
        }
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
            if(isFacingRight){
                invertImage();
                isFacingRight = false;
            }
        }
         if(walkRight){
            dx += 4;
            if(!isFacingRight){
                invertImage();
                isFacingRight = true;
            }
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
                isMoving = true;
            }
        }else{
            isMoving = false;
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
    
    private void animatePerson(){
        if(animationCounter == animationDelay){
            currentImage = (currentImage + 1) % walkingImages.length;
            setImage(walkingImages[currentImage]);
            animationCounter = 0;
        }else{
            animationCounter++;
        }
    }
    
    private void invertImage(){
        for (GreenfootImage img: walkingImages){
            img.mirrorHorizontally();
        }
    }
}
