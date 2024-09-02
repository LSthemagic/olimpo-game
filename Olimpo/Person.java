import greenfoot.*;

public class Person extends Actor {
    private int health = 0;
    private boolean isDead = false;
    private HealthBar healthBar;
    private Dust effects;
    private boolean isMoving = false;
    private boolean effectsAdded = false;  // Novo atributo para rastrear se o efeito já foi adicionado

    public Person() {
        this(100);
    }

    public Person(int health) {
        this.health = health;
        healthBar = new HealthBar(getHealth(), 100, 10);
        effects = new Dust();
    }

    public void setIsMoving(boolean isMoving){
        this.isMoving = isMoving;
    }
    
    @Override
    protected void addedToWorld(World world){
        getWorld().addObject(healthBar, getX(), getY() - 30);
    }
    
    public HealthBar getHealthBar(){
        return healthBar;
    }
    
    public Dust getDust(){
        return effects;
    }
    
    public int getHealth(){
        return health;
    }
    
    public boolean getIsDead(){
        return isDead;
    }
    
    public void updateHealth(int damage) {
        health -= damage;   
        healthBar.loseHealth(damage);
        if (getHealth() <= 0 && !isDead) {  // Verifica se a saúde chegou a zero e se ainda não foi marcado como morto
            isDead = true;
        }
        if(getHealth() == 0){
            Greenfoot.playSound("gameover.mp3");
        }
       
    }
    
    protected void removePerson(Person person){
        getWorld().removeObject(person);
        return;
    }
    
    public void animationPerson(GreenfootImage[] listImages, int[] currentImage, int[] animationCounter, int animationDelay) {
       animationPerson(listImages, currentImage, animationCounter, animationDelay, true);
    }
    
    public boolean animationPerson(GreenfootImage[] images, int[] currentImage, int[] animationCounter, int delay, boolean isLooping) {
        animationCounter[0]++;
        if (animationCounter[0] >= delay) {
            animationCounter[0] = 0;
            currentImage[0]++; 
            if (currentImage[0] >= images.length) {
                 if (isLooping) {
                    currentImage[0] = 0;
                } else {
                    currentImage[0] = images.length - 1; // Fixa na última imagem
                }
                return true;
            }
            setImage(images[currentImage[0]]);
        }
        return false;
    }

    public boolean walkPerson(boolean walkLeft, boolean walkRight, boolean walkUp, boolean walkDown, int speed){
        int dx = 0;
        int dy = 0;
        
        if(walkLeft){
            dx -= speed;
        }
        if(walkRight){
            dx += speed;
        }
        if(walkUp){
            dy -= speed;
        }
        if(walkDown){
            dy += speed;
        }
        if (dx != 0 || dy !=0 ){
            if(!colisionObject(dx, dy)){
                setLocation(getX() + dx, getY() + dy);
                setIsMoving(true);
                return true;
            }
        }
        
        return false;
    }
    
    
    public boolean colisionObject(int dx, int dy){
        int futureX = getX() + dx;
        int futureY = getY() + dy;
        
        setLocation(futureX, futureY);
        
        boolean colision = isTouching(Wall.class);
        
        
        setLocation(getX() - dx, getY() - dy);
        
        return colision;
    }
    
      public void invertImage(GreenfootImage[] walkingImages, GreenfootImage[] atackImages){
        for (GreenfootImage img: walkingImages){
            img.mirrorHorizontally();
        }
        
        for (GreenfootImage img: atackImages){
            img.mirrorHorizontally();
        }
    }
}