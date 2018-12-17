package project.partie1;

import pobj.simuagent.Terrain;
import pobj.simuagent.factory_terrain.MapFactoryFromFile_Matrix;

public class TerrainTest {
    public static void main(String[] args){
        Terrain t1 = Terrain.Mur;
        Terrain t2 = Terrain.conv('#');
        System.out.println("Conversion vers Terrain fontionne: " + (t1 == t2));

        char chart3 = Terrain.conv(Terrain.Scene);
        char char4 = '.';
        System.out.println("Conversion ver char fonctionne: " + (chart3 == char4));

        System.out.println("Security level fonctionne: " + (Terrain.level(t1) == 12));
        Terrain t5 = Terrain.BorneExit_5;
        System.out.println("isTarget fonctionne: " + (!Terrain.isTarget(t1)&&Terrain.isTarget(t5)) );

        String directory = "ressources/terrain1.trk";
        System.out.println("Test d'affichage:");
        System.out.println("Fichier: " + directory);
        Terrain.display(MapFactoryFromFile_Matrix.build(directory));
    }
}
