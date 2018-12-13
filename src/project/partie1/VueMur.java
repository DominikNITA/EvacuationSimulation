package project.partie1;

import pobj.ihm.Drawable;
import pobj.physics.RectanglePhysique;

import java.awt.*;

public class VueMur implements Drawable {
    private RectanglePhysique m;
    private Color color;

    public VueMur(RectanglePhysique m) {
        this(m, Color.BLACK);
    }
    public VueMur(RectanglePhysique m, Color color) {
        this.m = m;
        this.color = color;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        //TODO: substract 1 from width and height to match layout
        //https://docs.oracle.com/javase/7/docs/api/java/awt/Graphics.html#fillRect(int,%20int,%20int,%20int)
        g.drawRect((int)m.getPosX(),(int)m.getPosY(),(int)m.getHaut()-1,(int)m.getLarg()-1);
    }

    @Override
    public int getPriority() {
        return 0;
    }
}
