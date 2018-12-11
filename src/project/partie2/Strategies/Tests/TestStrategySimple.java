package project.partie2.Strategies.Tests;

import pobj.ihm.Drawable;
import pobj.ihm.Fenetre;
import pobj.physics.MoteurPhysique;
import pobj.simuagent.Terrain;
import pobj.simuagent.factory_terrain.MapFactoryFromFile_Matrix;
import project.partie1.VueMur;
import project.partie2.Mur;
import project.partie2.Personne;
import project.partie2.Salle;
import project.partie2.Strategies.StrategySimple;
import project.partie2.Vues.VuePersonne;

import java.util.ArrayList;

public class TestStrategySimple {
    // Ajout d'Exception liées à la temporisation que l'on ne souhaite pas gérer
    public static void main(String[] args) throws InterruptedException {
        Fenetre mgraph = new Fenetre();
        // Moteur Physique 2D (vue dessus)
        MoteurPhysique mphys = new MoteurPhysique();

        Terrain[][] terr = MapFactoryFromFile_Matrix.build("ressources/terrain1.trk");
        Salle salle = new Salle(terr,10);


        ArrayList<Drawable> drs =  salle.getBackgroundVues();
        for (Drawable dr:
                drs) {
            mgraph.add(dr);
        }
        for (Mur m : salle.getMurs()){
            mphys.add(m);
            mgraph.add(new VueMur(m));
        }

        Personne p = new Personne(40,40, new StrategySimple());
        mphys.add(p);
        mgraph.add(new VuePersonne(p));

        for(int iter = 0; iter<200; iter++) {
            System.out.println("Tour " + iter);
            System.out.println(p.getVit());
            p.move();
            // mouvements
            mphys.updateMovablePosition();
            // affichage  // A DECOMMENTER UNE FOIS LES CLASSES D'AFFICHAGE REALISEES
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
