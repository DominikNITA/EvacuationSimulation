package project.partie2;

import pobj.tools.Vecteur2D;
import project.partie2.Strategies.StrategieSauveQuiPeut;

public class FinalTest {
    public static void main(String[] args) throws InterruptedException{
        Simulation simulation1 = new Simulation("ressources/terrain1.trk", 10);
        simulation1.addGroupePersonnes(10, new StrategieSauveQuiPeut(simulation1.getSalle(),0.6));
        simulation1.prepareSimulation();
        simulation1.startSimulation();
        simulation1.showResultats();

        Thread.sleep(2000);

        Simulation simulation2 = new Simulation("ressources/terrain2.trk", 12);
        simulation2.addGroupePersonnes(8, new StrategieSauveQuiPeut(simulation2.getSalle(), 0.4));
        simulation2.addGroupePersonnes(12, new StrategieSauveQuiPeut(simulation2.getSalle(), 0.4),new Vecteur2D(50,200), new Vecteur2D(60,400));
        simulation2.prepareSimulation();
        simulation2.startSimulation();
        simulation2.showResultats();
    }
}
