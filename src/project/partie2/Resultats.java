package project.partie2;

import pobj.ihm.Drawable;
import pobj.physics.ObjectPhysique;
import project.partie2.Vues.VueResultats;

import java.util.ArrayList;

public class Resultats {
    private int iterations;
    private ArrayList<Personne> personnes;

    private final double MINUTES_PAR_ITERATION = 1/60.;
    public static final int EVACUATION_NOT_FINISHED = -1;

    public Resultats(ArrayList<Personne> personnes) {
        this.personnes = personnes;
    }

    public Drawable getResultatsVue(){
        return new VueResultats(this);
    }

    public void setIterations(int iterations) {
        this.iterations = iterations;
    }

    public double getTimeOfEvacuation(){
        if(iterations != EVACUATION_NOT_FINISHED){
            return iterations * MINUTES_PAR_ITERATION;
        }
        return Double.NaN;
    }

    public int getMaxCollisions(){
        int max = -1;
        for(Personne p: personnes){
            if(p.getCptChocs() > max){
                max = p.getCptChocs();
            }
        }
        return max;
    }

    public int getMinCollisions(){
        int min = Integer.MAX_VALUE;
        for(Personne p: personnes){
            if(p.getCptChocs() < min){
                min = p.getCptChocs();
            }
        }
        return min;
    }

    public int getNombrePersonnes(){
        return personnes.size();
    }
}
