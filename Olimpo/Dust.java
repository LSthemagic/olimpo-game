import greenfoot.*;

public class Dust extends Effects {
    private GreenfootImage[] dustImages;
    private int currentImage = 0;
    private int animationCounter = 0;
    private int animationDelay = 9;
    private boolean fadingOut = false;
    private boolean isFacingRight = true;
    private int maxTransparency = 255;
    private int transparency = 50;
    
    public Dust() {
        dustImages = new GreenfootImage[8];
        for (int i = 0; i < 8; i++) {
            dustImages[i] = new GreenfootImage("smoke/smoke (0_" + (i) + ").png");
        }
        
        // Define a imagem inicial para o objeto Dust
        if (dustImages.length > 0 && dustImages[0] != null) {
            setImage(dustImages[0]);
        }
    }

    public void act() {
        if (fadingOut) {
            fadeOut();
        } else {
            animateDust();
        }
    }
    
    private void animateDust() {     
        animationCounter++;
        if (animationCounter >= animationDelay) {
            animationCounter = 0;
            currentImage++;
            if (currentImage >= dustImages.length) {
                currentImage = 0;
            }            
            setImage(dustImages[currentImage]);
        }
    }
    
    public void startFadingOut() {
        this.fadingOut = true;
    }
    
    public boolean getFadingOut() {
        return fadingOut;
    }
    
    private void fadeOut() {
        GreenfootImage img = getImage();
        if (img != null) {
            int transparency = img.getTransparency();
            transparency -= 5;
            if (transparency <= 0) {
                getWorld().removeObject(this);
            } else {
                img.setTransparency(transparency);
                setImage(img);
            }
        }
    }
    
    public void resetTransparency() {
        for(GreenfootImage img: dustImages){
            if (img != null) {
                img.setTransparency(maxTransparency); // Restaura a transparência total
                setImage(img);
            }
        }
    
        fadingOut = false;
    }
    
}
