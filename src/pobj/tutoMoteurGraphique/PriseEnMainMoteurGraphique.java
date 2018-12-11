package pobj.tutoMoteurGraphique;

import pobj.ihm.Fenetre;
import project.partie1.VueRond;

public class PriseEnMainMoteurGraphique {

    // Ajout d'Exception liées à la temporisation que l'on ne souhaite pas gérer
    public static void main(String[] args) throws InterruptedException {
        Fenetre mgraph = new Fenetre();
        VueRond r = new VueRond(100, 100, 20);
        // ajout d'un élément dans le moteur graphique
        mgraph.add(r);
        
        // boucle de mouvement + affichage
        for(int iter = 0; iter<100; iter++) {
            // mouvement du cercle
            r.move();
            // rafraichissement de l'affichage
            mgraph.repaint();      
            // temporisation (sinon, on ne voit rien)
            Thread.sleep(50);
        }
        // Attention, le programme ne s'arrête pas, il faut fermer la fenêtre
    }

}
