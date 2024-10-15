import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class SecondEnemy extends Enemy
{
    private GreenfootImage[] walkImages;
    private GreenfootImage[] attackImages;
    private GreenfootImage[] deadImages;
    private int[] currentImage = {0};  
    private int[] animationCounter = {0};  
    private int animationDelay = 9;
    boolean deadAnimationFinished = false;
    
    private Dust dust;
    public SecondEnemy(int health){
        super(health);
        dust = getDust();
        setImage("persons/SecondEnemy/_Idle.png");

        walkImages = new GreenfootImage[10];
        for(int i = 0; i < walkImages.length; i++){
            walkImages[i] = new GreenfootImage("persons/SecondEnemy/_Run/_Run (0_" + i + ").png");
        }
        
        attackImages = new GreenfootImage[4];
        for(int i = 0; i < attackImages.length; i++){
            attackImages[i] = new GreenfootImage("persons/SecondEnemy/_Attack/_Attack (0_" + i + ").png");
        }
        
        deadImages = new GreenfootImage[10];
        for(int i = 0; i < deadImages.length; i++){
            deadImages[i] = new GreenfootImage("persons/SecondEnemy/_Death/_Death (0_" + i + ").png");
        }
    }

    public void act()
    {
        if (getIsDead()) {
            animateDead();
            if (deadAnimationFinished && isMarkedForRemoval()) {
                getWorld().removeObject(this);
            }
            dust.startFadingOut();
            return;
        } else {
            followMainPerson(1, walkImages, attackImages);
            checkCollisionAndAttack();
            if (!getIsAttacking()) {
                animationPerson(walkImages, currentImage, animationCounter, animationDelay);
                addDust();
            }else{
                dust.startFadingOut();
            }
            
            if(getIsAttacking()){
                animationPerson(attackImages, currentImage, animationCounter, attackImages.length + 4);
                killMainPerson(3);
            }
          
        }
    }
    
    private void addDust(){
        if(dust.getFadingOut()){
            dust.resetTransparency();
        }
        //dust.resetTransparency();
        getWorld().addObject(dust, getX() - 30, getY() + 30);
        if(!getIsInverted()){
            dust.setLocation(getX() - 30, getY() + 30);
        }else{
            dust.setLocation(getX() + 40, getY() + 30);
        }
    }
    
    private void animateDead(){
        deadAnimationFinished = animationPerson(deadImages, currentImage, animationCounter, deadImages.length + 1, false);
    }
    
}
