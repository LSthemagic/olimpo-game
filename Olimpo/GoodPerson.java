import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

public class GoodPerson extends Person
{
    private boolean isAtacking = false;
    private boolean gameIsOver = false;
    protected static int quantityGoodPersonsDead = 0; 
    private boolean isSpecialUsed = false;
    int delay = 10;
    int count = 0;
    
    int delayTimeDead = 5;
    int countTimeDead = 0;
    
    public GoodPerson(int health) {
        super(health);
    }
    
    public void updateDeathCountAndCheckGameOver() {
        if (!gameIsOver) { 
            quantityGoodPersonsDead++;
            if (quantityGoodPersonsDead >= 2) {
                quantityGoodPersonsDead = 0;
                gameOverScreen();
            }
        }
    }
    
    private void gameOverScreen() {
        Greenfoot.playSound("gameover.mp3");
        Greenfoot.setWorld(new GameOverScreen());
        gameIsOver = true;
    }
    
    protected void animateAtackPerson(boolean key, GreenfootImage[] images, int[] currentImage, int[] animationCounter, int delay) {
        if (key) {
            if (!isAtacking) {
                isAtacking = true;
                Greenfoot.playSound("espada.mp3");
            }
            animationPerson(images, currentImage, animationCounter, delay);
        } else {
            isAtacking = false; // Reseta o estado de ataque
        }
    }
    
    protected void eliminateEnemy(int powerAttack) {
        if (isTouching(Enemy.class)) {
            Enemy enemy = (Enemy) getOneIntersectingObject(Enemy.class);
            if (enemy != null && isAtacking) {
                count++;
                if (count >= delay) {
                    count = 0;
                    
                    if(powerAttack >= 50 && !isSpecialUsed){
                        isSpecialUsed = true;
                        enemy.updateHealth(powerAttack);
                    }else{
                        enemy.updateHealth(2);
                    }
                }

                // Marcar o inimigo como morto, mas não removê-lo imediatamente
                if (enemy.getIsDead()) {
                    enemy.setMarkedForRemoval();  // Método para indicar que o inimigo será removido
                    Greenfoot.playSound("win.mp3");
                }

                // Atualizar a lista de inimigos ainda ativos
                List<Enemy> listEnemys = getWorld().getObjects(Enemy.class);
                
                // Verifique se restam inimigos não marcados para remoção
                boolean allEnemiesMarkedForRemoval = true;
                for (Enemy e : listEnemys) {
                    if (!e.isMarkedForRemoval()) {
                        allEnemiesMarkedForRemoval = false;
                        break;
                    }
                }

                // Se todos os inimigos estiverem marcados para remoção e a animação de morte tiver terminado
                if (allEnemiesMarkedForRemoval) {
                    Greenfoot.playSound("victory.mp3");
                    Greenfoot.delay(10);  // Adicione um pequeno delay para garantir que a animação termine
                    Greenfoot.setWorld(new LevelPassed(getWorld()));  // Passar para a próxima fase
                }
            }
        }
    }
    
    public boolean getIsAttacking() {
        return isAtacking;
    }
}
