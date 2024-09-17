import java.util.List;
import greenfoot.*;

/**
 * Write a description of class T here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

class TeleportUtils {
    
    public static void teleportToHospital(World currentWorld, int x, int y, int toleranceX, int toleranceY) {
        List<GoodPerson> goodPersons = currentWorld.getObjects(GoodPerson.class);

        for (GoodPerson goodPerson : goodPersons) {
            int dx = Math.abs(goodPerson.getX() - x);
            int dy = Math.abs(goodPerson.getY() - y);

            if (dx <= toleranceX && dy <= toleranceY) {
                //System.out.println("Personagem está na área de teletransporte.");
                
                
                HealthBar healthBar = goodPerson.getHealthBar();
                
                Hospital hospital = new Hospital(currentWorld);
                hospital.addObject(goodPerson, goodPerson.getX(), goodPerson.getY());
                hospital.addObject(healthBar,  healthBar.getX(), healthBar.getY());
                
                Greenfoot.setWorld(hospital);
                break;
            }
        }
    }

    public static void teleportBackToWorld(World currentWorld, FirstWorld firstWorld, int x, int y, int toleranceX, int toleranceY) {
        List<GoodPerson> goodPersons = currentWorld.getObjects(GoodPerson.class);

        for (GoodPerson goodPerson : goodPersons) {
            int dx = Math.abs(goodPerson.getX() - x);
            int dy = Math.abs(goodPerson.getY() - y);

            if (dx <= toleranceX && dy <= toleranceY) {
                //System.out.println("Personagem está na área de retorno.");
                
                // Captura a HealthBar antes de mudar de mundo
                HealthBar healthBar = goodPerson.getHealthBar();
                

                firstWorld.addObject(goodPerson, goodPerson.getX(), goodPerson.getY());
                firstWorld.addObject(healthBar, healthBar.getX(), healthBar.getY()); // Posiciona a HealthBar
                //goodPerson.addedToWorld(firstWorld);
                
                Greenfoot.setWorld(firstWorld);
                break;
            }
        }
    }
}
