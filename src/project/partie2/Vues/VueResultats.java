package project.partie2.Vues;

import pobj.ihm.Drawable;
import pobj.physics.RectanglePhysique;
import project.partie2.Resultats;

import java.awt.*;

public class VueResultats implements Drawable {
    private RectanglePhysique m;
    private Color color;
    private Resultats resultats;

    public VueResultats(Resultats resultats) {
        this.m = new RectanglePhysique(20,20,300,200);
        this.color = Color.BLACK;
        this.resultats = resultats;
    }

    @Override
    public void draw(Graphics g) {
        //g.setColor(color);
        //g.drawRect((int)m.getPosX(),(int)m.getPosY(),(int)m.getHaut(),(int)m.getLarg());

        String timeStr = Double.isNaN(resultats.getTimeOfEvacuation()) ? "Evacuation n'a pas termine!" : "Evacuation a pris " + resultats.getTimeOfEvacuation() + "min";
        String minCollStr = "Le nombre de chocs minimal pour une personne: " + resultats.getMinCollisions();
        String maxCollStr = "Le nombre de chocs maximal pour une personne: " + resultats.getMaxCollisions();
        String nbPersonnesStr = "Le nombre de personnes dans la simulation: " + resultats.getNombrePersonnes();

        int margin = 30;
        g.drawString(timeStr,10,30 + margin);
        g.drawString(minCollStr,10,30 + 2*margin);
        g.drawString(maxCollStr,10,30 + 3*margin);
        g.drawString(nbPersonnesStr,10,30 + 4*margin);
        g.setFont(new Font("Arial", Font.BOLD,25));
        g.drawString("Resultats: ", 5, 30);
    }

    @Override
    public int getPriority() {
        return 3;
    }
}
