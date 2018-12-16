package project.partie2;

import pobj.ihm.Drawable;
import pobj.ihm.Fenetre;
import pobj.physics.MoteurPhysique;
import pobj.simuagent.Strategy;
import pobj.tools.Vecteur2D;
import project.partie1.VueMur;
import project.partie1.VueParticule;
import project.partie2.Population.Population;
import project.partie2.Vues.VueBackground;
import project.partie2.Vues.VuePersonne;

public class Simulation {
    private Salle salle;
    private Population population;
    private Fenetre mgraph;
    private MoteurPhysique mphys;

    private static final int MAX_SIMULATION_ITERATIONS = 800;

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

    public void prepareSimulation(){
        prepareBackground();
        prepareMurs();
        preparePersonnes();
    }

    public void prepareBackground(){
        for (Drawable vue : salle.getBackgroundVues()){
            mgraph.add(vue);
        }
    }

    public void prepareMurs(){
        for (Mur mur: salle.getMurs()){
            //ajout d'accesseur dans la class MoteurPhysique
            if(!mphys.getTab().contains(mur)) {
                mphys.add(mur);
                mgraph.add(new VueMur(mur));
            }
        }
    }

    public void preparePersonnes(){
        for (Personne personne: population.getPersonnes()){
            mphys.add(personne);
            mgraph.add(new VuePersonne(personne));
        }
    }

    public void startSimulation() throws InterruptedException{
        for(int iter = 0; iter<800; iter++) {
            System.out.println("Tour " + iter);
            population.move();
            // mouvements
            mphys.updateMovablePosition();
            // affichage
            mgraph.repaint();
            // temporisation (sinon, on ne voit rien)
            Thread.sleep(50);

            if(population.isSafe()){
                System.out.println("Evacuation reussite en " + iter + " iterations");
                break;
            }

            if(!mphys.isMove()) {
                System.out.println("plus de mouvement => sortie");
                break;
            }
        }
    }
}
