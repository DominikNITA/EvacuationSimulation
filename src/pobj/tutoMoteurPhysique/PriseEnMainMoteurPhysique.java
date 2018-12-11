package pobj.tutoMoteurPhysique;

import pobj.ihm.Fenetre;
import pobj.physics.CerclePhysique;
import pobj.physics.MoteurPhysique;
import pobj.physics.RectanglePhysique;
import project.partie1.VueMur;
import project.partie1.VueParticule;


public class PriseEnMainMoteurPhysique {
    
    // Ajout d'Exception liées à la temporisation que l'on ne souhaite pas gérer
    public static void main(String[] args) throws InterruptedException {
        
        // Moteur Physique 2D (vue dessus)
        MoteurPhysique mphys = new MoteurPhysique();
        // Création d'une boite = murs
        RectanglePhysique mur = new RectanglePhysique(50, 50, 200, 200);
        // Création d'une particule
        CerclePhysique particule = new CerclePhysique(100, 100, 10, 1);
        particule.setRandomDirectionAndVitesse();
        mphys.add(mur);
        mphys.add(particule);
        
        // graphiques: PARTIE A FAIRE
        Fenetre mgraph = new Fenetre();
        // Création des vues associées aux objets physiques
        mgraph.add(new VueParticule(particule));
        mgraph.add(new VueMur(mur));

        // boucle de mouvement + affichage
        for(int iter = 0; iter<200; iter++) {
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
        
        // Attention, le programme ne s'arrête pas, il faut fermer la fenêtre
    }
}
