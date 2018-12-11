package project.partie2;

import pobj.physics.CerclePhysique;
import pobj.simuagent.Strategy;
import pobj.tools.Vecteur2D;

import java.awt.*;

public class Personne extends CerclePhysique {
    private static double rayonPers = 2;
    private static double massePers = 1;
    private Color colorPers = Color.black;

    private int id;
    private static int cpt = 0;
    private Vecteur2D target;
    private Strategy str;


    public Personne(double x, double y) {
        super(x, y, rayonPers,massePers);
    }

    public static double getRayonPers() {
        return rayonPers;
    }

    public static double getMassePers() {
        return massePers;
    }

    public Color getColorPers() {
        return colorPers;
    }

    public int getId() {
        return id;
    }

    public Vecteur2D getTarget() {
        return target;
    }

    public void move(){

    }
    public void setDir(Vecteur2D dir){

    }
}
