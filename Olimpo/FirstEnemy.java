import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class FirstEnemy extends Enemy {
    
    // Atributos de imagem
    private GreenfootImage[] walkingImages;
    private GreenfootImage[] attackingImages;
    private int[] currentImage = {0};
    private int[] animationCounter = {0};
    private int animationDelay = 6; 
    private boolean isRightFacing = true; 
    private int speed = 5;
    private int moveCounter = 0;
    private int moveDelay = 7; 
    
    // Atributos de vida
    private boolean isDead = false;
    public int health = 100;
    
    // Atributos de ataque
    private int[] currentImageAttack = {0};
    private int[] animationCounterAttack = {0};
    private int animationDelayAttack = 10; 
    private boolean isAttacking = false;
    private final int powerAtack = 1;
    
    public FirstEnemy() {
        walkingImages = new GreenfootImage[8];
        attackingImages = new GreenfootImage[4];
        for (int i = 0; i < 8; i++) {
            walkingImages[i] = new GreenfootImage("WalkEnemy" + (i + 1) + ".png");
        }
        for (int i = 0; i < 4; i++) {
            attackingImages[i] = new GreenfootImage("AtackEnemy" + (i + 1) + ".png");
        }
        setImage(walkingImages[currentImage[0]]);
    }

    public void act() {
        if (isDead) {
            getWorld().removeObject(this);
            return;
        }

        if (isAttacking) {
            animationPerson(attackingImages, currentImageAttack, animationCounterAttack, animationDelayAttack);
            killMainPerson();
        } else {
            animationPerson(walkingImages, currentImage, animationCounter, animationDelay);
            if (moveCounter >= moveDelay) {
                moveCounter = 0;
                randomWalk(speed, isRightFacing, walkingImages, attackingImages);
            } else {
                moveCounter++;
            }
        }
        
        checkCollisionAndAttack();
    }
    
    private void killMainPerson(){
        MainPerson protagonist = (MainPerson) getOneIntersectingObject(MainPerson.class);
        if(protagonist != null){
            protagonist.updateHealth(powerAtack);
        }
    }

    public void updateHealth(int damage) {
        health -= damage;
        if (health <= 0) {
            isDead = true;
        }
    }


    private void checkCollisionAndAttack() {
        if (isTouching(MainPerson.class)) {
            isAttacking = true;
        } else {
            isAttacking = false;
        }
    }

}
