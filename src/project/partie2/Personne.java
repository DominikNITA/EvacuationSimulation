package project.partie2;

import pobj.physics.CerclePhysique;
import pobj.physics.ObjectPhysique;
import pobj.simuagent.Strategy;
import pobj.tools.Vecteur2D;

import java.awt.*;
import java.util.ArrayList;

public class Personne extends CerclePhysique {
    private static final double RAYON = 4;
    private static final double MASSE = 1;
    private static float inititalSpeed = 1;
    private static Color color = Color.black;

    private ArrayList<Vecteur2D> ciblesVisitees;
    private int id;
    private static int cpt = 0;
    private Vecteur2D target;
    private Strategy str;
    // Quand isSafe==false: personne se dirige vers zone safe, quand vrai, personne continue tout droit
    private boolean isSafe;

    public Personne(double x, double y, Strategy str) {
        super(x, y, RAYON,MASSE);
        this.str = str;
        super.setVit(inititalSpeed);
        isSafe = false;
        id = cpt++;
        ciblesVisitees = new ArrayList<>();
    }

    public static double getRayonPers() {
        return RAYON;
    }

    public static double getMassePers() {
        return MASSE;
    }

    public static Color getColorPers() {
        return color;
    }

    public int getId() {
        return id;
    }

    public Vecteur2D getTarget() {
        return target;
    }

    public void move(){
        if(str != null) {
            target = str.deplacement(this); // usage de la strategie sur la personne qui invoque move
        }
        else{
            target = new Vecteur2D(0,0);
        }
        setDir((target.minus(getPos())).normalize());
        super.setVit(inititalSpeed);
    }

    public void setDir(Vecteur2D dir){
        super.setDir(dir);
    }

    public boolean getIsSafe() {
        return isSafe;
    }

    public void setSafe(boolean safe) {
        isSafe = safe;
    }

    public void addCible(Vecteur2D v){
        ciblesVisitees.add(v);
    }

    public ArrayList<Vecteur2D> getCiblesVisitees() {
        return ciblesVisitees;
    }
}
