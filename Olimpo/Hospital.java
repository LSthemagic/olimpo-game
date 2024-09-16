import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

public class Hospital extends World {
    private FirstWorld firstWorld;
    private Doctor doctor;
    private Person person;
    // Tolerâncias para o teletransporte
    private static final int TOLERANCE_X = 20;
    private static final int TOLERANCE_Y = 10;
    
    private int countTreatment = 0;
    private int timeForTreatment = 5;
    

    public Hospital(World firstWorld) {    
        super(655, 473, 1); 
        this.firstWorld = (FirstWorld) firstWorld;
        this.doctor = new Doctor();
        addObject(this.doctor, 478, 375);
        //MainPerson p = new MainPerson(100);
        //addObject(p, 150, 225);
        setBackground("world/hp.png");
        
    }
    
    private void treatment(){
        if(getObjects(GoodPerson.class).isEmpty()){
            return;
        }
        person = getObjects(GoodPerson.class).get(0);
        
        if(person!=null){
            if (doctor.isTreatment()){
                countTreatment++;
                if(countTreatment < timeForTreatment) {
                    person.getHealthBar().gainHealth(1);
                    person.gainHealth(1);
                }
                    
                if(countTreatment == timeForTreatment) {
                    countTreatment = 0;
                }
                System.out.println(countTreatment);
            }
        }
    }

    @Override
    public void act() {
        TeleportUtils.teleportBackToWorld(this, firstWorld, 150, 225, TOLERANCE_X, TOLERANCE_Y);
        treatment();
    }
}

