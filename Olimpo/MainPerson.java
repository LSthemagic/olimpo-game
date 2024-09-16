import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

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
    //private HealthBar healthBar;
    
    //atributos para efeitos
    private Dust dust;
    
    public MainPerson(int health){ 
        super(health);
        //healthBar = getHealthBar();
        dust = getDust();
        //bgSound.playLoop();
        walkingImages = new GreenfootImage[8];
        atackImages = new GreenfootImage[4];
        deadImages = new GreenfootImage[4];
        
        
        for(int i = 0; i<8; i++){
            walkingImages[i] = new GreenfootImage("persons/mainPerson/walk/Walk"+(i+1)+".png");
        }
        setImage(walkingImages[currentImage[0]]);
        
        for(int i = 0; i<4;i++){
            atackImages[i] = new GreenfootImage("persons/mainPerson/attack/MainAtack"+(i+1)+".png");
        }
        
        for(int i = 0; i<4;i++){
            deadImages[i] = new GreenfootImage("persons/mainPerson/dead/mainPersonDead"+(i+1)+".png");
            
        }
    }
     
    public void act(){
            if (getIsDead()) {
                animateDead();
                removePerson(this);
                bgSound.stop();
                dust.startFadingOut();
                gameOverScreen();
                return;
            } else {
                walkPerson(); 
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
                    setImage(new GreenfootImage("persons/mainPerson/idle/Idle1.png"));
                }
                
                if (getIsAttacking() && !Greenfoot.isKeyDown("space") && !isMoving ) {
                    setImage(new GreenfootImage("persons/mainPerson/idle/Idle1.png"));
                }
            }
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
        /*if (animationFinished) {
            Greenfoot.setWorld(new GameOverScreen());
        }*/
    }

}
