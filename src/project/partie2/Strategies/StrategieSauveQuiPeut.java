package project.partie2.Strategies;

import pobj.simuagent.Strategy;
import pobj.simuagent.Terrain;
import pobj.tools.Vecteur2D;
import project.partie2.Personne;
import project.partie2.Salle;

import java.util.ArrayList;
import java.util.Collections;

public class StrategieSauveQuiPeut implements Strategy {
    private Salle salle;
    private double preference;

    public StrategieSauveQuiPeut(Salle s){
        salle=s;
        preference=Math.random();
    }

    public StrategieSauveQuiPeut(Salle s, double p){
        salle=s;
        preference=p;
    }

    public Vecteur2D deplacement(Personne p){
            ArrayList<Vecteur2D> listeCibles = salle.getListeCibles();
            ArrayList<Vecteur2D> listetemp = (ArrayList<Vecteur2D>)listeCibles.clone();
            if(  p.getIsSafe() || Terrain.Safe == salle.get(p.getPos()) )
            {
                p.setSafe(true);
                return new Vecteur2D(p.getPosX() + 1, p.getPosY());
            }
            for(Vecteur2D cible : listeCibles){
                if (!p.getCiblesVisitees().contains(cible)){
                    if(Terrain.isTarget(salle.get(p.getPos()))){
                        Vecteur2D cibleARetirer = new Vecteur2D(((int)(p.getPosX()/salle.getCote()))*salle.getCote()+salle.getCote()/2.0,
                                ((int)(p.getPosY()/salle.getCote()))*salle.getCote()+salle.getCote()/2.0);
                        p.addCible(cibleARetirer);
                        listetemp.remove(cibleARetirer);
                    }
                }else{
                    listetemp.remove(cible);
                }
            }
            return Collections.min(listetemp, new ComparateurPosition(salle, p.getPos(), preference));
    }
}

