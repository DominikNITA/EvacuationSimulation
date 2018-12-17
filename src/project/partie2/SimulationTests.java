package project.partie2;

import pobj.tools.Vecteur2D;
import project.partie2.Strategies.StrategieSauveQuiPeut;
import project.partie2.Strategies.StrategySimple;

public class SimulationTests {
    public static void main(String[] args) throws  InterruptedException{
        Simulation simulation = new Simulation("ressources/terrain1.trk", 10);
        simulation.addGroupePersonnes(10, new StrategieSauveQuiPeut(simulation.getSalle(),0.6));
        simulation.prepareSimulation();
        simulation.startSimulation();
        simulation.showResultats();
    }
}
