import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class MainPerson here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MainPerson extends GoodPerson
{
    // Atributos para movimentação
    private GreenfootImage[] walkingImages;
    private int[] currentImage = {0};  
    private int[] animationCounter = {0};  
    private int animationDelay = 9;
    private boolean isMoving = false;
    private boolean isFacingRight = true;
    private int speed = 3;
    
    // Atributos para ataque
    private GreenfootImage[] atackImages;
    private int[] currentImageAtack= {0};
    private int[] animationCounterAtack= {0};
    private int animationDelayAtack = 5;
    
    // Atributos para morte
    private GreenfootImage[] deadImages;
    private int[] currentImageDead = {0};
    private int[] animationCounterDead= {0};
    private int animationDelayDead = 15;
    
    //atributos de som
    protected GreenfootSound bgSound = new GreenfootSound("fundo.mp3");
    
    //atributos de vida
    private HealthBar healthBar;
    
    public MainPerson(int health){ 
        super(health);
        healthBar = getHealthBar();
        //bgSound.playLoop();
        walkingImages = new GreenfootImage[8];
        atackImages = new GreenfootImage[4];
        deadImages = new GreenfootImage[4];
        
        
        for(int i = 0; i<8; i++){
            walkingImages[i] = new GreenfootImage("Walk"+(i+1)+".png");
            
        }
        setImage(walkingImages[currentImage[0]]);
        
        for(int i = 0; i<4;i++){
            atackImages[i] = new GreenfootImage("MainAtack"+(i+1)+".png");
        }
        
        for(int i = 0; i<4;i++){
            deadImages[i] = new GreenfootImage("mainPersonDead"+(i+1)+".png");
            
        }
    }
     
    public void act(){
            if (getIsDead()) {
                animateDead();
                bgSound.stop();
            } else {
                healthBar.setLocation(getX(), getY() - 30);
                walkPerson(); 
                if (isMoving) {
                    animationPerson(walkingImages, currentImage, animationCounter, animationDelay);
                }
                if(getIsAttacking()){
                    eliminateEnemy(); 
                }
                
                animateAttack();
                if (!isMoving && !getIsAttacking()) { 
                    setImage(new GreenfootImage("Idle1.png"));
                }
                
                if (getIsAttacking() && !Greenfoot.isKeyDown("space") && !isMoving ) {
                    setImage(new GreenfootImage("Idle1.png"));
                }
            }
    }
    
    private void walkPerson(){
        int dx = 0;
        int dy = 0;
        final boolean walkLeft = Greenfoot.isKeyDown("left");
        final boolean walkRight = Greenfoot.isKeyDown("right");
        final boolean walkUp = Greenfoot.isKeyDown("up");
        final boolean walkDown = Greenfoot.isKeyDown("down");
        
        isMoving = walkPerson(walkLeft, walkRight, walkUp, walkDown, speed);
        if(walkLeft){
            dx -= speed;
            if(isFacingRight){
                invertImage(walkingImages, atackImages);
                isFacingRight = false;
            }
        }
        if(walkRight){
            dx += speed;
            if(!isFacingRight){
                invertImage(walkingImages, atackImages);
                isFacingRight = true;
            }
        }
    }
    
    private void animateAttack(){
        final boolean keyAttack = Greenfoot.isKeyDown("space");
        animateAtackPerson(keyAttack, atackImages, currentImageAtack, animationCounter, animationDelayAtack);
    }

    
    private void animateDead() {
        boolean animationFinished = animationPerson(deadImages, currentImageDead, animationCounterDead, animationDelayDead, false);
        // Verifica se a animação terminou  
        if (animationFinished) {
            Greenfoot.setWorld(new GameOverScreen());
        }
    }

}
