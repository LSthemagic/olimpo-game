import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.ArrayList;

/**
 * Write a description of class Doctor here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Doctor extends Actor
{
    Dialog dialog;
    List<String> dialogs = new ArrayList<>();
    
    public Doctor(){
        GreenfootImage image = new GreenfootImage("persons/med/med-hp.png");
        image.scale(50, 75); 
        setImage(image);
        addDialogs();
        medicalAdvice();
    }
    
    private void addDialogs(){
        dialogs.add("Alta concedida.");
        dialogs.add("Tratamento iniciado.");
        dialogs.add("Medicação ajustada.");
        dialogs.add("Sintomas aliviados.");
        dialogs.add("Dieta modificada.");
        dialogs.add("Hidratação aumentada.");
        dialogs.add("Sintomas monitorados.");
        dialogs.add("Melhora observada.");
        dialogs.add("Tratamento concluído.");
        dialogs.add("Paciente estável.");
        dialogs.add("Medicação suspensa.");
        dialogs.add("Sintomas controlados.");
        dialogs.add("Paciente recuperado.");
    }
    
    private void medicalAdvice(){
        int index = Greenfoot.getRandomNumber(13);
        dialog = new Dialog(dialogs.get(index));
    }
        
    public boolean isTreatment(){
        if (isTouching(GoodPerson.class)){
            getWorld().addObject(dialog, getX(), getY() - 50);
            return true;
        }
        getWorld().removeObject(dialog);
        return false;
    }
}
