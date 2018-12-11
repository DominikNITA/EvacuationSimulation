package project.partie2.Vues.Tests;

import pobj.ihm.Drawable;
import pobj.ihm.Fenetre;
import pobj.simuagent.Terrain;
import pobj.simuagent.factory_terrain.MapFactoryFromFile_Matrix;
import project.partie1.VueRond;
import project.partie2.Salle;

import java.util.ArrayList;

public class BackgroundColorsTest {
    // Ajout d'Exception liées à la temporisation que l'on ne souhaite pas gérer
    public static void main(String[] args) throws InterruptedException {
        Fenetre mgraph = new Fenetre();
        Terrain[][] terr = MapFactoryFromFile_Matrix.build("ressources/testColors.trk");
        Salle salle = new Salle(terr,10);
        ArrayList<Drawable> drs =  salle.getBackgroundVues();
        for (Drawable dr:
            drs) {
            mgraph.add(dr);
        }
/*        mgraph.add(new VueRond(0,0,5));
        mgraph.add(new VueRond(50,0,10));*/
        mgraph.repaint();
    }
}
