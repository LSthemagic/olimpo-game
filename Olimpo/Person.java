import greenfoot.*;

/**
 * Classe Person.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Person extends Actor {

    /**
     * Método para animar a caminhada.
     */
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
        return false; // Animação ainda em progresso
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