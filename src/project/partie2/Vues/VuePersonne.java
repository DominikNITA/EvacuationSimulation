package project.partie2.Vues;

import pobj.ihm.Drawable;
import pobj.physics.CerclePhysique;
import project.partie2.Personne;

import java.awt.*;

public class VuePersonne implements Drawable {
    private Personne personne;

    public VuePersonne(Personne pers) {
        this.personne = pers;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(personne.getColorPers());
        g.drawOval((int)personne.getPosX(), (int)personne.getPosY(), (int)personne.getRayon(), (int)personne.getRayon() );
    }

    @Override
    public int getPriority() {
        return 1;
    }
}
