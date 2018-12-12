package project.partie2;

import pobj.ihm.Drawable;
import pobj.physics.RectanglePhysique;
import pobj.simuagent.Terrain;
import pobj.simuagent.factory_terrain.MapFactoryFromFile_Matrix;
import pobj.tools.Vecteur2D;
import project.partie1.VueMur;
import project.partie2.Vues.BackgroundManager;
import project.partie2.Vues.VueBackground;

import java.util.ArrayList;

public class Salle {
    private ArrayList<Vecteur2D> listeCibles;
    private Terrain[][] terr;
    private int cote;

    public Salle(Terrain[][] terr, int cote) {
        this.terr = terr;
        this.cote = cote;
        listeCibles = new ArrayList<>();
        findCibles();
    }

    public Salle(String filename, int cote){
        this(MapFactoryFromFile_Matrix.build(filename),cote);
    }

    private void findCibles() {
        for (int i = 0; i < terr.length; i++) {
            for (int j = 0; j <terr[i].length ; j++) {
                if(Terrain.isTarget(terr[i][j])){
                    // on ajoute cote/2 pour mettre le point au centre du Terrain
                    listeCibles.add(new Vecteur2D(i*cote + cote/2.0, j*cote +cote/2.0));
                }
            }
        }
    }

    public ArrayList<Vecteur2D> getListeCibles() {
        return listeCibles;
    }
    public Terrain get(int i, int j){
        return terr[i][j];
    }
    public Terrain get(Vecteur2D vect){
        int i = (int)(vect.getX()/cote);
        int j = (int)(vect.getY()/cote);
        return get(i,j);
    }
    public int getHaut(){
        //TODO: check for errors
        return terr[0].length;
    }
    public int getLarg(){
        //TODO: check for errors
        return terr.length;
    }
    public boolean isVisible(Vecteur2D source, Vecteur2D target){
        Vecteur2D dir = target.minus(source);
        dir.normalize();
        while(get(source) != Terrain.Mur){
            source = source.add(dir);
            if(source.distance(target) < 2)
                return true;
        }
        return false;
    }

    public ArrayList<Mur> getMurs(){
        ArrayList<Mur> res = new ArrayList<>();
        for (int i = 0; i < terr.length; i++) {
            for (int j = 0; j <terr[i].length ; j++) {
                if(Terrain.Mur == terr[i][j]){
                    res.add(new Mur(i*cote,j*cote,cote,cote));
                }
            }
        }
        return res;
    }

    public ArrayList<Drawable> getBackgroundVues(){
        ArrayList<Drawable> res = new ArrayList<>();
        for (int i = 0; i < terr.length; i++) {
            for (int j = 0; j <terr[i].length ; j++) {
                    res.add(new VueBackground(i*cote,j*cote, cote, BackgroundManager.getBackgroundColor(terr[i][j])));
            }
        }
        return res;
    }
}
