package project.partie2.Strategies;

import pobj.simuagent.Strategy;
import pobj.tools.Vecteur2D;
import project.partie2.Personne;

public class StrategySimple implements Strategy {
    @Override
    public Vecteur2D deplacement(Personne p) {
        return new Vecteur2D(0,0);
    }
}
