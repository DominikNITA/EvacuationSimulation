package project.partie2.Strategies;

import pobj.simuagent.Terrain;
import pobj.tools.Vecteur2D;
import project.partie2.Salle;

import java.util.Comparator;

public class ComparateurPosition implements Comparator<Vecteur2D> {
    private Salle salle;
    private Vecteur2D pos;
    private double preference;

    public ComparateurPosition(Salle s, Vecteur2D p, double pref){
        salle=s;
        pos=p;
        preference=pref;
    }

    public int compare(Vecteur2D c1, Vecteur2D c2){
        if(!salle.isVisible(pos,c1)){
            if(!salle.isVisible(pos,c2)){
                return 0;
            }else{
                return 1;
            }
        }else{
            if (!salle.isVisible(pos,c2)){
                return -1;
            }else{
                double d1= Math.sqrt(Math.pow((c1.getX()-pos.getX()),2) + Math.pow(c1.getY()-pos.getY(),2));
                double d2= Math.sqrt(Math.pow((c2.getX()-pos.getX()),2) + Math.pow(c2.getY()-pos.getY(),2));
                // on ajoute 1 pour ne pas diviser par 0
                double s1 = Terrain.level(salle.get(c1))+1;
                double s2 = Terrain.level(salle.get(c2))+1;
                double proche = (1-preference) * (Math.max(d1, d2)/Math.min(d1,d2));
                double secu = preference * (Math.max(s1, s2)/Math.min(s1,s2));
                if (secu >proche || (secu==proche && preference>=0.5)){
                    if (s1 == s2){
                        return 0;
                    }else if(s1>s2){
                        return -1;
                    }else{
                        return 1;
                    }
                }else if (secu < proche || (secu==proche && preference<0.5)){
                    if (d1 == d2){
                        return 0;
                    }else if(d1>d2){
                        return 1;
                    }else{
                        return -1;
                    }
                }

            }
        }
        return 0;
    }
}
