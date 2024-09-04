import greenfoot.*;

public class Blood extends Effects {
    private GreenfootImage[] bloodImages;
    private int currentImage = 0;
    private int animationCounter = 0;
    private int animationDelay = 5;
    
    public Blood() {
        bloodImages = new GreenfootImage[9];
        for (int i = 0; i < bloodImages.length; i++) {
            bloodImages[i] = new GreenfootImage("blood/blood (0_" + (i) + ").png");
            bloodImages[i].scale(40, 40);
        }
        setImage(bloodImages[0]);
    }
    
    public void act() {
        animateBlood();
    }
    
    private void animateBlood() {
        animationCounter++;
        if (animationCounter >= animationDelay) {
            animationCounter = 0;
            currentImage++;
            if (currentImage >= bloodImages.length) {
                getWorld().removeObject(this); // Remove o efeito após a animação terminar
            } else {
                setImage(bloodImages[currentImage]);
            }
        }
    }
}
