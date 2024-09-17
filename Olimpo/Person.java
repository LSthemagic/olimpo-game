import greenfoot.*;
import java.util.List;

public class Person extends Actor {
    private int health = 0;
    private int maxHealth = 0;
    private boolean isDead = false;
    private HealthBar healthBar;
    private Dust dust;
    private boolean isMoving = false;
    private boolean healthAdded = false;
    private int minDistanceBetweenPersons = 50;
    
    
    public Person() {
        this(100);
    }

    public Person(int health) {
        this.health = health;
        this.maxHealth = health;
        healthBar = new HealthBar(getHealth(), getHealth() , 10);
        dust = new Dust();
    }

    public void setIsMoving(boolean isMoving){
        this.isMoving = isMoving;
    }     

    @Override
    protected void addedToWorld(World world) {
        List<Person> persons = getWorld().getObjects(Person.class);
        int baseY = 20; 
        int spacing = 35; 
        if(!healthAdded){
            healthAdded = true;
            for (int i = 0; i < persons.size(); i++) {
                Person p = persons.get(i);
                int yPosition = baseY + i * spacing;
                // Adiciona a HealthBar no mundo
                world.addObject(p.getHealthBar(), 100, yPosition);
                
                // Cria uma cópia da imagem do Person
                GreenfootImage personImage = new GreenfootImage(p.getImage());
                
                // Escala a imagem copiada
                personImage.scale(personImage.getWidth() / 2, personImage.getHeight() / 2);
                
                // Cria um Actor temporário com a imagem escalada
                Actor imageActor = new Actor() {
                    { setImage(personImage); }
                };
                world.addObject(imageActor, 170, yPosition - 10);
                
            }
        }
    }

    public HealthBar getHealthBar(){
        return healthBar;
    }
    
    public Dust getDust(){
        return dust;
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
        if(getHealth() > 0){
            spawnBloodEffect();
        }
        if (getHealth() <= 0 && !isDead) {
            isDead = true;
        }
        
    }
    
    protected void avoidPersonOverlap() {
        List<Person> persons = getWorld().getObjects(Person.class);
        for (Person otherPerson : persons) {
            if (otherPerson != this) {
                int distanceX = otherPerson.getX() - getX();
                int distanceY = otherPerson.getY() - getY();
                double distance = Math.sqrt(distanceX * distanceX + distanceY * distanceY);

                if (distance < minDistanceBetweenPersons) {
                    // Move o inimigo na direção oposta ao outro para evitar sobreposição
                    int dx = (int) Math.round(distanceX / distance);
                    int dy = (int) Math.round(distanceY / distance);

                    setLocation(getX() - dx, getY() - dy);
                }
            }
        }
    }
    
    public void gainHealth(int amount) {
        health += amount;
        if (health > maxHealth) {
            health = maxHealth;
        }
    }

    private void spawnBloodEffect() {
        Blood blood = new Blood();
        BloodStain bloodStain = new BloodStain();
        getWorld().addObject(blood, getX(), getY());
        getWorld().addObject(bloodStain, getX(), getY());
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
            avoidPersonOverlap();
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