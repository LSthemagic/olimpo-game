import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class SecondPerson extends GoodPerson {
    
    // Atributos de imagem
    private GreenfootImage[] walkingImages;
    private GreenfootImage[] attackingImages;
    private int[] currentImage = {0};
    private int[] animationCounter = {0};
    private int animationDelay = 6; 
    private boolean isRightFacing = true; 
    private int speed = 3;
    private int moveCounter = 0;
    private int moveDelay = 7; 
    private boolean isMoving = false;
    
    // Atributos de ataque
    private int[] currentImageAttack = {0};
    private int[] animationCounterAttack = {0};
    private int animationDelayAttack = 10; 
    
    //atributos de vida
    //private HealthBar healthBar;
    
    private boolean isFacingRight = true;
    private Dust dust;
    
     
    public SecondPerson(int health) {
        super(health);
        //healthBar = getHealthBar();
        dust = getDust();
        walkingImages = new GreenfootImage[8];
        attackingImages = new GreenfootImage[4];
        for (int i = 0; i < 8; i++) {
            walkingImages[i] = new GreenfootImage("SecondPersonWalk" + (i + 1) + ".png");
        }
        for (int i = 0; i < 4; i++) {
            attackingImages[i] = new GreenfootImage("SecondPersonAttack" + (i + 1) + ".png");
        }
        setImage(walkingImages[currentImage[0]]);
    }

    public void act() {
        if (getIsDead()) {
            getWorld().removeObject(this);
            gameOverScreen();
            return;
        } else {
            //healthBar.setLocation(getX(), getY() - 30);
            animateAttack();
            walking();
                if (isMoving) {
                    animationPerson(walkingImages, currentImage, animationCounter, animationDelay);
                    addDust();
                }else{
                    dust.startFadingOut();
                }
                
                if(getIsAttacking()){
                    eliminateEnemy(); 
                }
                
                animateAttack();
                if (!isMoving && !getIsAttacking()) { 
                    setImage(new GreenfootImage("SecondPersonIdle.png"));
                }
                
                if (getIsAttacking() && !Greenfoot.isKeyDown("q") && !isMoving ) {
                    setImage(new GreenfootImage("SecondPersonIdle.png"));
                }
            }
             /*if (moveCounter >= moveDelay) {
                moveCounter = 0;
                //followMainPerson(speed, walkingImages, attackingImages);
            } else {
                moveCounter++;
            }*/
            
        
        
        //checkCollisionAndAttack();
        }
           
    
    
     private void addDust(){
        if(dust.getFadingOut()){
            dust.resetTransparency();
        }
        //dust.resetTransparency();
        getWorld().addObject(dust, getX() - 30, getY() + 30);
        if(isFacingRight){
            dust.setLocation(getX() - 30, getY() + 30);
        }else{
            dust.setLocation(getX() + 40, getY() + 30);
        }
    }

    
    private void walking(){
        final boolean walkLeft = Greenfoot.isKeyDown("a");
        final boolean walkRight = Greenfoot.isKeyDown("d");
        final boolean walkUp = Greenfoot.isKeyDown("w");
        final boolean walkDown = Greenfoot.isKeyDown("s");
        
        isMoving = walkPerson(walkLeft, walkRight, walkUp, walkDown,speed);
       
        if(walkLeft){
            if(isFacingRight){
                invertImage(walkingImages, attackingImages);
                isFacingRight = false;
            }
        }
        if(walkRight){
            if(!isFacingRight){
                invertImage(walkingImages, attackingImages);
                isFacingRight = true;
            }
        }
    }
    
    private void animateAttack(){
        final boolean key = Greenfoot.isKeyDown("q");
        animateAtackPerson(key, attackingImages, currentImageAttack, animationCounterAttack, animationDelayAttack);
    }


}
