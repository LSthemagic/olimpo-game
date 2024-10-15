import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class BossEnemy extends Enemy
{
    private GreenfootImage[] walkImages;
    private GreenfootImage[] attackImages;
    private GreenfootImage[] deadImages;
    private int[] currentImage = {0};  
    private int[] animationCounter = {0};  
    private int animationDelay = 9;
    
    private Dust dust;
    public BossEnemy(int health){
        super(health);
        dust = getDust();
        setImage("persons/BossEnemy/demon_idle_1.png");
        setImageIsInverse(true);
        walkImages = new GreenfootImage[11];
        for(int i = 0; i < walkImages.length; i++){
            walkImages[i] = new GreenfootImage("persons/BossEnemy/walk/demon_walk_" + (i + 1)  + ".png");
        }
        
        attackImages = new GreenfootImage[14];
        for(int i = 0; i < attackImages.length; i++){
            attackImages[i] = new GreenfootImage("persons/BossEnemy/Attack/demon_cleave_" + (i + 1) + ".png");
        }
        
        deadImages = new GreenfootImage[21];
        for(int i = 0; i < deadImages.length; i++){
            deadImages[i] = new GreenfootImage("persons/BossEnemy/death/demon_death_" + (i + 1) + ".png");
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
                killMainPerson(5);
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
