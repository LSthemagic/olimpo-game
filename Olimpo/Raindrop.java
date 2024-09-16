import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class RainDrop here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Raindrop extends Actor {
    public Raindrop() {
        GreenfootImage rainDrop = new GreenfootImage(2, 10);
        rainDrop.setColor(Color.BLUE);
        rainDrop.fill();
        setImage(rainDrop);
    }

    public void act() {
        // Move a gota para baixo
        setLocation(getX(), getY() + 4);
        
        // Verifica se a gota saiu da tela e depura a posição
        if (getY() == 599) {
            System.out.println("Removendo gota da posição: " + getY());  // Verifica a posição da gota
            getWorld().removeObject(this);  // Remove a gota do mundo quando sai da tela
        } else {
            System.out.println("Gota na posição: " + getY());  // Verifica a posição em cada ato
        }
    }
}


