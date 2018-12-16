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
    private Resultats resultats;

    private static final int MAX_SIMULATION_ITERATIONS = 101;

    public Simulation(Salle salle) {
        this.salle = salle;
        this.population = new Population(salle);
        this.mgraph = new Fenetre();
        this.mphys = new MoteurPhysique();
        this.resultats = new Resultats(population.getPersonnes());
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

    private void prepareBackground(){
        for (Drawable vue : salle.getBackgroundVues()){
            mgraph.add(vue);
        }
    }

    private void prepareMurs(){
        for (Mur mur: salle.getMurs()){
            //ajout d'accesseur dans la class MoteurPhysique
            if(!mphys.getTab().contains(mur)) {
                mphys.add(mur);
                mgraph.add(new VueMur(mur));
            }
        }
    }

    private void preparePersonnes(){
        for (Personne personne: population.getPersonnes()){
            if(!mphys.getTab().contains(personne)) {
                mphys.add(personne);
                mgraph.add(new VuePersonne(personne));
            }
        }
    }

    public void startSimulation() throws InterruptedException{
        for(int iter = 0; iter<MAX_SIMULATION_ITERATIONS; iter++) {
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
                resultats.setIterations(iter);
                return;
            }

            if(!mphys.isMove()) {
                System.out.println("plus de mouvement => sortie");
                return;
            }
        }
        System.out.println("Evacuation non reussi");
        resultats.setIterations(Resultats.EVACUATION_NOT_FINISHED);
    }

    public void showResultats(){
        //Clearing JFrame with:
        //https://stackoverflow.com/questions/6260855/how-to-clear-reset-a-jframe
        //https://stackoverflow.com/questions/9347076/how-to-remove-all-components-from-a-jframe-in-java
//        mgraph.removeAll();
//        mgraph.revalidate();
//        mgraph.repaint();
//        mgraph.add(resultats.getResultatsVue());
//        mgraph.repaint();
        Fenetre fenetreResultats = new Fenetre();
        fenetreResultats.setLocation(mgraph.getX() + mgraph.getWidth(), mgraph.getY());
        fenetreResultats.setSize(mgraph.getWidth(), mgraph.getHeight() / 3);

        fenetreResultats.add(resultats.getResultatsVue());
        fenetreResultats.repaint();
    }
}
