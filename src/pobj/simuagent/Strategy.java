package pobj.simuagent;

import pobj.tools.Vecteur2D;
import project.partie2.Personne;

public interface Strategy {
    Vecteur2D deplacement(Personne p);
}
