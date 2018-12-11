package project.partie2.Vues;


import pobj.ihm.Drawable;
import pobj.physics.RectanglePhysique;
import pobj.simuagent.Terrain;
import project.partie1.VueMur;
import project.partie2.Mur;

import java.awt.*;

public class BackgroundManager {
    private BackgroundManager() { }

    //Color constants
    //According to http://colorizer.org/
    public final static Color CASE_VIDE = Color.BLUE;
    public final static Color CASE_SAFE = Color.ORANGE;
    public final static Color CASE_SCENE = Color.getHSBColor(23/360.0f, 80/100.0f, 60/100.0f);
    public final static Color CASE_EXIT9 = Color.getHSBColor(250/360.0f, 30/100.0f, 70/100.0f);
    public final static Color CASE_EXIT8 = Color.getHSBColor(250/360.0f, 50/100.0f, 70/100.0f);
    public final static Color CASE_EXIT7 = Color.getHSBColor(250/360.0f, 55/100.0f, 70/100.0f);
    public final static Color CASE_EXIT6 = Color.getHSBColor(300/360.0f, 50/100.0f, 70/100.0f);
    public final static Color CASE_EXIT5 = Color.getHSBColor(300/360.0f, 60/100.0f, 70/100.0f);
    public final static Color CASE_EXIT4 = Color.getHSBColor(300/360.0f, 80/100.0f, 70/100.0f);
    public final static Color CASE_EXIT3 = Color.getHSBColor(350/360.0f, 75/100.0f, 70/100.0f);
    public final static Color CASE_EXIT2 = Color.getHSBColor(350/360.0f, 85/100.0f, 70/100.0f);
    public final static Color CASE_EXIT1 = Color.getHSBColor(350/360.0f, 90/100.0f, 60/100.0f);

    //TODO: To refactor => shall be in Terrain class
    public static Color getBackgroundColor(Terrain t){
        Color color;
        switch (t){
            case Mur: color = Color.black;
                break;
            case Vide: color = CASE_VIDE;
                break;
            case Safe: color = CASE_SAFE;
                break;
            case Scene: color = CASE_SCENE;
                break;
            case BorneExit_1: color = CASE_EXIT1;
                break;
            case BorneExit_2: color = CASE_EXIT2;
                break;
            case BorneExit_3: color = CASE_EXIT3;
                break;
            case BorneExit_4: color = CASE_EXIT4;
                break;
            case BorneExit_5: color = CASE_EXIT5;
                break;
            case BorneExit_6: color = CASE_EXIT6;
                break;
            case BorneExit_7: color = CASE_EXIT7;
                break;
            case BorneExit_8: color = CASE_EXIT8;
                break;
            case BorneExit_9: color = CASE_EXIT9;
                break;
                default: color = Color.WHITE;
        }
        return color;
    }
}
