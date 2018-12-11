package pobj.simuagent.factory_terrain;

import java.io.*;

import pobj.simuagent.Terrain;
import pobj.tools.Vecteur2D;


public class MapFactoryFromFile_Matrix {

    public static Terrain[][] build(String filename) {
        try {
            InputStreamReader fr = new InputStreamReader(new FileInputStream(filename));
            BufferedReader in = new BufferedReader(fr);
            int cptLines = 0;

            String buf = in.readLine();// nb columns
            int nColumns = Integer.parseInt(buf.split(" ")[0]); // recherche d'un entier dans le premier mot
            buf = in.readLine();// nb lines
            int nLines = Integer.parseInt(buf.split(" ")[0]);

            Terrain[][] track = new Terrain[nLines][nColumns];

            while (true) {
                buf = in.readLine();			
                if (buf == null)
                    break;
                if(buf.length() != nColumns)
                    throw new Exception("nb de lignes incohérent...");

                for(int i=0; i<buf.length(); i++){
                    track[cptLines][i] = Terrain.conv(buf.charAt(i));
                }

                cptLines++;

            }

            if(cptLines != nLines)
                throw new Exception("nb de lignes incohérent...");
            in.close();

           
            return track;


        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Invalid Format or missing file : " + filename
                    + "... Loading aborted");
            return null;
        }

    }


}
