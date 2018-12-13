package project.partie2.Population.Tests;

import pobj.ihm.Drawable;
import pobj.ihm.Fenetre;
import pobj.physics.MoteurPhysique;
import project.partie1.VueMur;
import project.partie2.Mur;
import project.partie2.Personne;
import project.partie2.Population.Population;
import project.partie2.Salle;
import project.partie2.Strategies.StrategySimple;
import project.partie2.Vues.VuePersonne;

public class PopulationTests {
    public static void main(String[] args) throws InterruptedException{
        Salle s = new Salle("ressources/terrain1.trk", 10);
        Population pop = new Population(s);
        pop.addGroupePersonne(500, new StrategySimple());
                //, 80, 130, 150, 225);

        Fenetre mgraph = new Fenetre();
        MoteurPhysique mphys = new MoteurPhysique();

        //Partie draw background et mur
        for (Drawable dr:
                s.getBackgroundVues()) {
            mgraph.add(dr);
        }
        for (Mur m : s.getMurs()){
            mphys.add(m);
            mgraph.add(new VueMur(m));
        }
        for (Personne p : pop.getPersonnes()){
            mphys.add(p);
            mgraph.add(new VuePersonne(p));
        }

        for(int iter = 0; iter<800; iter++) {
            System.out.println("Tour " + iter);
            pop.move();
            // mouvements
            mphys.updateMovablePosition();
            // affichage
            mgraph.repaint();
            // temporisation (sinon, on ne voit rien)
            Thread.sleep(50);

            if(!mphys.isMove()) {
                System.out.println("plus de mouvement => sortie");
                break;
            }
        }
    }
}
