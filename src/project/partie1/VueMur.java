package project.partie1;

import pobj.ihm.Drawable;
import pobj.physics.RectanglePhysique;

import java.awt.*;

public class VueMur implements Drawable {
    private RectanglePhysique m;
    private Color color;

    public VueMur(RectanglePhysique m) {
        this.m = m;
    }
    public VueMur(RectanglePhysique m, Color color) {
        this.m = m;
        this.color = color;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.drawRect((int)m.getPosX(),(int)m.getPosY(),(int)m.getHaut(),(int)m.getLarg());
    }

    @Override
    public int getPriority() {
        return 0;
    }
}
