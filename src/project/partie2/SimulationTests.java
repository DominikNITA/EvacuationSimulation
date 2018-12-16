package project.partie2;

import project.partie2.Strategies.StrategySimple;

public class SimulationTests {
    public static void main(String[] args) throws  InterruptedException{
        Simulation simulation = new Simulation("ressources/terrain1.trk", 10);
        simulation.addGroupePersonnes(20, new StrategySimple());
        simulation.prepareSimulation();
        simulation.startSimulation();
        simulation.showResultats();
    }
}
