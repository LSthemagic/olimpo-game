import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Enemy extends Person
{
    
    private boolean isInverted = false;
    
    public Enemy(int health){
        super(health);
    }
       
    
    protected void followMainPerson(int speed, GreenfootImage[] walkingImages, GreenfootImage[] attackImages) {
       
       
         // Verifica se o MainPerson está no mundo
        MainPerson mainPerson = (MainPerson) getWorld().getObjects(MainPerson.class).get(0);
        if (mainPerson != null) {
            // Obtém as coordenadas do MainPerson
            int mainX = mainPerson.getX();
            int mainY = mainPerson.getY();
            
            // Obtém as coordenadas atuais do inimigo
            int enemyX = getX();
            int enemyY = getY();
            
            // Calcula a direção para o MainPerson
            int dx = mainX - enemyX;
            int dy = mainY - enemyY;
            
            // Normaliza a direção para manter a velocidade constante
            double length = Math.sqrt(dx * dx + dy * dy);
            dx = (int) Math.round(dx / length);
            dy = (int) Math.round(dy / length);
            
            if (dx < 0 && !isInverted) {
               isInverted = true;
               invertImage(walkingImages, attackImages);
            }else if(dx > 0 && isInverted){
                isInverted = false;
                invertImage(walkingImages, attackImages);
            }


            setLocation(enemyX + dx * speed, enemyY + dy * speed);
            if (atWorldEdge()) {
                moveToCenter();
            }
        }
    }
    
    private boolean atWorldEdge() {
        int x = getX();
        int y = getY();
        int worldWidth = getWorld().getWidth();
        int worldHeight = getWorld().getHeight();

        return x < 5 || x > worldWidth - 5 || y < 5 || y > worldHeight - 5;
    }
    
    private void moveToCenter() {
        int centerX = getWorld().getWidth() / 2;
        int centerY = getWorld().getHeight() / 2;
        setLocation(centerX, centerY);
    }
}
