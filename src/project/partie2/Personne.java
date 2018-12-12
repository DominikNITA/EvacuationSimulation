package project.partie2;

import pobj.physics.CerclePhysique;
import pobj.simuagent.Strategy;
import pobj.tools.Vecteur2D;

import java.awt.*;

public class Personne extends CerclePhysique {
    private static final double RAYON = 4;
    private static final double MASSE = 1;
    private static final float INITIAL_SPEED = 1;
    private static final Color COLOR = Color.black;

    private int id;
    private static int cpt = 0;
    private Vecteur2D target;
    private Strategy str;
    private boolean isSafe;

    public Personne(double x, double y, Strategy str) {
        super(x, y, RAYON,MASSE);
        this.str = str;
        super.setVit(INITIAL_SPEED);
        isSafe = false;
        id = cpt++;
    }

    public static double getRayonPers() {
        return RAYON;
    }

    public static double getMassePers() {
        return MASSE;
    }

    public static Color getColorPers() {
        return COLOR;
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
        //TODO: add speed renewed
    }

    public void setDir(Vecteur2D dir){
        super.setDir(dir);
    }
    public void setVit(float vit) { super.setVit(vit);}
}
