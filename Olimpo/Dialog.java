import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Dialog here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Dialog extends Actor {
    public Dialog(String message) {
        GreenfootImage image = new GreenfootImage(message, 24, Color.BLACK, Color.WHITE);
        setImage(image);
    }
}

