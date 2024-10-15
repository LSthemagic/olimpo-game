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
    private GreenfootImage[] attackImages;
    private int[] currentImageAttack = {0};
    private int[] animationCounterAttack = {0};
    private int animationDelayAttack = 5;
    private int powerAttack = 2;
    private int specialAttackPower = 50; // Poder do ataque especial
    private boolean isSpecialAttacked = false; // Marca se o ataque especial está ativo
    private boolean isSpecialUsed = false; // Marca se o ataque especial está ativo
    // Atributos para morte
    private GreenfootImage[] deadImages;
    private int[] currentImageDead = {0};
    private int[] animationCounterDead = {0};
    private int animationDelayDead = 15;
    private boolean deadAnimationFinished = false;
    private boolean deathProcessed = false; 
    
    // Atributos para efeitos
    private Dust dust;

    public MainPerson(int health) { 
        super(health);
        dust = getDust();
        walkingImages = new GreenfootImage[8];
        attackImages = new GreenfootImage[4];
        deadImages = new GreenfootImage[4];
        
        for (int i = 0; i < 8; i++) {
            walkingImages[i] = new GreenfootImage("persons/mainPerson/walk/Walk" + (i + 1) + ".png");
        }
        setImage(walkingImages[currentImage[0]]);
        
        for (int i = 0; i < 4; i++) {
            attackImages[i] = new GreenfootImage("persons/mainPerson/attack/MainAtack" + (i + 1) + ".png");
        }
        
        for (int i = 0; i < 4; i++) {
            deadImages[i] = new GreenfootImage("persons/mainPerson/dead/mainPersonDead" + (i + 1) + ".png");
        }
    }
     
    public void act() { 
        if (getIsDead() && !deathProcessed) {
            animateDead();
            if (deadAnimationFinished) {
                removePerson(this);
                updateDeathCountAndCheckGameOver();
                dust.startFadingOut();
                deathProcessed = true;
            }
        } else {
            walkPerson();
    
            if (isMoving) {
                animationPerson(walkingImages, currentImage, animationCounter, animationDelay);
                addDust();
            } else { 
                dust.startFadingOut();
            }
    
            // Lógica para ativar o ataque especial com a tecla 'm', apenas se ainda não tiver sido utilizado
            if (Greenfoot.isKeyDown("m") && !isSpecialUsed) {
                isSpecialAttacked = true; // Ativa o ataque especial
            } 
    
            // Chamada para eliminar inimigos
            eliminateEnemy(isSpecialAttacked ? specialAttackPower : powerAttack);
            animateAttack();
    
            // Define a imagem de idle se não estiver se movendo ou atacando
            if (!isMoving && !getIsAttacking()) {
                setImage(new GreenfootImage("persons/mainPerson/idle/Idle1.png"));
            } 
        } 
    }

    
    private void addDust() {
        if (dust.getFadingOut()) {
            dust.resetTransparency();
        }
        getWorld().addObject(dust, getX() - 30, getY() + 30);
        if (isFacingRight) {
            dust.setLocation(getX() - 30, getY() + 30);
        } else {
            dust.setLocation(getX() + 40, getY() + 30);
        }
    }
    
    private void walkPerson() {
        int dx = 0;
        int dy = 0;
        final boolean walkLeft = Greenfoot.isKeyDown("left");
        final boolean walkRight = Greenfoot.isKeyDown("right");
        final boolean walkUp = Greenfoot.isKeyDown("up");
        final boolean walkDown = Greenfoot.isKeyDown("down");
        
        isMoving = walkPerson(walkLeft, walkRight, walkUp, walkDown, speed);
        if (walkLeft) {
            dx -= speed;
            if (isFacingRight) {
                invertImage(walkingImages, attackImages);
                isFacingRight = false;
            }
        }
        if (walkRight) {
            dx += speed;
            if (!isFacingRight) {
                invertImage(walkingImages, attackImages);
                isFacingRight = true;
            }
        }
    }
    
    private void animateAttack() {
        final boolean keyAttack = Greenfoot.isKeyDown("space");
        animateAtackPerson(keyAttack, attackImages, currentImageAttack, animationCounterAttack, animationDelayAttack);
    }
    
    private void animateDead() {
        deadAnimationFinished = animationPerson(deadImages, currentImageDead, animationCounterDead, animationDelayDead, false);
    }
}
