package project.partie2.TerrainTest;

import pobj.simuagent.Terrain;

public class TerrainConv {
    public static void main(String[] args){
        Terrain t1 = Terrain.Mur;
        Terrain t2 = Terrain.conv('.');
        Terrain t3 = Terrain.conv('#');
        char charBE9 = Terrain.conv(Terrain.BorneExit_9);


        System.out.println(t1);
        System.out.println(t2);
        System.out.println(t3);
        System.out.println(t3==t1);
        System.out.println(charBE9);
        System.out.println(Terrain.level(t1));
        System.out.println(Terrain.isTarget(t1));
        
    }
}
