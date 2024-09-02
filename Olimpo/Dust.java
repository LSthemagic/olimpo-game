import greenfoot.*;

public class Dust extends Effects {
    private GreenfootImage[] dustImages;
    private int currentImage = 0;
    private int animationCounter = 0;
    private int animationDelay = 26;
    private boolean fadingOut = false;
    private boolean isFacingRight = true;
    
    public Dust() {
        dustImages = new GreenfootImage[5];
        for (int i = 0; i < 5; i++) {  // Certifique-se de que o loop corresponde ao número de imagens disponíveis
            dustImages[i] = new GreenfootImage("dust/dust" + (i + 1) + ".png");
        }
        
        // Defina a imagem inicial para o objeto Dust
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
        fadingOut = true;
    }
    
    private void fadeOut() {
        GreenfootImage img = getImage();
        if (img != null) {
            int transparency = img.getTransparency();
            transparency -= 5; // Diminui a transparência gradualmente
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
                img.setTransparency(255); // Restaura a transparência total
                setImage(img);
            }
        }
    
        fadingOut = false;
    }
    
}
