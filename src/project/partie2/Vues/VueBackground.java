package project.partie2.Vues;

import pobj.ihm.Drawable;
import pobj.physics.RectanglePhysique;

import java.awt.*;

public class VueBackground implements Drawable {
    private double x,y;
    private int cote;
    private Color color;

    public VueBackground(double x, double y, int cote, Color color) {
        this.x = x;
        this.y = y;
        this.cote = cote;
        this.color = color;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect((int)x,(int)y,cote,cote);
    }

    @Override
    public int getPriority() {
        return 0;
    }
}
