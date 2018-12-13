package project.partie2;

import pobj.ihm.Fenetre;
import pobj.physics.MoteurPhysique;
import pobj.simuagent.Strategy;
import pobj.tools.Vecteur2D;
import project.partie2.Population.Population;

public class Simulation {
    private Salle salle;
    private Population population;
    private Fenetre mgraph;
    private MoteurPhysique mphys;


    public Simulation(Salle salle) {
        this.salle = salle;
        this.population = new Population(salle);
        this.mgraph = new Fenetre();
        this.mphys = new MoteurPhysique();
    }

    public Simulation(String filename, int cote){
        this(new Salle(filename,cote));
    }

    public void addGroupePersonnes(int amount, Strategy str){
        population.addGroupePersonne(amount, str);
    }

    public void addGroupePersonnes(int amount, Strategy str, Vecteur2D topLeftCorner, Vecteur2D bottomRightCorner){
        population.addGroupePersonne(amount, str, topLeftCorner.getX(), bottomRightCorner.getX(), topLeftCorner.getY(), bottomRightCorner.getY());
    }
}
