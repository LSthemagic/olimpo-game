import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MainPerson here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MainPerson extends Person
{
    // Atributos para movimentação
    private GreenfootImage[] walkingImages;
    private int[] currentImage = {0};  // Usando array para passar por referência
    private int[] animationCounter = {0};  // Usando array para passar por referência
    private int animationDelay = 9;
    private boolean isMoving = false;
    private boolean isFacingRight = true;
    
    // Atributos para ataque
    private GreenfootImage[] atackImages;
    private int[] currentImageAtack= {0};
    private int[] animationCounterAtack= {0};
    private int animationDelayAtack = 5;
    private boolean isAtacking = false;
    private final int damage = 2;
    private int health = 20;
    
    // Atributos para morte
    private GreenfootImage[] deadImages;
    private int[] currentImageDead = {0};
    private int[] animationCounterDead= {0};
    private int animationDelayDead = 15;
    private boolean isDead = false;
  
    protected GreenfootSound bgSound = new GreenfootSound("fundo.mp3");
    
    public MainPerson(){
        bgSound.playLoop();
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
            System.out.println(deadImages[i]);
        }
    }
     
  public void act()
    {
        if (isDead) {
            animateDead();
            bgSound.stop();
        } else {
            walkPerson(); 
            if (isMoving) {
                animationPerson(walkingImages, currentImage, animationCounter, animationDelay);
            }
            eliminateEnemy();
            animateAtackPerson();
            if (!isMoving && !isAtacking) { 
                setImage(new GreenfootImage("Idle1.png"));
            }
            
            if (isAtacking && !Greenfoot.isKeyDown("q") && !isMoving) {
                setImage(new GreenfootImage("Idle1.png"));
            }
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
                invertImage(walkingImages, atackImages);
                isFacingRight = false;
            }
        }
        if(walkRight){
            dx += 4;
            if(!isFacingRight){
                invertImage(walkingImages, atackImages);
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
    }
    
    private void animateAtackPerson() {
        if (Greenfoot.isKeyDown("q")) {
            if (!isAtacking) {
                isAtacking = true;
                Greenfoot.playSound("espada.mp3");
            }
            animationPerson(atackImages, currentImageAtack, animationCounterAtack, animationDelayAtack);
        } else {
            isAtacking = false; // Reseta o estado de ataque quando a tecla "q" não está pressionada
        }
    }
    
    private void animateDead() {
        boolean animationFinished = animationPerson(deadImages, currentImageDead, animationCounterDead, animationDelayDead, false);
        // Verifica se a animação terminou
        if (animationFinished) {
            Greenfoot.setWorld(new StartScreen());
        }
    }

     
    private void eliminateEnemy() {
        if (isTouching(FirstEnemy.class)) {
            FirstEnemy enemy = (FirstEnemy) getOneIntersectingObject(FirstEnemy.class);
            
            if (enemy != null) {
                if (Greenfoot.isKeyDown("q")) { 
                    enemy.updateHealth(damage);
                }
            }
        }
    }
 
    public void updateHealth(int damage) {
        health -= damage; 
        if (health <= 0 && !isDead) {  // Verifica se a saúde chegou a zero e se ainda não foi marcado como morto
            isDead = true;
        }
        if(health == 0){
            Greenfoot.playSound("gameover.mp3");
        }
        System.out.println(isDead);
    }
    
    private void removePerson(){
        getWorld().removeObject(this);
        return;
    }
}
