import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

public class Enemy extends Person
{
    
    private boolean isInverted = false;
    private int timeAttack = 0;
    private boolean isAttacking = false;
    private boolean markedForRemoval = false;
    private boolean imageInverse = false;
    boolean deadAnimationFinished = false;
    
    public Enemy(int health){
        super(health);
    }
    
    public Enemy(){
        super(100);
    }
    
    public void setMarkedForRemoval() {
        this.markedForRemoval = true;
    }
    
    public boolean isMarkedForRemoval() {
        return this.markedForRemoval;
    }
    
    public boolean isDeadAnimationFinished() {
        return this.deadAnimationFinished;
    }
    
    protected void followMainPerson(int speed, GreenfootImage[] walkingImages, GreenfootImage[] attackImages) {
        List<GoodPerson> goodPersons = getWorld().getObjects(GoodPerson.class);
        if (!goodPersons.isEmpty()) {
            GoodPerson closestPerson = null;
            double closestDistance = Double.MAX_VALUE;
            for (GoodPerson p : goodPersons) {
                int distanceX = p.getX() - getX();
                int distanceY = p.getY() - getY();
                double distance = Math.sqrt(distanceX * distanceX + distanceY * distanceY);
    
                if (distance < closestDistance) {
                    closestDistance = distance;
                    closestPerson = p;
                }
            }
    
            if (closestPerson != null) {
                int mainX = closestPerson.getX();
                int mainY = closestPerson.getY();
                int enemyX = getX();
                int enemyY = getY();
    
                // Calcula a direção para o GoodPerson mais próximo
                int dx = mainX - enemyX;
                int dy = mainY - enemyY;
    
                // Normaliza a direção para manter a velocidade constante
                double length = Math.sqrt(dx * dx + dy * dy);
                dx = (int) Math.round(dx / length);
                dy = (int) Math.round(dy / length);
    
                // Inverte as imagens se necessário, dependendo da direção
                if (walkingImages != null) {
                    if(!imageInverse){
                        if (dx < 0 && !isInverted) {
                            isInverted = true;
                            invertImage(walkingImages, attackImages);
                        } else if (dx > 0 && isInverted) {
                            isInverted = false;
                            invertImage(walkingImages, attackImages);
                        }
                    }else{
                        if (dx > 0 && !isInverted) {
                            isInverted = true;
                            invertImage(walkingImages, attackImages);
                        } else if (dx < 0 && isInverted) {
                            isInverted = false;
                            invertImage(walkingImages, attackImages);
                        }
                    }
                }
                
                // Move o inimigo em direção ao GoodPerson mais próximo
                setLocation(enemyX + dx * speed, enemyY + dy * speed);
    
                //evita sobreposição de inimigos
                avoidPersonOverlap();
                
                // Verifica se está na borda do mundo
                if (atWorldEdge()) {
                    moveToCenter();
                }
            }
        }
    }
    
    public void setImageIsInverse(boolean imageInverse){
        this.imageInverse = imageInverse;
    }
    
    protected boolean getIsAttacking(){
        return isAttacking;
    }
    
    protected boolean getIsInverted(){
        return isInverted;
    }
    
    protected void killMainPerson(int power){
        GoodPerson goodPerson = (GoodPerson) getOneIntersectingObject(GoodPerson.class);
        timeAttack++;
        if (timeAttack == 25) { 
            timeAttack = 0;
            if(goodPerson != null){
                goodPerson.updateHealth(power);
            }   
        }
    }

    protected void checkCollisionAndAttack() {
        if (isTouching(GoodPerson.class)) {
            isAttacking = true;
        } else {
            isAttacking = false;
        }
    }
    
    protected boolean atWorldEdge() {
        int x = getX();
        int y = getY();
        int worldWidth = getWorld().getWidth();
        int worldHeight = getWorld().getHeight();

        return x < 5 || x > worldWidth - 5 || y < 5 || y > worldHeight - 5;
    }
    
    protected void moveToCenter() {
        int centerX = getWorld().getWidth() / 2;
        int centerY = getWorld().getHeight() / 2;
        setLocation(centerX, centerY);
    }
}
