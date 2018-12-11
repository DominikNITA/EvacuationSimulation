package project.partie2.Vues;

import pobj.ihm.Drawable;
import pobj.physics.RectanglePhysique;

import java.awt.*;

public class VueBackground implements Drawable {
    private RectanglePhysique b;
    private Color color;

    public VueBackground(RectanglePhysique b, Color color) {
        this.b = b;
        this.color = color;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect((int)b.getPosX(),(int)b.getPosY(),(int)b.getHaut(),(int)b.getLarg());
    }

    @Override
    public int getPriority() {
        return 0;
    }
}
